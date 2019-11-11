package com.micro.rest.jpa.db.h2;

import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.micro.rest.app.Book;

@Repository
public interface H2Repo extends JpaRepository<Book, Long>{
	
//	@Query("SELECT b FROM Book b WHERE b.name = :name")
//	List<Book> findAllGoodBooks(@Param("name") String name);

}
