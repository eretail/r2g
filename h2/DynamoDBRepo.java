package com.micro.rest.jpa.db.h2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.micro.rest.model.Book;

public interface DynamoDBRepo extends JpaRepository<Book, Long>{
	
	@Query("SELECT b FROM Book b WHERE b.name = :name")
	List<Book> findAllGoodBooks(@Param("name") String name);

}
