package com.micro.rest.model.id;

import java.io.Serializable;

import javax.persistence.Id;

public class WarehouseId implements Serializable {
	@Id
	private int wsNum;
	
	public int getWsNum() {
		return wsNum;
	}

	public void setWsNum(int wsNum) {
		this.wsNum = wsNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this == null) ? 0 : this.hashCode());
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
		
		WarehouseId other = (WarehouseId) obj;
		
		return wsNum == other.getWsNum();
	}	
}
