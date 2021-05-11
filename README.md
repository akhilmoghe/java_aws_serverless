# Serverless Application
	This repository contains sample to build serverless apllication using below technology stack.
		- Front-end: Angular 6
		- Back-end: Spring Boot
		- Database: Mongo DB
	AWS Service:
		- AWS Lambda
		- API Gateway
	This serverless apllication is single page application and performs CRUD operations on "users" document.
	
	Step 1: Install and create mongodb database "test/test" 
	
	Step 2: Build the Angular 6 application for List users, Add User, Edit and Delete Users. Sample code here
		- Deploy the angular 6 code S3 bucket.
			- Create S3 bucket
			- Static Website enable
	
	Step 3: Build Spring boot Rest API for GET, POST, PUT and DELETE for users. Sample code here
		- Create AWS Lambda function "users.
		- Upload spring boot jar to AWS Lambda
		- Setup the handler created
		
	Step 4: Setup AWS API Gateway
