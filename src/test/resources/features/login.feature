Feature: Login
  Scenario: User logs in with valid credentials
    Given User launches the browser
    When User opens ALLCOM Home Page
    And User clicks on LogIn link
    And User enters valid username and password
    And User clicks on Log In button
    Then User is still on Login Page
