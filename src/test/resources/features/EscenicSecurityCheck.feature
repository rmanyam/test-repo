@advance
Feature: Checking for Escenic access through the site

Scenario Outline:  Ping the Escenic admin page and verify that it is not available
Given I have the HTTP Request
When I make a get request to the URL <environment>
Then I should get the response code as "404"     
 
 Examples:
 
  |environment|
  |"http://www.thetimes.co.uk/escenic-admin"|
  |"http://www.staging-thetimes.co.uk/escenic-admin"|
  |"http://www.uat-thetimes.co.uk/escenic-admin"|
  |"http://ttosi.se.newsint.co.uk//escenic-admin"|