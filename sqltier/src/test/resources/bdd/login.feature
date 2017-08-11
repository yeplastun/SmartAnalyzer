Feature: User should be able to login to the application
	As a user of SmartAnalyzer
	I want to login to the application with correct user name and password
	so that I can use the SmartAnalyzer.
	
Scenario: User tries to login with correct user name and password
   Given the database connection has been established
   When the user enters correct user name and password
   Then the user can see the message "LOGIN_SUCCESSFUL" displayed on screen.

Scenario: User tries to login with incorrect password
   Given the attempt to establish database connection has been successful
   When the user enters incorrect password
   Then the user sees the message "LOGIN_FAILED" displayed on screen.

Scenario: User tries to login with incorrect user name
   Given the connection to database has been established 
   When the user enters incorrect user name and password
   Then the user can view the message "LOGIN_FAILED" on screen.

 
   
   
