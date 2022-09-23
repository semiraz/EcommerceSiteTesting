Feature: Error Validation

  @ErrorValidation
  Scenario Outline: Negative Test for Login Validation
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

   Examples:
     | name           | password |
     | haha@gmail.com |5555555   |