@Acceptance @WorldCupHub
Feature: World Cup Hub Article with Slideshow in seperate tab

Scenario: Section has Article with multiple images under it
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And 11 images exists under the WCH Article
And the Article has Display Slideshow In New Tab set
When I make a request for the World Cup Hub feed
Then I get the main WCH response with the correct mandatory fields
And the main WCH response contains the Article entry with the correct fields

Scenario: Link to directly Article with multiple images
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And 11 images exists under the WCH Article
And the Article has Display Slideshow In New Tab set
When I make a request for the WCH Article
Then I get the WCH Article response with the correct mandatory fields
And the WCH article contains the correct article url field

Scenario: Follow Article URL link to a readingPane response with image list
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And 11 images exists under the WCH Article
And the Article has Display Slideshow In New Tab set
When I make a request for the WCH Article
And I follow the article url link
Then I get a WCH readingPane response with the correct fields
And the first 10 images are present in the readPane response

Scenario: Section has Unpublished Slideshow in separate tab
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And 11 images exists under the WCH Article
And the unpublished Article has Display Slideshow In New Tab set
When I make a request for the WCH Article
Then I get a 204 status code from the WCH response

Scenario: Section has Article with Image under it
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And 11 images exists under the WCH Article
And the unpublished Article has Display Slideshow In New Tab set
When I make a request for the World Cup Hub feed
Then the main WCH response does not have an entry for the Article