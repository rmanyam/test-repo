@regression

Feature: HomePage Functional Verifications

	Story:
		As a user
		I want to be able to validate all the components available on the Times home page
		So that I can make sure all the components are intact on the times home page
	
	Background:
	
		Given I am on home page
		 
		Scenario: verifying all components on the Times home page
		
		When I click on the THE TIMES tab on the top tab 
		Then I should be redirected to The Time home page
		
		When I click on the THE SUNDAY TIMES tab on the top tab
		Then I should be redirected to The Sunday Times website
		
		When I click on the TIMES Plus tab on the top tab 
		Then I should be redirected to TIMES PLUS Website
		
		When I click on the Login link
		Then Login window should appear
		When I provide valid login credentials and clicks on the Submit button
		Then user should logged into website 
		
		When user enters incorrect login credentials and clicks on the Submit button
		Then Error message should be shown to the user
		
		When user clicks on the Subscribe link 
		Then User should be redirected to subscription page
		
		When user clicks on the Contact US Link
		Then CONTACT US window appears underneath top menu
		
		