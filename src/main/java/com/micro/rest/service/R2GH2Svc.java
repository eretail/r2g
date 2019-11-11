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

	public void stock(String sku, Integer whsNum, Integer qty) {
		Stock st = new Stock(sku, whsNum, qty);
		stockRepo.saveAndFlush(st);
	}

	public void unstock(String sku, Integer whsNum, Integer qty) {
		Stock st = new Stock(sku, whsNum, qty);
		stockRepo.delete(st);
	}

	public void addWarehouse(int num, int qty){
		List<Warehouse> whs= warehouseRepo.findByWsNum(num);
		if (whs == null|| whs.isEmpty()) 
			warehouseRepo.save(new Warehouse(num,qty));
		else
			warehouseRepo.save(whs.get(0));
	}

	public List<Warehouse> getAllWarehouses(){

		if(!warehouseRepo.findAll().isEmpty()){
			return warehouseRepo.findAll();
		}
		return null;
	}

	public List<Stock> getWarehouseStock(int num){
//		 return stockRepo.findByWsNum(num);
		 return stockRepo.findWarehouseItem(num);
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
