Feature: Add a new customer
  In order to manage my customer list
  As an app user
  I want to be able to add a new customer from the main screen

  Scenario: Successfully add a customer
    Given I launch the application
    And I am on the customer list
    When I click on the "Add a customer" button
    And I enter the name "John Doe"
    And I enter the email "frank@example.com"
    And I submit the form
    Then I should see "John Doe" in the customer list
    And the list should contain 6 customers
    And I should see a toast saying "Customer added successfully"
