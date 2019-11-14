package com.micro.rest.jpa.db.h2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.micro.rest.model.h2.Stock;
import com.micro.rest.model.id.StockId;

@Repository("h2StockRepo")
public interface StockRepo extends JpaRepository<Stock, StockId>{

	@Query("SELECT b FROM h2Stock b WHERE b.stockId.wsNum = :num")
	List<Stock> findByWsNum(@Param("num") Integer num);
}