package com.micro.rest.jpa.db.dynamodb;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.micro.rest.app.Book;

@EnableScan
@Repository("dynamodbRepo")
public interface DynamoDBRepo extends CrudRepository<Book, Long>{
//	
//	@Query("SELECT b FROM Book b WHERE b.name = :name")
//	List<Book> findAllGoodBooks(@Param("name") String name);
	List<Book> findAll();
		
}
