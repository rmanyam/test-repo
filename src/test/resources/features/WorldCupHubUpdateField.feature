@Acceptance @WorldCupHub
Feature: World Cup Hub Updated field

Scenario: Follow Article URL link to a readingPane response with image list
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And I update the WCH article headline to "this has been updated!"
When I make a request for the World Cup Hub feed
Then the update field has been updated to the current time
When I make a request for the WCH Article
Then the update field has been updated to the current time