@Acceptance @WorldCupHub
Feature: World Cup Hub Article with Image

Scenario: Section has Article with Image under it
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And an Image exists under the WCH Article
When I make a request for the World Cup Hub feed
Then I get the main WCH response with the correct mandatory fields
And the main WCH response contains the Article entry with the correct fields

Scenario: Link to Article with Image directly
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And an Image exists under the WCH Article
When I make a request for the WCH Article
Then I get the WCH Article response with the correct mandatory fields
And the WCH article contains the Image entry

Scenario: Follow Image entry Link to Image
Given World Cup Hub Section exists
And a Section named "worldcuphubtest1" exists underneath the WCH Section
And an Article exists under the WCH Section
And an Image exists under the WCH Article
When I make a request for the WCH Article
And I follow the link to the WCH image
Then I get a WCH image response with the correct fields
And there is version "original" in the image list