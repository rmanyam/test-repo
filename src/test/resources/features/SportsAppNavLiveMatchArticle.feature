@Acceptance @SportsAppNav
Feature: Sports Application Navigation Live Match Article

Scenario: Section has Live Match Article under it
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a Live Match Article exists under the Section
When I make a request for the Section
Then I get a Section response with the correct mandatory fields
And the Section contains the Article entry with the correct fields

Scenario: Section links to Live Match Article correctly
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a Live Match Article exists under the Section
When I make a request for the Section
And I use the Article URL returned to make a request for the Article
Then I get the Live Match Article response with the correct mandatory fields
And the Live Match Article contains correct fields

Scenario: Link to Live Match Article directly
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a Live Match Article exists under the Section
When I make a request for the Article
Then I get the Live Match Article response with the correct mandatory fields
And the Live Match Article contains correct fields

Scenario: Requesting Unpublished Live Match Article
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And an unpublished Live Match Article exists under the Section
When I make a request for the Article
Then I get a 204 response

Scenario: Unpublished Live Match Article in Section
Given Times Sports Navigation Section exists
And a Section named "Automation 1 Sub Section 1" exists underneath it
And an unpublished Live Match Article exists under the Section
When I make a request for the Section
Then the Section does not have an entry for the Article

Scenario: Requesting Live Match Article with Cover Image
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a Live Match Article exists under the Section
And a Cover Image exists under the Article
When I make a request for the Article
Then I get the Article response with Cover Image