package com.micro.rest.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.micro.rest.jpa.db.dynamodb.AwsServiceRepository;
import com.micro.rest.model.WarehouseItem;
import com.micro.rest.model.h2.Product;
import com.micro.rest.model.h2.Warehouse;
import com.micro.rest.service.R2GH2Svc;

@SpringBootApplication
@EntityScan(basePackages = {"com.micro.rest"} )
@ComponentScan(basePackages = {"com.micro.rest"} )
@EnableJpaRepositories(basePackages = {"com.micro.rest.jpa.db.h2"})
@EnableDynamoDBRepositories(basePackages = {"com.micro.rest.jpa.db.dynamodb"})
@EnableAutoConfiguration
public class SpringRestApplication implements CommandLineRunner{
    private static final Logger logger = LogManager.getLogger(SpringRestApplication.class);
	private DynamoDBMapper dynamoDBMapper;
	
	@Autowired @Qualifier("amazonDynamoDB") AmazonDynamoDB amazonDynamoDB;
    @Autowired AwsServiceRepository awsServiceRepository;

    @Autowired R2GH2Svc r2gSvc;
	
	static final String ADD_PRODUCT = "ADD PRODUCT";
	static final String ADD_WAREHOUSE = "ADD WAREHOUSE";
	static final String STOCK = "STOCK";
	static final String UNSTOCK = "UNSTOCK";
	static final String LIST_PRODUCTS = "LIST_PRODUCTS";
	static final String LIST_WAREHOUSES = "LIST_WAREHOUSES";
	static final String LIST_WAREHOUSE = "LIST_WAREHOUSE";
	
	static List<String> commandList=new ArrayList<String>();
	
	static {
		commandList.add(ADD_PRODUCT);
		commandList.add(ADD_WAREHOUSE);
		commandList.add(STOCK);
		commandList.add(UNSTOCK);
		commandList.add(LIST_PRODUCTS);
		commandList.add(LIST_WAREHOUSE);
		commandList.add(LIST_WAREHOUSES);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		r2gSvc.clean();
		System.out.println("WebService has started, please input your commands after >");
	}
}