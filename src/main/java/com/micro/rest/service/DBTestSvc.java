package com.micro.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.micro.rest.app.Book;
import com.micro.rest.jpa.db.h2.H2Repo;

@Service
public class DBTestSvc {
	
	@Autowired @Qualifier("h2Repo") H2Repo h2Repo;
//	@Autowired H2Repo h2Repo;
    
	public void clean(){
		h2Repo.deleteAll();
	}
	public void saveAll(){
			h2Repo.save(new Book("e"));
			h2Repo.save(new Book("f"));
			h2Repo.save(new Book("c"));
			h2Repo.save(new Book("f8"));
			h2Repo.save(new Book("f"));
		}
/*	
//	@Bean
	public Book getFistOne(){
		if(!h2Repo.findAll().isEmpty()){
	//		h2Repo.findAll().forEach(a->{System.out.println(a);});
			
			return h2Repo.findAll().get(3);
			
		}
		return new Book("0000");
	}

//	@Bean
	public List<Book> getAll(){
		if(!h2Repo.findAll().isEmpty()){
	//		h2Repo.findAll().forEach(a->{System.out.println(a);});
			
			return h2Repo.findAll();
		}
		return null;
	}
*/	
/*//	@Bean
	public List<Book> getAllGoodBooks(String name){
		if(!h2Repo.findAllGoodBooks(name).isEmpty()){
	//		h2Repo.findAll().forEach(a->{System.out.println(a);});
			
			return h2Repo.findAllGoodBooks(name);
		}
		return null;
	}*/
	
}
