package com.micro.rest.model.id;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class StockId implements Serializable {
	
	private String prodSku;
	private int wsNum;
	
	public String getProdSKU() {
		return prodSku;
	}

	public void setProdSKU(String prodSKU) {
		this.prodSku = prodSKU;
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
		this.prodSku=prodSKU;
		this.wsNum=wsNum;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((prodSku == null) ? 0 : prodSku.hashCode());
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
		
		if (prodSku == null) {
			if (other.getProdSKU() != null)
				return false;
		} else if (!prodSku.equals(other.getProdSKU()))
			return false;
		
		return wsNum == other.getWsNum();
	}	
}