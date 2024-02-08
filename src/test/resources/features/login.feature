Feature: Login Client
  Scenario: User logs in with valid credentials
    Given User launches the browser
    When User opens ALLCOM Home Page
    And User changes the language to English
    And User clicks on LogIn link
    And User enters valid CLIENT_CHECKED username and password
    And User clicks on Log In button
    Then User is redirected on Home Page?
  Scenario: Admin logs in with valid credentials
    Given User launches the browser
    When User opens ALLCOM Home Page
    And User changes the language to English
    And User clicks on LogIn link
    And User enters valid ADMIN username and password
    And User clicks on Log In button
    Then User is redirected on Home Page?
  Scenario: User logs out
    Given User launches the browser
    When User opens ALLCOM Home Page
    And User changes the language to English
    And User clicks on LogIn link
    And User enters valid ADMIN username and password
    And User clicks on Log In button
    And User is redirected on Home Page?
    Then User is logged in?
  Scenario: User trying to Login with unchecked account?
    Given User launches the browser
    When User opens ALLCOM Home Page
    And User changes the language to English
    And User clicks on LogIn link
    And User enters valid CLIENT_UNCHECKED username and password
    And User clicks on Log In button
    And User is redirected on Login Page?