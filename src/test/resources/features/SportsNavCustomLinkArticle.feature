@Acceptance @SportsAppNav
Feature: Sports Application Navigation CustomLink Article

Scenario: Requesting a Section with a CustomLink Article entry
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a CustomLink Article exists under the Section
When I make a request for the Section
Then I get a Section response with the correct mandatory fields
And the Section contains the "CustomLink" Article entry with the correct fields

Scenario: Section links to CustomLink Article correctly
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a CustomLink Article exists under the Section
When I make a request for the Section
And I use the Article URL returned to make a request for the Article
Then I get the CustomLink Article response with the correct mandatory fields
And the CustomLink Article contains correct fields

Scenario: Link to CustomLink Article directly
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a CustomLink Article exists under the Section
When I make a request for the Article
Then I get the CustomLink Article response with the correct mandatory fields
And the CustomLink Article contains correct fields

Scenario: Requesting Unpublished CustomLink Article
Given Times Sports Navigation Section exists
And a Section named "Automation 1 Sub Section 1" exists underneath it
And an unpublished CustomLink Article exists under the Section
When I make a request for the Article
Then I get a 204 response

Scenario: Unpublished CustomLink Article in Section
Given Times Sports Navigation Section exists
And a Section named "Automation 1 Sub Section 1" exists underneath it
And an unpublished CustomLink Article exists under the Section
When I make a request for the Section
Then the Section does not have an entry for the Article

Scenario: Requesting an Article with a relation to another Article
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a CustomLink Article exists under the Section
And the CustomLink Article has a relatation to another Article
When I make a request for the Article
Then there is no Article entry for that Article

Scenario: Requesting an Article with Images
Given Times Sports Navigation Section exists
And a Section named "Automation 3 published" exists underneath it
And a CustomLink Article exists under the Section
And there are 3 Images attached to the Article
When I make a request for the Article
Then there are 3 Image Entries in the Article response
