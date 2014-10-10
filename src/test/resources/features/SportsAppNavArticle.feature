@Acceptance @SportsAppNav
Feature: Sports Application Navigation Article

Scenario: Section has Article under it
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an Article exists under the Section
When I make a request for the Section
Then I get a Section response with the correct mandatory fields
And the Section contains the Article entry with the correct fields

Scenario: Section links to Article correctly
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an Article exists under the Section
When I make a request for the Section
And I use the Article URL returned to make a request for the Article
Then I get the Article response with the correct mandatory fields
And the Article contains correct fields

Scenario: Link to Article directly
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an Article exists under the Section
When I make a request for the Article
Then I get the Article response with the correct mandatory fields
And the Article contains correct fields

Scenario: Section has 25 Articles in grid
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And 25 Articles exist under the Section
When I make a request for the Section
Then there are 25 Article entries in the Section response

Scenario: Requesting Unpublished Article
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an unpublished Article exists under the Section
When I make a request for the Article
Then I get a 204 response

Scenario: Unpublished Article in Section
Given Times Sports Navigation Section exists
And a Section named "Automation 1 Sub Section 1" exists underneath it
And an unpublished Article exists under the Section
When I make a request for the Section
Then the Section does not have an entry for the Article

Scenario: Article with Cover Image should have standfirst and standfirst override
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an Article exists under the Section
And a Cover Image exists under the Article
When I make a request for the Article
Then I get the Article response with the correct mandatory fields

Scenario: Requesting an Article with a relation to another Article
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an Article exists under the Section
And the first Article is related to a second Article
When I make a request for the Article
Then there is no Article entry for that Article

Scenario: Requesting an Article with a relation to another Article without a Section
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an Article exists under the Section
And a second Article exists without a Section
And the first Article is related to a second Article
When I make a request for the Section
And there is no Article entry for the second Article