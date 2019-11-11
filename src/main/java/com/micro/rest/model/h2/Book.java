package com.micro.rest.model.h2;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity(name="h2Book")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String isbn;
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String getIsbn) {
		this.isbn = getIsbn;
	}

	public Book(){
	}
	
	public Book(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
	
	@Override
	public String toString() {
		return "Book id=" + id + ", name=" + name;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getIsbn(), book.getIsbn());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getIsbn());
    }
}
