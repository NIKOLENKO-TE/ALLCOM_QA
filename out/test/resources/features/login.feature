##login.feature
Feature: {Login Client}
  Scenario: [CLIENT_CHECKED logs in with valid credentials]
    Given User open HOME_PAGE and choose language to EN
    And User is redirected on Home Page?
    And User clicks on LogIn link
    And User enters valid CLIENT_CHECKED username and password
    And User clicks on Log In button
    And User is redirected on Home Page?
    Then User is Logged in?
    And User close the browser
  Scenario: [ADMIN logs in]
    Given User open HOME_PAGE and choose language to EN
    And User clicks on LogIn link
    And User enters valid ADMIN username and password
    And User clicks on Log In button
    And User is redirected on Home Page?
    Then User is Logged in?
    And User close the browser
  Scenario: [ADMIN logs in and logs out]
    Given User open HOME_PAGE and choose language to EN
    And User clicks on LogIn link
    And User enters valid ADMIN username and password
    And User clicks on Log In button
    And User is redirected on Home Page?
    Then User is Logged in?
    Then User logged out
    Then User is Logged out?
    And User close the browser
  Scenario: [CLIENT_UNCHECKED trying to Login with unchecked account]
    Given User open HOME_PAGE and choose language to EN
    And User clicks on LogIn link
    And User enters valid CLIENT_UNCHECKED username and password
    And User clicks on Log In button
    Then User is Logged out?
    And User close the browser
  Scenario: [CLIENT_BLOCKED trying to Login with blocked account]
    Given User open HOME_PAGE and choose language to EN
    And User clicks on LogIn link
    And User enters valid CLIENT_BLOCKED username and password
    And User clicks on Log In button
    Then User is Logged out?
    And User close the browser
  Scenario: [CLIENT_NON_EXISTING trying to Login]
    Given User open HOME_PAGE and choose language to EN
    And User clicks on LogIn link
    And User enters valid CLIENT_NON_EXISTING username and password
    And User clicks on Log In button
    Then User is Logged out?
    And User close the browser