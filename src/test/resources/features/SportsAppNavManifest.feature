@Acceptance @SportsAppNav
Feature: Sports Application Navigation Manifest

Scenario: Sports Navigation Manifest Feed lists available Sections
Given Times Sports Navigation Section exists
And a Section named "Automation 1 published" exists underneath it
When I make a request for the Sports Navigation Manifest feed
Then I get the Sports Navigation Manifest response with the correct mandatory fields
And the Section list contains the Section with the correct values

Scenario: The Section URL in the Sports Navigation Feed links to the Section
Given Times Sports Navigation Section exists
And a Section named "Automation 1 published" exists underneath it
When I make a request for the Sports Navigation Manifest feed
And I use the Section URL returned to make a request for the Section
Then I get a Section response with the correct mandatory fields
And the Section has the correct values

Scenario: Sports Navigation Manifest feed lists Sub-Sections
Given Times Sports Navigation Section exists
And a Section named "Automation 1 published" exists underneath it
And a Sub-Section named "Automation 1 Sub Section 1" exists underneath it
When I make a request for the Sports Navigation Manifest feed
Then I get the Sports Navigation Manifest response with the correct mandatory fields
And the Section list contains the Section with the correct values
And the Section list contains the Sub-Section with the correct values

Scenario: The Sub-Section URL in the Sports Navigation feed links to the Sub-Section
Given Times Sports Navigation Section exists
And a Section named "Automation 1 published" exists underneath it
And a Sub-Section named "Automation 1 Sub Section 1" exists underneath it
When I make a request for the Sports Navigation Manifest feed
And I use the Sub-Section URL returned to make a request for the Sub-Section
Then I get a Section response for the Sub-Section with the correct mandatory fields
And the Sub-Section has the correct values

Scenario: Priority of Sections - Sections should be ordered in the Manifest feed by their Section Priority value
Given Times Sports Navigation Section exists
And a Section named "Automation 1 published" with Priority of 1 exists underneath it
And a Section named "Automation 3 published" with Priority of 10 exists underneath it
When I make a request for the Sports Navigation Manifest feed
Then I get the Sports Navigation Manifest response with the correct mandatory fields
And the Section "Automation 1 published" is above the Section "Automation 3 published" in the response

Scenario: Priority of Sub-Sections - Sub-Sections should be ordered in the Manifest feed by their Section Priority value
Given Times Sports Navigation Section exists
And a Section named "Automation 1 published" with Priority of 1 exists underneath it
And a Sub-Section named "Automation 1 Sub Section 1" with Priority 20 exists underneath the Section
And a Sub-Section named "Automation 1 Sub Section 2" with Priority 30 exists underneath the Section
When I make a request for the Sports Navigation Manifest feed
Then I get the Sports Navigation Manifest response with the correct mandatory fields
And the Section "Automation 1 Sub Section 1" is above the Section "Automation 1 Sub Section 2" in the response
And the Section "Automation 1 published" is above the Section "Automation 1 Sub Section 1" in the response

#Scenario: Sports Navigation doesn't exist
#Given no Times Sports Navigation section exists
#When I make a request for the Sports Navigation Manifest feed
#Then I get a 204 response

Scenario: Requesting a Section that does not exist
When I request for a Section that does not exist
Then I get a 204 response

Scenario: Request a Section that is unpublished
Given Times Sports Navigation Section exists
And a Section named "Automation 1 published" exists underneath it
And a Sub-Section named "Automation 1 Sub Section 3 Unpublished" exists underneath which is unpublished
When I make a request for the Sub-Section 
Then I get a 204 response
