package com.micro.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rest.jpa.db.h2.WarehouseRepo;

@Service
public class WarehouseSvc {

	@Autowired 
	WarehouseRepo whsRepo;


}
