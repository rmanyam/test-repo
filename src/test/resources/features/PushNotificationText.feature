@pushnotification
Feature: Sport App - Push notification deep linking

  Scenario Outline: 
    Given I have a <articleType> article with articleId <articleId>
    When I update the article Push notification text to "Test push notification text" and Send push notification to Times Sports app "true"
    And I request for the Article
    Then the pushNotificationText fields populated with "Test push notification text" and ifSendPushNotification populated with "true"

  Examples:
    | articleType | articleId |
    | standard    | 3245695   |
    | custom_html | 3246060   |
    | live_match  | 3246061   |
    | custom_link | 3246063   |
    | columnist   | 3246064   |

  Scenario Outline:
    Given I have a <articleType> article with articleId <articleId>
    When I update the article Push notification text to "<pushNotificationText>" and Send push notification to Times Sports app "<sendPushNotification>"
    And I request for the Article
    Then the pushNotificationText field should be empty and ifSendPushNotification populated with "false"

  Examples:
    | articleType | articleId | pushNotificationText | sendPushNotification |
    | standard    | 3245695   | Standard Art Push    | false                |
    | standard    | 3245695   |                      |                      |
    | custom_html | 3246060   | Custom HTML Push     | false                |
    | custom_html | 3246060   |                      |                      |
    | live_match  | 3246061   | Live Match Push      | false                |
    | live_match  | 3246061   |                      |                      |
    | custom_link | 3246063   | Custom Link Push     | false                |
    | custom_link | 3246063   |                      |                      |
    | columnist   | 3246064   | Columnist Push       | false                |
    | columnist   | 3246064   |                      |                      |