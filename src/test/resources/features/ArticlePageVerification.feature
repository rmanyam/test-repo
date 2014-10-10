Feature: Article Verification Test
Scenario Outline: Open the article and verify its content
   Given I have the article URL "http://www.uat-thetimes.co.uk/tto/business/article3306064.ece"
   Then I open the article on <browser>
   Then The article should have "Simple Article Test" as headline
   Then The article should have "StandFirst for Automation" as standfirst
   Then The article should have "API Author" as author
   Then The article should have last Updated date
   Then I close the article 
   
   Examples:
    | browser  | 
    | "chrome" | 
    | "firefox"|  