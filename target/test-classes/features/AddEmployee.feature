Feature: Add Employee

  Background:
    And user is logged in with valid admin credentials
    When user clicks on PIM option
    And user clicks on Add Employee button

  @1027
  Scenario: first scenario of adding the employee
    And user enters firstname middlename and lastname
    And user clicks on save button
    Then employee added successfully

    @1027
    Scenario: second scenario of adding the employee
      And user enters firstname middlename and lastname
      When user deletes employee id
      And user clicks on save button
      Then employee added successfully

  @1027
  Scenario: third scenario of adding the employee
    And user enters firstname middlename and lastname
    And user selects checkbox
    When user enters username password and confirmpassword
    And user clicks on save button
    Then employee added successfully

  @1028
  Scenario: adding an employee from feature file
    And user enters "Blake321" "Edward99" and "Melton4734"
    And user clicks on save button
    Then employee added successfully

  @1029
  Scenario Outline: adding an employee from feature file
    And user enters "<firstName>" "<middleName>" and "<lastName>" for an employee
    And user clicks on save button
    Then employee added successfully
    Examples:
    |firstName|middleName|lastName|
    |Blake    |Edward    |Melton3 |
    |Roger    |Randal    |Ricky   |
    |Johnny   |James     |Jingle  |

    @10291
    Scenario: adding an employee using data table
      When I add multiple employees and verify them that user has been added successfully
        |firstName|middleName|lastName|
        |Blake    |Edward    |Melton3 |
        |Roger    |Randal    |Ricky   |
        |Johnny   |James     |Jingle  |
        |Blake515 |Edward    |Melton1353 |
        |Roger13  |Randal    |Ricky135   |
        |Johnny135|James     |Jingle135  |

      @excel
      Scenario: Adding an employee from excel file
        When user adds multiple employees from excel file using "EmployeeData" sheet and verify the added employee

