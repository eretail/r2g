package com.micro.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WarehouseItem implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	String itemName;
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItmSku() {
		return itmSku;
	}

	public void setItmSku(String itmSku) {
		this.itmSku = itmSku;
	}

	public int getWhsIdentity() {
		return whsIdentity;
	}

	public void setWhsIdentity(int whsIdentity) {
		this.whsIdentity = whsIdentity;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	String itmSku;
	int whsIdentity;
	int qty;
	
	public WarehouseItem(String itemName, int whsIdentity, String itmSku, int qty){
		this.itemName=itemName;
		this.itmSku=itmSku;
		this.qty=qty;
		this.whsIdentity=whsIdentity;
	}
	
	@Override
	public String toString() {
		return String.format("%1$-50s %2$-50s %3$-5s",  itemName, itmSku, qty);
	}
}
