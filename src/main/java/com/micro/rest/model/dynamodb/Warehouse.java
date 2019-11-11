package com.micro.rest.model.dynamodb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.micro.rest.model.id.WarehouseId;

@IdClass(WarehouseId.class)
@DynamicUpdate
@Entity(name="dynamoWarehouse")
@DynamoDBTable(tableName = "Warehouse")
public class Warehouse {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
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
	
	@DynamoDBAttribute
	public int getWsNum() {
		return wsNum;
	}

	public void setWsNum(int wsNum) {
		this.wsNum = wsNum;
	}

	@DynamoDBAttribute
	public int getlimitQty() {
		return limitQty;
	}

	public void setlimitQty(int limitQty) {
		this.limitQty = limitQty;
	}

	private int limitQty;
	
	@Override
	public String toString() {
		return "Warehouse id=" + id + ", number =" + wsNum + " capacility is " + limitQty;
	}
	
}
