@SharingUrlServiceTest
Feature: Sharing URL 
	Story: 	As a user
			I want a url
			So that I can share articles and online content with friends
			
Scenario: newly created article sharing url works
	Given I create a new article for sharing
	When I access the sharing url
	Then accessing the sharing url returns successfully





