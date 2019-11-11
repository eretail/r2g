package com.micro.rest.jpa.db.h2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.rest.model.h2.Warehouse;

@Repository("h2WarehouseRepo")
public interface WarehouseRepo extends JpaRepository<Warehouse, Integer>{
	public List<Warehouse> findByWsNum(Integer whsNum);
}
