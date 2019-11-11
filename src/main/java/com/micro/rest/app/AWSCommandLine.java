package com.micro.rest.app;

import java.util.Optional;
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
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.google.gson.Gson;
import com.micro.rest.jpa.db.dynamodb.AwsServiceRepository;
import com.micro.rest.service.AwsService;
import com.micro.rest.service.DBTestSvc;
import com.micro.rest.service.R2GH2Svc;

@EntityScan(basePackages = {"com.micro.rest"} )
@ComponentScan(basePackages = {"com.micro.rest"} )
@EnableJpaRepositories(basePackages = {"com.micro.rest.jpa.db.h2"})
@EnableDynamoDBRepositories(basePackages = {"com.micro.rest.jpa.db.dynamodb"})
@EnableAutoConfiguration
//implements CommandLineRunner
public class AWSCommandLine {
    private static final Logger logger = LogManager.getLogger(AWSCommandLine.class);

	private DynamoDBMapper dynamoDBMapper;
	@Autowired @Qualifier("amazonDynamoDB") AmazonDynamoDB amazonDynamoDB;
    @Autowired AwsServiceRepository awsServiceRepository;

    static final String ADD_PRODUCT = "ADD PRODUCT";
	static final String ADD_WAREHOUSE = "ADD WAREHOUSE";
	static final String STOCK = "STOCK";
	static final String UNSTOCK = "UNSTOCK";
	static final String LIST_PRODUCTS = "LIST_PRODUCTS";
	static final String LIST_WAREHOUSES = "LIST_WAREHOUSE";
	
	public static void main(String[] args) {
		SpringApplication.run(AWSCommandLine.class, args);
	}

//	@Override
	public void run(String... args) throws Exception {
        
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(AwsService.class);

        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
        AwsService awsService = new AwsService();
        awsService.setServiceName("AWS DynamoDB");
        awsService.setServiceHomePageUrl("https://aws.amazon.com/dynamodb/?nc2=h_m1");
        awsService = awsServiceRepository.save(awsService);

        logger.info("Saved AwsService object: " + new Gson().toJson(awsService));

        String awsServiceId = awsService.getId();

        logger.info("AWS Service ID: " + awsServiceId);

        Optional<AwsService> awsServiceQueried = awsServiceRepository.findById(awsServiceId);

        if (awsServiceQueried.get() != null) {
            logger.info("Queried object: " + new Gson().toJson(awsServiceQueried.get()));
        }

        Iterable<AwsService> awsServices = awsServiceRepository.findAll();

        for (AwsService awsServiceObject : awsServices) {
            logger.info("List object: " + new Gson().toJson(awsServiceObject));
        }

	}
}
