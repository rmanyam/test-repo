@Acceptance @WorldCupHub
Feature: World Cup Hub Article with Interactive

Scenario: Section has Article with Interactive
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And Interactive exists under the WCH Article
When I make a request for the World Cup Hub feed
Then I get the main WCH response with the correct mandatory fields
And the main WCH response contains the Article entry with the correct fields

Scenario: Link to directly Article with Interactive
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And Interactive exists under the WCH Article
When I make a request for the WCH Article
Then I get the WCH Article response with the correct mandatory fields
And the WCH article with Podcast contains the correct fields

Scenario: Requesting Unpublished Article with Interactive
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an unpublished WCH Article with Interactive exists under the Section
When I make a request for the WCH Article
Then I get a 204 status code from the WCH response

Scenario: Unpublished Articlie with Interactive in Section
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an unpublished WCH Article with Interactive exists under the Section
When I make a request for the World Cup Hub feed
And the main WCH response does not have an entry for the Article