# WK-Automation-Test-Assignment

## Created by:
Kaveh - 17/10/2021

## Project name: 
Todos list testing Project

## Description: 
The functionality of a web app https://todomvc.com/examples/angular2/ would be tested in this project. This project has based on the below steps:

## STLC (Software test life cycle)
** Test requirement analysis: In this step, the required items for the test will be analyzed. **
	For getting an acceptable result Acceptance test and load testing has been done for this web app.

** Test planning: **
	It has been planned to test all existing items on the web page with positive and negative tests which mean using correct and incorrect data. 

** Test cases development: **
	The test scenarios were collected in the Scenarios.xlsx file according to the structure of black-box testing. 
	
** Test environment setup: Test environment is accessible easily by the specific link. **
	The automation test provided is based on the test case which is created in the previous step.
		- Programming language: Java and Selenium libraries have been used to provide test scripts.
		- Issues which is made automated are the process of Add, Remove, Select and Clearing of Todo list items.
		- Jmeter tool has been used for making a simple Load testing on HTTP by creating a few virtual HTTP connections and is saved as JMX.

** Test closure and result: **
	    1- There is no limitation for adding items
		2- I can add only numbers as a task
		3- I can add only signs
		4- The number of characters is not limited.
		5- It is possible to add a duplicate task to the list.
		6- Add, edit, delete is working correctly.
	
	- For this page, there is no big transaction with the host but the load test result was acceptable.
 
## Test execution log

---------------- Test execution 17-10-2021 20:29:47--------------- 
2021-10-17T20:29:50.604744100: Page loaded successfully!! (PASSED) 
2021-10-17T20:29:53.744003500: Page title value is correct. (PASSED) 
2021-10-17T20:29:53.756200300: Current URL is: https://todomvc.com/examples/angular2/ (PASSED) 
2021-10-17T20:29:53.826688800: TODOS exists on the page. (PASSED) 
2021-10-17T20:29:54.404257100: Add new task scenario has done successfully, and (3) items added. (PASSED) 
2021-10-17T20:29:54.404257100: Add empty task scenario has been done successfully, and an empty item was not added. (PASSED) 
2021-10-17T20:29:56.859193800: Edit task scenario has been done successfully. (PASSED) 
2021-10-17T20:29:57.641402900: Delete task scenario has been done successfully, and (2) items have remained. (PASSED) 
2021-10-17T20:29:59.786779700: Delete Multiple tasks scenario has been done successfully, and (0) items have remained. (PASSED) 
2021-10-17T20:30:00.102928100: There are no limitations for the number of entered characters and (1) items added. (Need a review) 
2021-10-17T20:30:00.238781600: It is possible to add numbers only, and (2) items added. (Need a review) 
2021-10-17T20:30:00.395855300: It is possible to add signs only, and (3) items added. (Need a review) 
2021-10-17T20:30:00.395855300: Negative Adding a task scenario has been done successfully and need a review. (NOT PASSED) 
2021-10-17T20:30:02.207906400: Negative Edit task with empty text scenario has been done successfully. (PASSED) 
2021-10-17T20:30:03.414152100: Clear completed button link is shown correctly. (PASSED) 
2021-10-17T20:30:18.935945400: 100 tasks have been added successfully. (PASSED) 

## Project name: 
Todos list load testing Project

## Description: 
Test the performance of the TODOS web app. https://todomvc.com/examples/angular2/ would be tested in this project.

## This test has been designed using the JMeter tool

	- The number of sample users is 200 users.
	- The 200 requests have been sent to the host for load testing, and the report is saved in the "LOAD TESTING REPORT.csv" file.
