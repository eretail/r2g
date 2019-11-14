package com.micro.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.micro.rest.jpa.db.h2.ProductRepo;
import com.micro.rest.jpa.db.h2.StockRepo;
import com.micro.rest.jpa.db.h2.WarehouseRepo;
import com.micro.rest.model.h2.Product;
import com.micro.rest.model.h2.Stock;
import com.micro.rest.model.h2.Warehouse;
import com.micro.rest.model.id.StockId;

@Service
public class R2GH2Svc {
	@Autowired
	@Qualifier("h2WarehouseRepo")
	private WarehouseRepo warehouseRepo;

	@Qualifier("h2ProductRepo")
	@Autowired
	private ProductRepo productRepo;

	@Autowired
	@Qualifier("h2StockRepo")
	private StockRepo stockRepo;
	
	public void clean(){
		productRepo.deleteAll();
		warehouseRepo.deleteAll();
		stockRepo.deleteAll();
	}
	
	public void addProduct(String name,String sku){
		productRepo.save(new Product(sku,name));
	}

	public List<Product> getAllProducts(){

		if(!productRepo.findAll().isEmpty()){
			return productRepo.findAll();
		}
		return null;
	}

	public Product getProduct(String sku){
		return (productRepo.findBySKU(sku)==null||productRepo.findBySKU(sku).isEmpty())?null:productRepo.findBySKU(sku).get(0);
	}

	public int stock(String sku, Integer whsNum, Integer qty) {
		
		int stkQty=0;
		List<Stock> stkList= stockRepo.findByWsNum(whsNum);
		int whsCurrentStockedProdQty = stkList.stream().mapToInt(c->c.getProdQty()).sum();		

		Stock stk=stkList.stream().filter(c->sku.equals(c.getStockId().getProdSKU())).findFirst().orElse(null);
		List<Warehouse> whss=warehouseRepo.findByWsNum(whsNum);
		if(whss==null||whss.isEmpty()) return -1;
		
		if(stk!=null && stk.getStockId().getProdSKU()!=null) stkQty = stk.getProdQty();		
		int whsLimit = whss.get(0).getlimitQty();
		
		if( whsLimit > whsCurrentStockedProdQty) { 				// there are space available to stock
			if( whsLimit >= whsCurrentStockedProdQty + qty) {   //all in
				qty=qty+stkQty;
			}else {
				qty=whsLimit-whsCurrentStockedProdQty ;
			}

			if(stk!=null) stk.setProdQty(qty);       			//only update stock quantity
			else stk = new Stock(new StockId(sku, whsNum),qty);
			
			stockRepo.save(stk);
			return qty;											//partially stocked
		}
		else return -1;
	}

	public int unstock(String sku, Integer whsNum, Integer qty) {
		try {
			int stkQty=0;

			Stock stk=stockRepo.getOne(new StockId(sku,whsNum));
			if(stk!=null) stkQty = stk.getProdQty();		
			
			qty= (stkQty >= qty)?stkQty - qty:0; 					// there are enough products to unstock
	
			if(stk==null) return 0;
			
			stk.setProdQty(qty); 				      				//only update stock quantity
			
			if(qty==0) stockRepo.delete(stk);
			else stockRepo.save(stk);
			return qty;
		}catch(Exception e) {
			return -1;
		}
	}

	public int addWarehouse(int num, int qty){
		List<Warehouse> whs= warehouseRepo.findByWsNum(num);

		List<Stock> stkList= stockRepo.findByWsNum(num);
		int whsCurrentStockedProdQty = stkList.stream().mapToInt(c->c.getProdQty()).sum();		
		
		if(qty<whsCurrentStockedProdQty) return -2;
		if (whs == null|| whs.isEmpty()) 
			warehouseRepo.save(new Warehouse(num,qty));
		else {
			Warehouse wh = whs.get(0); wh.setlimitQty(qty);
			warehouseRepo.save(wh);
		}
		return 1;
	}

	public List<Warehouse> getAllWarehouses(){

		if(!warehouseRepo.findAll().isEmpty()){
			return warehouseRepo.findAll();
		}
		return null;
	}

	public List<Stock> getWarehouseStock(int num){
		 return stockRepo.findByWsNum(num);
	}

	public List<Stock> findAllStockItems(){
		 return stockRepo.findAll();
	}
	
	public boolean hasProduct(String sku) {
		return productRepo.findBySKU(sku)!=null && !productRepo.findBySKU(sku).isEmpty(); 
	}

	public boolean hasWarehouse(Integer whsNum) {
		return warehouseRepo.findByWsNum(whsNum)!=null && !warehouseRepo.findByWsNum(whsNum).isEmpty(); 
	}
}
