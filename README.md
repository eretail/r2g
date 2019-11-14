# Introduction

The original requirement from Rooms To Go is to build a simple backend system to fetch data through command line. In addtion to fullfill the the original requirement, I would also like to build a small system as POC on Microservice, AWS, etc, also I would like to experiment multi database(SQL/noSQL) access based on SpringData. Two challenge myself as a weekend project to be done, if not be able to finish the goal, will leave it for future.

# Design:

System is based on SpringBoot’s Microservices/Restful API. So User can operate CRUD function through shell client application or web. The system is also designed on multiple database sources: H2 and DynamoDB to compare the SpringBoot implementation on SQL/noSQL.

# Installation
User needs to have maven and java8 installed in local environment in order to run this application, this application will be run on windows system.

First, user needs to be at a work directory, download, install.bat and run.bat. 

also, during the installation and running, the script will change directory, in case you need rerun, please make sure you are on right directory.

```
install.bat
```

![image](https://user-images.githubusercontent.com/26700142/68820059-18bab780-0658-11ea-9f88-aa1b1adb0730.png)

```
run.bat
```
![image](https://user-images.githubusercontent.com/26700142/68820729-4dc80980-065a-11ea-9596-638d920ea910.png)

# Usage

System has many enhancement based on original requirement, such as handling overflow and stock/ustoack tracking, also system will only throw error message if user has correctly input the correct command, otherwise, no error message will show. If command is correct, but argument is wrong, message will show to indicate user command error.

### Add warehouse
Enhancement made to let user add limit after the initial warehouse is created.

![image](https://user-images.githubusercontent.com/26700142/68821714-08590b80-065d-11ea-9466-f17635f7993d.png)

### Add products
![image](https://user-images.githubusercontent.com/26700142/68821827-54a44b80-065d-11ea-9064-0b9ad7be2c44.png)

### Stock/unstock in general condition
![image](https://user-images.githubusercontent.com/26700142/68821982-b1a00180-065d-11ea-9c9e-408e25269450.png)

### Stock/ustock among multiple warehouses and products 

![image](https://user-images.githubusercontent.com/26700142/68828413-9c35d200-0673-11ea-9a10-49c5cd0c5c9e.png)

### Enhanced Condition
warehouse add limit during transaction of stock process, and ovestock, which will add partial of stock to hit the limit.

![image](https://user-images.githubusercontent.com/26700142/68829322-883f9f80-0676-11ea-8c95-6881582499b4.png)

# Current Status and Enhancement

Application is successfully up running, Full fill the requirement on server side, directly service call from standalone application.

Rest Service is operated correctly on top of SpringBoot

CRUD function is operated correctly on H2

Docker and SAM local have successfully tested on both DynamoDB and H2

Service side implementation on all functions

Add Spring Security on top of microservice, to access remotely through microservice, user criterial needed (client/client)

Implement Client Side function that support the commands, calling through microservice

on H2, add microservice, the user still update and fetch data locally, but through microservice.

Add logs on both Client and Server Side

Add logic into stock, the warehouse capacity limitation

Add logic into unstock, should be able to update the current number of products in stock

Enhance on error/exception handler and log mechanism

Enhance on end to end testing

Build deployment package


# Roadmap

Java9 JShell Implementation

Implement ORM on DynamoDB

Add Web Client calling Microservice

Deploy on AMS

Adding Lambada and API Gateway

```
yingchun.li @Atlanta
```