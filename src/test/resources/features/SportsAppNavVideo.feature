@Acceptance @SportsAppNav
Feature: Sports Application Navigation Video

Scenario: Requesting Article with Video attached
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an Article exists under the Section
And a Video exists under the Article
When I make a request for the Article
Then I get the Article response with the correct mandatory fields
And the Article contains correct fields
And the Article has the Video entry with the correct fields

Scenario: Following Video URL
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an Article exists under the Section
And a Video exists under the Article
When I make a request for the Article
And I use the Video URL returned to make a request for the Video
And I get a Video response contains correct fields

Scenario: Requesting Article with deleted Video
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an Article exists under the Section
And an unpublished Video exists under the Article
When I make a request for the Article
Then I get the Article response with the correct mandatory fields
And there is no Video entry for the unpublished Video

Scenario: Following Video URL
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an Article exists under the Section
And an Image exists under the Article
And a Video exists under the Article
When I make a request for the Video
Then there are 1 Image Entries in the Article response

Scenario: Requesting deleted Video
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an Article exists under the Section
And an unpublished Video exists under the Article
When I make a request for the Video
Then I get a 204 response

Scenario: Requesting deleted Article with Video attached
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an unpublished Article exists under the Section
And a Video exists under the Article
When I make a request for the Article
Then I get a 204 response