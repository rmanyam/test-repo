Feature: Menu Colour verification on Time home page
 
Scenario: Click on the main menus and verify colour of text and menu highlights
Given User is on Times home page
When I click on Main menu News
Then the colour code of Menu should be #666666
And Colour code of test Menu text should be #666666

When I click on Main menu Opinion
Then the colour code of Menu should be #850029
And Colour code of test Menu text should be #850029

When I click on Main menu Business
Then the colour code of Menu should be #112244
And Colour code of test Menu text should be #112244

When I click on Main menu Money
Then the colour code of Menu should be #007469
And Colour code of test Menu text should be #007469

When I click on Main menu Sport
Then the colour code of Menu should be #0E6100
And Colour code of test Menu text should be #0E6100

When I click on Main menu Life
Then the colour code of Menu should be #733171
And Colour code of test Menu text should be #733171

When I click on Main menu Arts
Then the colour code of Menu should be #BB1A00
And Colour code of test Menu text should be #BB1A00

When I click on Main menu Puzzles
Then the colour code of Menu should be #F66200
And Colour code of test Menu text should be #F66200

When I click on Main menu Papers
Then the colour code of Menu should be #000000
And Colour code of test Menu text should be #000000