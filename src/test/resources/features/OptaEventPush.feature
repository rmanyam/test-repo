 @Hello
Feature: Publishing Opta Events 

Scenario Outline: Trying to do a GET      
 Given I have the HTTP Request
 And I set Http Request content type as application/xml
 When I Post an xml from <fileName> To endpoint "http://ni-epl-fe-ElasticL-RW58BAO8DVCN-868806928.eu-west-1.elb.amazonaws.com/commentaryservice/matchevents"
 Then I get a http success code 
 
  Examples:
    | fileName  | 
    | "redcard.xml"|
    
   
   