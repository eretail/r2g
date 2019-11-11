package com.micro.rest.model.dynamodb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.hibernate.annotations.DynamicUpdate;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.micro.rest.model.id.StockId;

@Entity(name="dynamoStock")
@DynamoDBTable(tableName = "Stock")
//@IdClass(StockId.class)
public class Stock {
	@Id
	private String id;
	private String prodSKU;
	private int wsNum;

	public Stock(){
	}
	
	public Stock(String prodSKU, int wsNum, int prodQty){
		this.prodSKU=prodSKU;
		this.wsNum=wsNum;
		this.prodQty=wsNum;
	}

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

	@DynamoDBAttribute
	public int getProdQty() {
		return prodQty;
	}
	
    @DynamoDBAttribute
	public String getProdSKU() {
		return prodSKU;
	}

    @DynamoDBAttribute
	public int getWsNum() {
		return wsNum;
	}

	public void setWsNum(int wsNum) {
		this.wsNum = wsNum;
	}

	public void setProdSKU(String prodSKU) {
		this.prodSKU = prodSKU;
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