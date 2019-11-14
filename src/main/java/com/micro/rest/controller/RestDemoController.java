package com.micro.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.rest.model.WarehouseItem;
import com.micro.rest.model.h2.Product;
import com.micro.rest.model.h2.Stock;
import com.micro.rest.model.h2.Warehouse;
import com.micro.rest.service.R2GH2Svc;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/h2")
public class RestDemoController {

    @Autowired R2GH2Svc r2gSvc;
	
	@PutMapping("/products/{name}/{sku}")
	public int addProduct(@PathVariable("sku") String sku, @PathVariable("name") String name){
		
		if(r2gSvc.hasProduct(sku)) return -1; 
		r2gSvc.addProduct(name, sku);
		
		return 1;
	}
	
	@PutMapping("/warehouse/{whsNum}/{limit}")
	public int addWarehouse(@PathVariable("whsNum") Integer whsNum, @PathVariable("limit") Integer limit){
		return r2gSvc.addWarehouse(whsNum, limit);
	}

	@PutMapping("/warehouse/{whsNum}")
	public int addWarehouse(@PathVariable("whsNum") Integer whsNum){
		
		if(r2gSvc.hasWarehouse(whsNum)) return -1; 
		
		r2gSvc.addWarehouse(whsNum, 100000000);
		return 1;
	}

	@PostMapping("/stocks/{sku}/{whsNum}/{qty}")
	public int stock(@PathVariable("sku") String sku, @PathVariable("whsNum") Integer whsNum, @PathVariable("qty") Integer qty){
		if(!r2gSvc.hasWarehouse(whsNum)) return -1;
		if(!r2gSvc.hasProduct(sku)) return -1; 
		try {
			r2gSvc.stock(sku, whsNum, qty);
		}
		catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	@DeleteMapping("/stocks/{sku}/{whsNum}/{qty}")
	public int unstock(@PathVariable("sku") String sku, @PathVariable("whsNum") Integer whsNum, @PathVariable("qty") Integer qty){
		if(!r2gSvc.hasWarehouse(whsNum)) return -1;
		if(!r2gSvc.hasProduct(sku)) return -1; 
		try {
			r2gSvc.unstock(sku, whsNum, qty);
		}
		catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return r2gSvc.getAllProducts();
	}

	@GetMapping("/stocks")
	public List<Stock> getAllStocks(){
		List<Stock> stocks=r2gSvc.findAllStockItems();
		return stocks;
	}

	@GetMapping("/warehouses")
	public List<Warehouse> getAllWarehouses(){
		return r2gSvc.getAllWarehouses();
	}

	@GetMapping("/warehouses/{wsNum}")
	public List<WarehouseItem> getWarehouseItem(@PathVariable("wsNum") Integer wsNum){
		List<Stock> stocks=r2gSvc.findAllStockItems();
		List<WarehouseItem> whList =new ArrayList<WarehouseItem>();
		if(stocks!=null && !stocks.isEmpty() && stocks.get(0).getStockId().getProdSKU()!=null) {
			whList= stocks.stream().filter(c->c.getStockId().getWsNum()==wsNum).map(c-> new WarehouseItem( 
					r2gSvc.getProduct( c.getStockId().getProdSKU() ).getName(), wsNum, r2gSvc.getProduct(c.getStockId().getProdSKU()).getSku(), 
					c.getProdQty() )).collect(Collectors.toList());
		}
		return whList;
	}		
}
