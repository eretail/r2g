package com.micro.rest.model.h2;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.micro.rest.model.id.WarehouseId;

//@IdClass(WarehouseId.class)
@DynamicUpdate
@Entity(name="h2Warehouse")
@Table(name = "h2_warehouse")
public class Warehouse  implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int wsNum;

	public Warehouse(){
	}
	
	public Warehouse(int wsNum){
		this.wsNum=wsNum;
	}

	public Warehouse(int wsNum, int limitQty){
		this.wsNum=wsNum;
		this.limitQty=limitQty;
	}	
	
	public int getWsNum() {
		return wsNum;
	}

	public void setWsNum(int wsNum) {
		this.wsNum = wsNum;
	}

	public int getlimitQty() {
		return limitQty;
	}

	public void setlimitQty(int limitQty) {
		this.limitQty = limitQty;
	}

	private int limitQty;
	
	@Override
	public String toString() {
		return Integer.valueOf(wsNum).toString();
	}
	
}
