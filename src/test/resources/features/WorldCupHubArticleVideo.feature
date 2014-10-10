@Acceptance @WorldCupHub
Feature: World Cup Hub Article Video

Scenario: Requesting Article with Video attached
Given World Cup Hub Section exists
And a Section named "world cup hub test 1" exists underneath the WCH Section
And an Article exists under the WCH Section
And a Video exists under the WCH Article
When I make a request for the WCH Article
Then I get the WCH Article response with the correct mandatory fields
And the WCH article contains the Video entry

Scenario: Following Video URL
Given World Cup Hub Section exists
And a Section named "world cup hub test 1" exists underneath the WCH Section
And an Article exists under the WCH Section
And a Video exists under the WCH Article
When I make a request for the WCH Article
And I use the Video URL returned to make a request for the WCH Video
And I get a WCH Video response contains correct fields

Scenario: Requesting Article with deleted Video
Given World Cup Hub Section exists
And a Section named "world cup hub test 1" exists underneath the WCH Section
And an Article exists under the WCH Section
And an unpublished Video exists under the WCH Article
When I make a request for the WCH Article
Then I get the WCH Article response with the correct mandatory fields
And there is no Video entry for the unpublished WCH Video

Scenario: Requesting deleted Video
Given World Cup Hub Section exists
And a Section named "world cup hub test 1" exists underneath the WCH Section
And an Article exists under the WCH Section
And an unpublished Video exists under the WCH Article
When I make a request for the WCH Video
Then I get a 204 status code from the WCH response

Scenario: Requesting deleted Article with Video attached
Given World Cup Hub Section exists
And a Section named "world cup hub test 1" exists underneath the WCH Section
And an unpublished WCH Article with video exists under the Section
When I make a request for the WCH Article
Then I get a 204 status code from the WCH response