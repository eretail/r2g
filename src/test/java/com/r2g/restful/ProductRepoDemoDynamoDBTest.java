package com.r2g.restful;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.micro.rest.jpa.db.h2.ProductRepo;
import com.micro.rest.model.dynamodb.Product;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductRepoDemoDynamoDBTest.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = { 
  "amazon.dynamodb.endpoint=http://localhost:8000/", 
  "amazon.aws.accesskey=test1", 
  "amazon.aws.secretkey=test231" })
public class ProductRepoDemoDynamoDBTest {
 
    private DynamoDBMapper dynamoDBMapper;
 
    @Autowired
    private AmazonDynamoDB amazonDynamoDB;
 
    @Autowired
    ProductRepo repository;
 
    private static final String EXPECTED_COST = "20";
    private static final String EXPECTED_PRICE = "50";
 
    @Before(value = "")
    public void setup() throws Exception {
/*        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
         
        CreateTableRequest tableRequest = dynamoDBMapper
          .generateCreateTableRequest(Product.class);
        tableRequest.setProvisionedThroughput(
          new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);
*/         
        //...
 
//        dynamoDBMapper.batchDelete
//        ((List<Product>)repository.findAll());
    }
 
    @Test
    public void sampleTestCase() {
//        Product dave = new Product(EXPECTED_COST, EXPECTED_PRICE);
/*        repository.save(dave);
 
        List<Product> result 
          = (List<Product>) repository.findAll();*/
         
//        assertTrue("Not empty", result.size() > 0);
//        assertTrue("Contains item with expected cost", 
//          result.get(0).getCost().equals(EXPECTED_COST));
    }
}
