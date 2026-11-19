@login
Feature: SauceDemo login
 Background:
 Given the user is on the SauceDemo login page
 @smoke @positive
 Scenario: Successful login with valid credentials
 When the user logs in with username "standard_user" and password "secret_sauce"
 Then the user should land on the products page
 @negative
 Scenario: Login fails with an invalid password
 When the user logs in with username "standard_user" and password "wrong_password"
 Then the error message should be "Epic sadface: Username and password do not match any user in this service"
 @negative
 Scenario: Login fails for a locked out user
 When the user logs in with username "locked_out_user" and password "secret_sauce"
 Then the error message should be "Epic sadface: Sorry, this user has been locked out."
 @negative
 Scenario: Login fails when username is empty
 When the user logs in with username "" and password "secret_sauce"
 Then the error message should be "Epic sadface: Username is required"
 @negative
 Scenario: Login fails when password is empty
 When the user logs in with username "standard_user" and password ""
 Then the error message should be "Epic sadface: Password is required"
  @negative
  Scenario Outline: Login fails with invalid credential combinations
    When the user logs in with username "<username>" and password "<password>"
    Then the error message should be "<expected_error>"

    Examples:
      | username      | password     | expected_error                                                            |
      | invalid_user  | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      | standard_user | 12345        | Epic sadface: Username and password do not match any user in this service |
      |               |              | Epic sadface: Username is required                                        |