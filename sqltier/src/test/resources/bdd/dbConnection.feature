Feature: Successful connection to database
	As a user of SmartAnalyzer
	I want to know that database connectivity has been established
	so that I can use the data from database.
	
Scenario: JSP page attempts to establish successful connection  
   Given the java code written to connect to the database is working
   When the connection has been established
   Then user can see the message "SUCCESS" displayed on screen. 
