package com.micro.rest.jpa.db.h2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.micro.rest.model.WarehouseItem;
import com.micro.rest.model.h2.Product;
import com.micro.rest.model.h2.Stock;
import com.micro.rest.model.id.StockId;

import org.springframework.stereotype.Repository;

@Repository("h2StockRepo")
public interface StockRepo extends JpaRepository<Stock, StockId>{

	@Query("SELECT b FROM h2Stock b WHERE b.wsNum = :num")
	List<Stock> findWarehouseItem(@Param("num") Integer num);
	
	List<Stock> findByWsNum(int num);
	
	List<Product> findProductsByWsNum(int num);
	
	@Query("select s.p from h2Stock s where s.wsNum=:whsNum")
	List<Product> getTotalProductsStockedInWarehouse(int num);
}