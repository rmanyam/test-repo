@Acceptance @WorldCupHub
Feature: World Cup Hub Article Type Precedence

Scenario Outline: Testing Article Video precedence test this one
Given World Cup Hub Section exists
And a Section named "world cup hub test 1" exists underneath the WCH Section
And an Article exists under the WCH Section
And many different asset types exist under the WCH Article, including <articleType>
When I make a request for the WCH Article
And the classification value is <articleType>

  Examples:
    | articleType	|
    | "Video"		|
    | "Slideshow"	|
    | "Interactive" |
    | "Podcast"		|
    | "Image"		|