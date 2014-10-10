@Acceptance @ImageVersionService 
Feature: Image Version Service

Scenario: Create an image in Escenic and check generated image versions
Given a new large image is created in Escenic from Eidos Methode
When I look at the image details
Then the image has 1 image versions

Scenario: Create an image in Escenic and check generated image versions
Given a new large image is created in Escenic with source as "WebserviceImport"
When I look at the image details
Then the image has 1 image versions