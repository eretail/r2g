package com.micro.rest.model.id;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

public class StockId  implements Serializable {
	@Id
	private String prodSKU;
	@Id
	private int wsNum;
	
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
	
	public StockId(){
	}
	
	public StockId(String prodSKU, int wsNum){
		this.prodSKU=prodSKU;
		this.wsNum=wsNum;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((prodSKU == null) ? 0 : prodSKU.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		StockId other = (StockId) obj;
		
		if (prodSKU == null) {
			if (other.getProdSKU() != null)
				return false;
		} else if (!prodSKU.equals(other.getProdSKU()))
			return false;
		
		return wsNum == other.getWsNum();
	}	
}