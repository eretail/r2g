package com.micro.rest.model.h2;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Entity(name="h2Product")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String sku;
	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	private String name;
	
	public Product(){
	}
	
	public Product(String sku, String name){
		this.sku=sku;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name + " " + sku;
	}
	
	@Override
	public int hashCode() {
		return Product.class.hashCode();
	}
}
