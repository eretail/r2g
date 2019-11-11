package com.micro.rest.app;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.micro.rest.model.WarehouseItem;
import com.micro.rest.model.h2.Product;
import com.micro.rest.model.h2.Warehouse;

@EntityScan(basePackages = {"com.micro.rest"} )
@ComponentScan(basePackages = {"com.micro.rest"} )
@EnableJpaRepositories(basePackages = {"com.micro.rest.jpa.db.h2"})
@EnableDynamoDBRepositories(basePackages = {"com.micro.rest.jpa.db.dynamodb"})
@EnableAutoConfiguration
public class H2CommandLine{
    private static final Logger logger = LogManager.getLogger(H2CommandLine.class);

	static final String ADD_PRODUCT = "ADD PRODUCT";
	static final String ADD_WAREHOUSE = "ADD WAREHOUSE";
	static final String STOCK = "STOCK";
	static final String UNSTOCK = "UNSTOCK";
	static final String LIST_PRODUCTS = "LIST PRODUCTS";
	static final String LIST_WAREHOUSES = "LIST WAREHOUSES";
	static final String LIST_WAREHOUSE = "LIST WAREHOUSE";
	static final String USER_NAME = "client";
	static final String PASSWORD = "client";
	
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

	private HttpHeaders createHeaders(String username, String password)  {
	   return new HttpHeaders() {{
	         String userAndPass = username + ":" + password;
	         byte[] encodedAuth = Base64.encodeBase64( 
	            userAndPass.getBytes(Charset.forName("US-ASCII")) );
	         String authHeader = "Basic " + new String( encodedAuth );
	         set( "Authorization", authHeader );
	      }};
	}

	private HttpEntity<String> createEntity()  {
			HttpHeaders headers = createHeaders(USER_NAME,PASSWORD);
			headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			
	        return new HttpEntity<String>(headers);
		}

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();

		Scanner scanner = new Scanner(System.in);
		
		H2CommandLine h2Command = new H2CommandLine();
		HttpEntity<String>  entity=h2Command.createEntity();
		
/*		do {
			System.out.print("Please Choice you prefered database, 1:SQL or 2:noSQL");
			System.out.println(">");
			dbPick=Integer.valueOf(scanner.nextLine());
		}while(dbPick!=1 && dbPick!=2);

		do {
			System.out.print("Please Choice you prefered operation, 1:Command or 2:MicroService");
			System.out.print(">");
			svcPick=Integer.valueOf(scanner.nextLine());
		}while(svcPick!=1 && svcPick!=2);

		boolean isAWS = (dbPick==2?true:false),
				isH2  = (dbPick!=1?false:true),
				isMicroservice=(svcPick==2?true:false),
				isCommand=(svcPick==1?true:false);
		*/
		System.out.println(">");
		
        String line = scanner.nextLine();
        /*List<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(line);
		while (m.find())
		    list.add(m.group(1)); // Add .replace("\"", "") to remove surrounding quotes.
		
        String[] cmdLine = Arrays.copyOf(list.toArray(), list.toArray().length, String[].class);*/
		
