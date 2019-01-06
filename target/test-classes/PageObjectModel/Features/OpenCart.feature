Feature: OpenCart

  Scenario: TC01 - Login
    Given I open Opencart Application
    And I login with invalid Email
    And I login with invalid Password
    When I login with Valid Username and password
    Then I log Off

  Scenario: TC02 - E2E Execution
    Given I open Opencart Application
    And I login with Valid Username and password
    And I enter Search text "Sony"
    And I click on the product and Add to Cart
    And Continue shopping
    And I enter Search text "Nikon"
    And I click on the product and Add to Cart
    When I Confirm order
    Then I log Off

  Scenario: TC03 - Remove Product from Basket
    Given I open Opencart Application
    And I login with Valid Username and password
    And I enter Search text "Sony"
    And I click on the product and Add to Cart
    When I remove Item from cart
    Then I log Off

  Scenario: TC04 - Order based on Quantity
    Given I open Opencart Application
    And I login with Valid Username and password
    And I enter Search text "Sony"
    And I click on the product and Add to Cart
    When I enter Quantity and update cart
    When I Confirm order
    Then I log Off

  Scenario: TC05 - Browse Category
    Given I open Opencart Application
    And I login with Valid Username and password
    And I select "Macs" from "Laptops & Notebooks" category
    And I select "PC" from "Desktops" category
    And I select "Monitors" from "Components" category
    Then I log Off

  Scenario: TC0 6 - Additional Images
    Given I open Opencart Application
    And I login with Valid Username and password
    And I enter Search text "Sony"
    And I click on the product
    And I verify product description is displayed
    Then I view additional Images for the item

#    Then I log Off
