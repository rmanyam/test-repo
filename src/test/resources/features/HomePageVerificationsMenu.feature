@uiregression

Feature: HomePage Functional Verifications

  Story:
  As a user
  I want to be able to navigate to Times UK Home Page
  So that I can make sure that environment is up

  Background:
    Given I am on home page

  Scenario: verifying all menus
    Then I should be able see all the menus as follows
      | News     |
      | Opinion  |
      | Business |
      | Money    |
      | Sport    |
      | Life     |
      | Arts     |
      | Puzzles  |
      | Papers   |

  Scenario Outline: navigating to all menus
    When I hover mouse pointer on menu "<menu item>"
    Then I the left panel heading should be "<leftPanelHeading>"
    And the middle panel heading should be "<midPanelHeading>"
    And the right panel heading should be "<rightPanelHeading>"

  Examples:
    | menu item | leftPanelHeading | midPanelHeading | rightPanelHeading                   |
    | News      | IN NEWS          | LATEST          | FEATURE                             |
    | Opinion   | IN OPINION       | OBITUARIES      | FEATURE ARTICLE IN NAVIGATION TITLE |
    | Business  | IN BUSINESS      | LATEST          | TEMPUS                              |
    | Money     | IN MONEY         | LATEST          | FEATURE                             |
    | Sport     | IN SPORT         | LATEST          |                                     |
    | Life      | IN LIFE          | LATEST          | FEATURE                             |
    | Arts      | IN ARTS          | LATEST          | FEATURE                             |
    | Puzzles   | IN PUZZLES       | LATEST          | FEATURE                             |
    | Papers    | IN PAPERS        | LATEST          | FEATURE                             |

  Scenario Outline: navigating to all menus items then to sections
    When I hover mouse pointer on menu "<menu item>"
    Then I should be able navigate to "<section>" page
    And I should see the logo
    And The page header should be "<header>"
    And The page title should be "<pageTitle>"

  Examples:
    | menu item | section           | header         | pageTitle                   |
    | News      | UK News           | UK News        | UK News \| The Times        |
    | Opinion   | Columnists        | Columnists     | Columnists \| The Times     |
    | Business  | Industries        | Industries     | Industries \| The Times     |
    | Money     | Investment        | Investment     | Investment \| The Times     |
    | Sport     | Cricket           | Cricket        | Cricket \| The Times        |
    | Life      | Court & Social    | Court & Social | Court & Social \| The Times |
    | Arts      | Stage             | Stage          | Stage \| The Times          |
    | Puzzles   | Su Doku           | Su Doku        | Su Doku \| The Times        |
    | Papers    | The Times Archive | Archive        | Archive \| The Times        |


  Scenario: Verify Date on Navigation bar
    Then I should see current date on the navigation bar

#    List View Test
  Scenario: Clicking on List View from the navigation bar should take the user to List View page
    When I click on List View icon
    Then I should see list of articles with article number, date & time and article title
    And I should see Filter headlines search box
    And I should see an ad on List View page

#SiteMap view Tests
  Scenario: Clicking on Site Map fro the navigation bar should take me to SiteMap page
    When I click on SiteMap icon
#    All Sections configured in MenuItem enum
    Then I should see all sections in SiteMap view page
    And I should see the corresponding sub-titles

  Scenario Outline: Navigate to Section from Site Map page
    When I click on SiteMap icon
    And I click on "<section>" section from site map page
    Then The page title should be "<title>"
  Examples:
    | section  | title                                        |
    | News     | The Times \| UK News, World News and Opinion |
    | Opinion  | Opinion \| The Times                         |
    | Business | Business \| The Times                        |
    | Life     | Life \| The Times                            |
    | Money    | Money \| The Times                           |
    | Sport    | Sport \| The Times                           |
    | Arts     | Arts \| The Times                            |
    | Puzzles  | Puzzles \| The Times                         |
    | Papers   | Papers \| The Times                          |

  Scenario Outline: Navigate to a sub-title from Site Map page
    When I click on SiteMap icon
    And I click on "<sub-title>" sub-title from site map page
    Then The page title should be "<pageTitle>"
    And The page header should be "<header>"
  Examples:
    | sub-title         | header         | pageTitle                   |
    | UK News           | UK News        | UK News \| The Times        |
    | Columnists        | Columnists     | Columnists \| The Times     |
    | Industries        | Industries     | Industries \| The Times     |
    | Investment        | Investment     | Investment \| The Times     |
    | Cricket           | Cricket        | Cricket \| The Times        |
    | Court & Social    | Court & Social | Court & Social \| The Times |
    | Stage             | Stage          | Stage \| The Times          |
    | Su Doku           | Su Doku        | Su Doku \| The Times        |
    | The Times Archive | Archive        | Archive \| The Times        |

# Need to include TLS, Blog pages etc
  Scenario Outline: Navigate to a sub-section from Site Map page
    When I click on SiteMap icon
    And I click on "<sub-section>" sub-section from site map page
    Then The page title should be "<pageTitle>"
    And The page header should be "<header>"
  Examples:
    | sub-section    | header         | pageTitle                   |
    | Scotland       | Scotland       | Scotland News \| The Times  |
    | India          | India          | India \| The Times          |
    | Wine           | Wine           | Wine \| The Times           |
    | County Cricket | County Cricket | County Cricket \| The Times |
    | Comedy         | Comedy         | Comedy \| The Times         |
    | Killer Su Doku | Killer Su Doku | Killer Su Doku \| The Times |

  Scenario: User should be able to search
    When I click on SiteMap icon
    Then I search for "de" on site map page
    And I should be able to find the search string "de" in all results returned

  Scenario: User should be able to navigate to section from search results
    When I click on SiteMap icon
    Then I search for "gardens" on site map page
    And I should be able to navigate to "Gardens" section by clicking on it
    And The page title should be "Gardens | The Times"
    And The page header should be "Gardens"


#    RSS Feeds option testing

  Scenario: User should be able to navigate to RSS Feeds section
    When I click on RSS Feeds icon
    Then The RSS Feeds header should be "News RSS Feed"
    And The RSS feeds are displayed correctly

  Scenario Outline: Selecting RSS Feeds from a section should take the user to that section's RSS Feeds
    When I hover mouse pointer on menu "<menu item>"
    And I should be able navigate to "<section>" page
    And I click on RSS Feeds icon
    Then The RSS Feeds header should be "<header>"
    And The RSS feeds are displayed correctly
  Examples:
    | menu item | section    | header              |
    | News      | UK News    | UK News RSS Feed    |
    | Opinion   | Columnists | Columnists RSS Feed |
    | Sport     | Cricket    | Cricket RSS Feed    |
