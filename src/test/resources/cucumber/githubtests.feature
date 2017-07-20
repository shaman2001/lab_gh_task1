Feature: User profile change

  @SmokeTest
  Scenario:
    Given user navigates to own profile page
    When enters data in the bio text box
    And clicks Update profile button
    Then confirmation popup is displayed
    And user data is changed