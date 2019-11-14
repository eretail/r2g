package com.micro.rest.model.h2;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.DynamicUpdate;

import com.micro.rest.model.id.StockId;

@DynamicUpdate
@Entity(name="h2Stock")
public class Stock implements Serializable {
	@EmbeddedId
	private StockId stockId;

	public Stock(StockId id, int qty) {
		this.stockId=new StockId();
		this.stockId.setProdSKU(id.getProdSKU());
		this.stockId.setWsNum(id.getWsNum());
		this.prodQty=qty;
	}
	public StockId getStockId() {
		return stockId;
	}

	public void setStockId(StockId stockId) {
		this.stockId = stockId;
	}

	public Stock(){
	}
	
/*	private Product product;
	private Warehouse warehouse;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
*/	
/*	public Stock(String prodSKU, int wsNum, int prodQty){
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
*/
	public int getProdQty() {
		return prodQty;
	}

	public void setProdQty(int prodQty) {
		this.prodQty = prodQty;
	}
	private int prodQty;
	
	@Override
	public String toString() {
		return "Stock's warehouse is:" + stockId.getWsNum() +" "+" product sku:" + stockId.getProdSKU() + ", total stocked count:" + prodQty;
	}
}