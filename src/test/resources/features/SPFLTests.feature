Feature: Enable SPFL

  Scenario: Upload a SPFL Type 1 video with valid metadata
    Given I upload a Type1 Video through ooyala and populate the following valid metadata
    """
    {
     "posterURL": "http://footballspeak.com/PostImages/uploadedimage/2012_3_21_17_14.jpg",
     "clipType": "ingame_FirstHalfClip",
     "eventType": "goal",
     "title": "Test Title SPFL - 11-09-2014-1",
     "subTitle": "SubTitle",
     "matchId": "99879",
     "awayTeamDisplayName": "Dundee Utd",
     "awayTeamId": "t63",
     "awayTeamScore": "2",
     "awayTeamActiveTeam": 0,
     "homeTeamDisplayName": "Celtic",
     "homeTeamId": "t61",
     "homeTeamScore": "2",
     "homeTeamActiveTeam": 1,
     "eventElapsedTime": "34",
     "playerId": "5",
     "playerDisplayName": "SomeZ",
     "competitionid": "14",
     "brand": "TNL"
    }
    """
    When I open up the SPFL webapp
    Then The uploaded clip is displayed in webapp
    And Displays correct metadata (Title, upload time, team info etc...)
    And User can play the clip
    And The uploaded clip isn't get displayed on EPL webpage

  Scenario: Upload a SPFL Type 2 video with valid metadata
    Given I upload a Type2 Video through ooyala and populate the following valid metadata
    """
    {
     "posterURL": "http://bit.ly/1riuUko",
     "clipType": "internet_goaloftheweek",
     "title": "Test Title EPL - 23-09-2014",
     "subTitle": "internet_goaloftheweek",
     "matchId": "g99879",
     "competitionid": "14",
     "brand": "TNL"
    }
    """
    When I open up the SPFL webapp
    Then The uploaded clip is displayed in webapp
    And Displays correct metadata (Title, upload time, team info etc...)
    And User can play the clip
    And The uploaded clip isn't displayed on EPL webpage

  Scenario Outline: Upload a SPFL Type1 Video with different Clip Types
    Given I upload a Type1 video from ooyala
    When I populate Clip Type as "<clipType>"
    Then the json response displays correct "<clipType>"
    #    ( http://epl.uat-thetimes.co.uk/files/v3_spfl_videosMobile_latest.json)
  Examples:
    | clipType                         |
    | ingage_FirstHalfClip             |
    | ingage_FirstHalfClipAfter36Mins  |
    | ingage_HalfTimePackage           |
    | ingage_SecondHalfClip            |
    | ingage_SecondHalfClipAfter81Mins |
    | ingage_MatchPackage              |

  Scenario Outline: Upload a SPFL Type2 Video with different metadata
    Given I upload a Type2 video from ooyala
    When I populate Clip Type as "<clipType>"
    Then the json response displays correct "<clipType>"
#    ( http://epl.uat-thetimes.co.uk/files/v3_spfl_videosMobile_latest.json)
  Examples:
    | clipType                        |
    | internet_ClipsPackage           |
    | internet_AdditionalMatchContent |
    | internet_goaloftheday           |
    | internet_goaloftheweek          |
    | internet_goalofthemonth         |

  Scenario Outline: Upload a SPFL Type1 Video with different Event Types
    Given I upload a Type1 video from ooyala
    When I populate Clip Type as "<eventType>"
    Then the json response displays correct "<eventType>"
    And the web app displays the correct event type
  #    ( http://epl.uat-thetimes.co.uk/files/v3_spfl_videosMobile_latest.json)
  Examples:
    | eventType      |
    | goal           |
    | free kick lost |
    | offside        |
    | red card       |
    | miss           |
    | penalty won    |

  Scenario: Test blackout period Start time
#      Blackout periods : http://epl.uat-thetimes.co.uk/files/v3_blackout.json
    Given I set my system time to 23/08/2014 13:45
    When I open up the web app
    Then A message is displayed stating "The videos will appear in some time"

  Scenario: Test blackout period End time
#      Blackout periods : http://epl.uat-thetimes.co.uk/files/v3_blackout.json
    Given I set my system time to 23/08/2014 17:45
    When I open up the web app
    Then I should see all the videos