@Acceptance @SportsAppNav
Feature: Sports Application Navigation Updated field

Scenario: A change to an Article triggers the Updated Field to update in  the related Manifest, Section, Sub-Section, Article
Given Times Sports Navigation Section exists
And a Section named "Update Test" exists underneath it
And a Sub-Section named "Update SubSection Test" exists underneath it
And an Article exists under the Section
And I update the article headline to "this has been updated!"
When I make a request for the Sports Navigation Manifest feed
Then the update field has been updated to the current time
When I make a request for the Section
Then the update field has been updated to the current time
When I make a request for the Sub-Section
Then the update field has been updated to the current time
When I make a request for the Article
Then the update field has been updated to the current time

Scenario: A change to an unpublished Article does not trigger Updated field to change
Given Times Sports Navigation Section exists
And a Section named "Update Test" exists underneath it
And a Sub-Section named "Update SubSection Test 2" exists underneath it
And an unpublished Article exists under the Section
And I update the unpublished article headline to "this has been updated!"
When I make a request for the Sub-Section
Then the update field has not been updated to the current time

Scenario: A change to an Article Image triggers the Updated Field to update in  the related Manifest, Section, Sub-Section, Article and Image
Given Times Sports Navigation Section exists
And a Section named "Update Test" exists underneath it
And a Sub-Section named "Update SubSection Test" exists underneath it
And an Article exists under the Section
And an Image exists under the Article
And I update the Image description to "this has been updated!"
When I make a request for the Sports Navigation Manifest feed
Then the update field has been updated to the current time
When I make a request for the Section
Then the update field has been updated to the current time
When I make a request for the Sub-Section
Then the update field has been updated to the current time
When I make a request for the Article
Then the update field has been updated to the current time
When I make a request for the Image
Then the update field has been updated to the current time