        do {
        		logger.info(line);
        	    String[] cmdLine = line.split("\"?( |$)(?=(([^\"]*\"){2})*[^\"]*$)\"?");
        		try {
        		String url = "http://localhost:8888/h2/";
        		String cmd=cmdLine[0]!=null&&(cmdLine[0].toUpperCase().equals(STOCK)||cmdLine[0].toUpperCase().equals(UNSTOCK))?cmdLine[0].toUpperCase()
        				: cmdLine[0].concat(" ").concat(cmdLine[1]).toUpperCase();
        		
	        	if(cmd.equals(ADD_PRODUCT)){									//ADD PRODUCT "PRODUCT NAME" SKU
	        		if(cmdLine.length == 4) {
	        			url = url.concat("products/").concat(cmdLine[2].replace("\"", "")).concat("/").concat(cmdLine[3]);
        		       ResponseEntity<String> response =restTemplate.exchange(url , HttpMethod.PUT, entity, String.class);
        		       String result = response.getBody();

        		       if("-1".equals(result)) 
        		    	   System.out.println("ERROR ADDING PRODUCT PRODUCT with SKU " + cmdLine[3] + " ALREADY EXISTS");
	        		}
	        		else 
	        			System.out.println("command error, please check your syntax");
	        	}
	        	else if(cmd.equals(ADD_WAREHOUSE)){								//ADD WAREHOUSE WAREHOUSE# [STOCK_LIMIT]
	        		if(cmdLine.length == 4||cmdLine.length == 3) {
	        		   url = url.concat("warehouse/").concat(cmdLine[2]);
	        		   url = cmdLine.length == 3?url:url.concat("/").concat(cmdLine[3]);
        		       ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        		       String result = response.getBody();

        		       if("-1".equals(result)) 
        		    	   System.out.println("ERROR ADDING WAREHOUSE WAREHOUSE with " + cmdLine[2] + " ALREADY EXISTS");
	        		}
	        		else 
	        			System.out.println("command error, please check your syntax");
	        	}
	        	else if(cmd.equals(STOCK)){
	        		if(cmdLine.length == 4)										//STOCK SKU WAREHOUSE# QTY
	        		{
	      			   url = url.concat("stocks/").concat(cmdLine[1]).concat("/").concat(cmdLine[2]).concat("/").concat(cmdLine[3]);

	        		   ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.POST, entity, String.class);
	        		   String result = response.getBody();
	        		   if(1==Integer.valueOf(result)) System.out.println(cmdLine[1]);
	        		}
	        		else 
	        			System.out.println("command error, please check your syntax");
	        	}
	        	else if(cmd.equals(UNSTOCK)){									//UNSTOCK SKU WAREHOUSE# QTY
	        		if(cmdLine.length == 4)
	        		{
	      			   url = url.concat("stocks/").concat(cmdLine[1]).concat("/").concat(cmdLine[2]).concat("/").concat(cmdLine[3]);
	        			
	        			   ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
	        		       String result = response.getBody();
//	        		       System.out.println(result);	        			
	        		}
	        		else 
	        			System.out.println("command error, please check your syntax");
	        	}
	        	else if(cmd.equals(LIST_PRODUCTS)){
	        		if(cmdLine.length == 2) {
		        		url = url.concat("products/");

		        		ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET, entity, String.class);
        		        String result = response.getBody();

        		        Gson gson = new Gson();        		       
        		        List<Product> prods=gson.fromJson(result, new TypeToken<List<Product>>(){}.getType());
        		       
        		        if(prods!=null&&!prods.isEmpty()) prods.stream().forEach(System.out::println);
	        		}
	        		else 
	        			System.out.println("command error, please check your syntax");
	        	}
	        	else if(cmd.equals(LIST_WAREHOUSES)){
	        		if(cmdLine.length == 2) {
		        		url=url.concat("warehouses/");
        		       ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET, entity, String.class);
        		       String result = response.getBody();
       		        
        		       Gson gson = new Gson();        		       
       		           List<Warehouse> whss=gson.fromJson(result, new TypeToken<List<Warehouse>>(){}.getType());
       		       
       		           if(whss!=null&&!whss.isEmpty()) 
       		        	   whss.stream().forEach(System.out::println);       			
	        		}
	        		else 
	        			System.out.println("command error, please check your syntax");
	        	}
	        	else if(cmd.equals(LIST_WAREHOUSE)){
	        		if(cmdLine.length == 3) {
		        		url=url.concat("warehouses/").concat(cmdLine[2]);

	        			ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET, entity, String.class);
	        			String result = response.getBody();

        		       Gson gson = new Gson();        		
//        		       Warehouse whs=gson.fromJson(result,Warehouse.class);
       		           List<WarehouseItem> whsi=gson.fromJson(result, new TypeToken<List<WarehouseItem>>(){}.getType());
       		       
       		           if(whsi!=null&&!whsi.isEmpty()) 
       		        	   whsi.stream().forEach(System.out::println);       			
	        		}
	        		else 
	        			System.out.println("command error, please check your syntax");
	        	 	}

		        }catch(Exception e) {
		        	System.out.println(e.getMessage());
//		        	e.printStackTrace();
//		        	System.exit(5);
		        }
		        System.out.print(">");
		        line = scanner.nextLine();
		        cmdLine=line.split(" ");

	        }while(!"x".equals(line));
	
		logger.info("Ended");
        scanner.close();
	}	
}