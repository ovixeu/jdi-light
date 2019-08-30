@textfield
Feature: TextField

  Scenario: sendKeys test
    Given I open "Html5 Page"
    When I Send keys "Lorem" to "Name"
    Then the "Name" text equals to "Lorem"

  Scenario: setText test
    Given I open "Html5 Page"
    When I Set text "Lorem" in "Name"
    Then the "Name" text equals to "Lorem"

  Scenario: clear test
    Given I open "Html5 Page"
    And I Set text "Lorem" in "Name"
    When I Clear "Name"
    Then the "Name" text equals to ""

  Scenario: input test
    Given I open "Html5 Page"
    When I Input "Lorem" in "Name"
    Then the "Name" text equals to "Lorem"

  Scenario: placeholder test
    Given I open "Html5 Page"
    Then the "Name" placeholder equals to "Input name"

  Scenario: getText test
    Given I open "Html5 Page"
    And I Set text "Lorem" in "Name"
    Then the "Name" text equals to "Lorem"

  Scenario: base validation test
    Given I open "Html5 Page"
    Then the "Name" is basically valid