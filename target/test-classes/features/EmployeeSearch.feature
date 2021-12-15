Feature: Search employee

  Scenario: search employee by id
    And user is logged in with valid admin credentials
    And user is navigated to employee list page
    When user enters valid employee id
    And clicks on search button
    Then user see employee information is displayed

  Scenario: search employee by name
    And user is logged in with valid admin credentials
    And user is navigated to employee list page
    When user enters valid employee name
    And clicks on search button
    Then user see employee information is displayed



