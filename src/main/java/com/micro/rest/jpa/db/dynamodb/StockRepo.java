package com.micro.rest.jpa.db.dynamodb;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.micro.rest.model.dynamodb.Stock;

@Repository("dynamoStockRepo")
@EnableScan
public interface StockRepo extends CrudRepository<Stock, String>{
//	
//	@Query("SELECT b FROM Stock b WHERE b.name = :name")
//	List<Book> findAllGoodBooks(@Param("name") String name);

}
