@Acceptance @WorldCupHub
Feature: World Cup Hub Feed

Scenario: Section has Article under it
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
When I make a request for the World Cup Hub feed
Then I get the main WCH response with the correct mandatory fields
And the main WCH response contains the Article entry with the correct fields
And the layout value is valid

Scenario: Section links to Article correctly
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
When I make a request for the World Cup Hub feed
And I use the Article URL returned in the WCH to make a request for the Article
Then I get the WCH Article response with the correct mandatory fields
And the WCH Article contains correct fields

Scenario: Link to Article directly
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
When I make a request for the WCH Article
Then I get the WCH Article response with the correct mandatory fields
And the WCH Article contains correct fields

Scenario: Section has 2 Articles in grid 
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And 2 WCH Articles exist under the Section
When I make a request for the World Cup Hub feed
Then there are 2 WCH Article entries in the Section response

Scenario: Requesting Unpublished Article
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an unpublished WCH Article exists under the Section
When I make a request for the WCH Article
Then I get a 204 status code from the WCH response

Scenario: Unpublished Article in Section
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an unpublished WCH Article exists under the Section
When I make a request for the World Cup Hub feed
And the main WCH response does not have an entry for the Article

Scenario: Article with Cover Image should have standfirst and standfirst override
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And a Cover Image exists under the WCH Article
When I make a request for the WCH Article
Then I get the WCH Article response with the correct mandatory fields

Scenario: Requesting an Article with a relation to another Article
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And the first WCH Article is related to a second WCH Article
When I make a request for the WCH Article
Then there is no Article entry for the second WCH Article