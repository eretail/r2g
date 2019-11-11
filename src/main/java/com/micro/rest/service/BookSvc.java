package com.micro.rest.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.micro.rest.app.Book;

@Service
public class BookSvc {
	HashMap<String, Book> hmBooks=new HashMap<String, Book>(); 
	
	BookSvc() {
		Book bk1=new Book("yuname");
		Book bk2=new Book("oname");
		Book bk3=new Book("iuname");
		Book bk4=new Book("quname");
		
		hmBooks.put("1",bk1);
		hmBooks.put("2",bk2);
		hmBooks.put("3",bk3);
		hmBooks.put("4",bk4);
	}
	
	public Book getBook(String id){
		return hmBooks.containsKey(id)?hmBooks.get(id):null;
	}

	public HashMap<String, Book> getAll(){
		return hmBooks;
	}
}
