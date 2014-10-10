@search

Feature: HomePage Functional Verifications

  Story:
  As a user
  I want to use search functionality
  So that I can

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

