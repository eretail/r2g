package com.micro.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.micro.rest.jpa.db.dynamodb.StockRepo;
import com.micro.rest.jpa.db.dynamodb.WarehouseRepo;
import com.micro.rest.jpa.db.dynamodb.ProductRepo;
import com.micro.rest.model.dynamodb.Product;
import com.micro.rest.model.dynamodb.Warehouse;

@Service
public class R2GAWSSvc {
	
//	@Autowired
//	@Qualifier("dynamodbWarehouseRepo")
//	private WarehouseRepo warehouseRepo;

//	@Qualifier("dynamoProductRepo")
//	@Autowired
//	private ProductRepo productRepo;
//
//	@Autowired
//	@Qualifier("dynamoStockRepo")
//	private StockRepo stockRepo;
	
	static final String ADD_PRODUCT = "ADD PRODUCT";
	static final String ADD_WAREHOUSE = "ADD WAREHOUSE";
	static final String STOCK = "STOCK";
	static final String UNSTOCK = "UNSTOCK";
	static final String LIST_PRODUCTS = "LIST_PRODUCTS";
	static final String LIST_WAREHOUSES = "LIST_WAREHOUSE";
		
	public void clean(){
//		productRepo.deleteAll();
	}
	
	/*
	public void addProduct(String sku, String name){
		productRepo.save(new Product(sku,name));
	}

	public List<Product> getAllProduct(){

		if(!productRepo.findAll().isEmpty()){
			return productRepo.findAll();
		}
		return null;
	}

	public void getProduct(String sku, String name){
		productRepo.save(new Product(sku,name));
	}
	
	public void addWarehouse(int num, int qty){
		warehouseRepo.save(new Warehouse(num,qty));
	}

	public List<Warehouse> getAllWarehouses(){

		if(!warehouseRepo.findAll().isEmpty()){
			return warehouseRepo.findAll();
		}
		return null;
	}

	public List<Warehouse> getWarehouseStock(int num){

		if(!stockRepo.findAll().isEmpty()){
			return warehouseRepo.findAll();
		}
		return null;
	}
*/}
