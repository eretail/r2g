package com.micro.rest.jpa.db.dynamodb;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.micro.rest.model.dynamodb.Product;
import com.micro.rest.model.id.ProductId;

@Repository("dynamoProductRepo")
@EnableScan
public interface ProductRepo extends CrudRepository<Product, ProductId>{
	
//	List<Product> findAll();
	List<Product> findById(String id);

}
