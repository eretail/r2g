package com.micro.rest.jpa.db.h2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.micro.rest.model.h2.Product;

@Repository("h2ProductRepo")
public interface ProductRepo extends JpaRepository<Product, String>{

	@Query("SELECT b FROM h2Product b WHERE b.sku = :sku")
	List<Product> findBySKU(@Param("sku") String sku);

}