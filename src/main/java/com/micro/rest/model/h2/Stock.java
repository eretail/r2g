package com.micro.rest.model.h2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.hibernate.annotations.DynamicUpdate;

import com.micro.rest.model.id.StockId;

@DynamicUpdate
@Entity(name="h2Stock")
@IdClass(StockId.class)
public class Stock {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Id
	private String prodSKU;
	@Id
	private int wsNum;

	public Stock(){
	}
	
	public Stock(String prodSKU, int wsNum, int prodQty){
		this.prodSKU=prodSKU;
		this.wsNum=wsNum;
		this.prodQty=prodQty;
	}
	
	public String getProdSKU() {
		return prodSKU;
	}

	public void setProdSKU(String prodSKU) {
		this.prodSKU = prodSKU;
	}

	public int getWsNum() {
		return wsNum;
	}

	public void setWsNum(int wsNum) {
		this.wsNum = wsNum;
	}

	public int getProdQty() {
		return prodQty;
	}

	public void setProdQty(int prodQty) {
		this.prodQty = prodQty;
	}
	private int prodQty;
	
	@Override
	public String toString() {
		return "Stock id=" + id + ", warehouse number =" + wsNum + ", prodQty " + prodQty;
	}
}