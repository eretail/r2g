package com.micro.rest.model.id;

import java.io.Serializable;

import javax.persistence.Id;

public class ProductId  implements Serializable {
	@Id
	private String sku;
	
	public ProductId() {};
	public ProductId(String sku) {this.sku=sku;};
	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sku == null) ? 0 : sku.hashCode());
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
		
		return true;
	}
}
