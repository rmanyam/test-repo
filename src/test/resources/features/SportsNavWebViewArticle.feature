@Acceptance @SportsAppNav
Feature: Sports Application Navigation WebView Article

Scenario: Requesting a Section with a WebView Article entry
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a WebView Article exists under the Section
When I make a request for the Section
Then I get a Section response with the correct mandatory fields
And the Section contains the "WebView" Article entry with the correct fields

Scenario: Section links to WebView Article correctly
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a WebView Article exists under the Section
When I make a request for the Section
And I use the Article URL returned to make a request for the Article
Then I get the WebView Article response with the correct mandatory fields
And the WebView Article contains correct fields

Scenario: Link to WebView Article directly
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a WebView Article exists under the Section
When I make a request for the Article
Then I get the WebView Article response with the correct mandatory fields
And the WebView Article contains correct fields

Scenario: Requesting Unpublished WebView Article
Given Times Sports Navigation Section exists
And a Section named "Automation 1 Sub Section 1" exists underneath it
And an unpublished WebView Article exists under the Section
When I make a request for the Article
Then I get a 204 response

Scenario: Unpublished WebView Article in Section
Given Times Sports Navigation Section exists
And a Section named "Automation 1 Sub Section 1" exists underneath it
And an unpublished WebView Article exists under the Section
When I make a request for the Section
Then the Section does not have an entry for the Article

Scenario: Requesting an Article with a relation to another Article
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a WebView Article exists under the Section
And the WebView Article has a relatation to another Article
When I make a request for the Article
Then there is no Article entry for that Article

Scenario: Requesting an Article with Images
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a WebView Article exists under the Section
And there are 3 Images attached to the Article
When I make a request for the Article
Then there are no Image Entries in the Article response
