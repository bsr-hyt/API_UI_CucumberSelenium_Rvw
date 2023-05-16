Feature: Sign up

  @wip
  Scenario Outline: Exlab user register
    When The user create a POST request with "<name>"and "<email>" and "<password>" and "<about>" and "<terms>"
    Then The user verifies that the status code is <statusCode>
    And The user verifies that body contains "<content>"
    And The compiler gets the token
    Examples:
      | name | email | password | about | terms | statusCode | content |
      | name | email | password | about | terms | 200        | content |
