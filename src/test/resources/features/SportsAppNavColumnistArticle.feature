@Acceptance @SportsAppNav
Feature: Sports Application Navigation Columnist Article

Scenario: Requesting a Section with a Columnist Article entry
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a Columnist Article exists under the Section
When I make a request for the Section
Then I get a Section response with the correct mandatory fields
And the Section contains the "Columnist" Article entry with the correct fields

Scenario: Section links to Columnist Article correctly
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a Columnist Article exists under the Section
When I make a request for the Section
And I use the "Columnist" Article URL returned to make a request for the Article
Then I get the Article response with the correct mandatory fields
And the Columnist Article contains correct fields

Scenario: Link to Columnist Article directly
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a Columnist Article exists under the Section
When I make a request for the Article
Then I get the Article response with the correct mandatory fields
And the Columnist Article contains correct fields

Scenario: Requesting Unpublished Columnist Article
Given Times Sports Navigation Section exists
And a Section named "Automation 1 Sub Section 1" exists underneath it
And an unpublished Columnist Article exists under the Section
When I make a request for the Article
Then I get a 204 response

Scenario: Unpublished Columnist Article in Section
Given Times Sports Navigation Section exists
And a Section named "Automation 1 Sub Section 1" exists underneath it
And an unpublished Columnist Article exists under the Section
When I make a request for the Section
Then the Section does not have an entry for the Columnist Article

Scenario: Requesting an Article with a relation to another Article
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a Columnist Article exists under the Section
And the Columnist Article has a relatation to another Article
When I make a request for the Article
Then there is no Article entry for that Article

Scenario: Requesting an Article with Images
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a Columnist Article exists under the Section
And there are 3 Images attached to the Article
When I make a request for the Article
Then there are Image Entries for first 2 Images

Scenario: Requesting an Article with Images
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a Columnist Article exists under the Section
And there are 2 Images attached to the Article
When I make a request for Image number 1 from the Article
Then I get an Image Response and the Image version is "original"