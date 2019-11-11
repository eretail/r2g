Introduction

The original requirement is to build a very simple backend system to fetch data through command line. Adding to the original requirement, I would also like to build a small full-stack system as POC of Microservice and AWS, also I would like to experiment mulit database(SQL/noSQL) access through Springdata. I would like to challenge myself as a one day’s job to finish it, some might not be reachable for current approached, which will be continue to implemented later once getting more time.


Design

System is implemented on SpringBoot’s Microservices/Restful API. User can operate CRUD function through shell client application or web. The system is also designed on multiple database sources: H2 and DynamoDB to compare the SpringBoot implementation on SQL/noSQL, also, I would like to deploy the application to AWS later, which DynamoDB would be ideal economically.

Accomplishment

Application is successfully up running, Full fill the requirement on server side, directly service call from standalone application.
Rest Service is operated correctly on top of SpringBoot.
CRUD function is operated correctly on H2
Docker and SAM local have successfully tested on both DynamoDB and H2
Service side implementation on all functions
Add Spring Security on top of microservice, to access remotely through microservice, user criterial needed (client/client)
Implement Client Side function that  support the commands, calling through microservice
on H2, add microservice, the user still update and fetch data locally, but through microservice. 
Add Logs on both Client and Server Side
Build deployment package


Todo

Add logic into stock, the warehouse capacity limitation 
 Add logic into unstock, should be able to update the current number of products in stock
Enhance on error/exception handler and log mechanism
Enhance on end to end testing
Java9 JShell Implementation
Implement ORM on DynamoDB
Add Web Client calling Microservice
Deploy on AMS
Adding Lambada and API Gateway
