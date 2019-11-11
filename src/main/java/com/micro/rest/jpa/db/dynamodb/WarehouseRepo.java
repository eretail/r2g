package com.micro.rest.jpa.db.dynamodb;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.micro.rest.model.dynamodb.Warehouse;

@Repository("dynamodbWarehouseRepo")
@EnableScan
public interface WarehouseRepo extends CrudRepository<Warehouse, Long>{
//	
//	@Query("SELECT b FROM Warehouse b WHERE b.wsNum= :num")
//	List<Warehouse> findAllGoodBooks(@Param("num") int num);

}
