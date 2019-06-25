Feature: OpenCart

  @Login
  Scenario: TC01 - Login
    Given I open Opencart Application
    And I login with invalid Email
    When I login with invalid Password
    Then I login with Valid Username and password
    And I log Off

#  Scenario: TC02 - E2E Execution
#    Given I open Opencart Application
#    And I login with Valid Username and password
#    And I enter Search text "Sony"
#    And I click on the product and Add to Cart
#    And Continue shopping
#    And I enter Search text "Nikon"
#    When I click on the product and Add to Cart
#    Then I click on CheckOut
#    And I Confirm order
#    And I log Off
#
#  Scenario: TC03 - Remove Product from Basket
#    Given I open Opencart Application
#    And I login with Valid Username and password
#    And I enter Search text "Sony"
#    When I click on the product and Add to Cart
#    Then I remove Item from cart
#    And I log Off
#
#  Scenario: TC04 - Order based on Quantity
#    Given I open Opencart Application
#    And I login with Valid Username and password
#    And I enter Search text "Sony"
#    And I click on the product and Add to Cart
#    When I enter Quantity and update cart
#    Then I click on CheckOut
#    And I Confirm order
#    And I log Off
#
#  Scenario: TC05 - Browse Category
#    Given I open Opencart Application
#    And I login with Valid Username and password
#    And I select "Macs" from "Laptops & Notebooks" category
#    Then I select "PC" from "Desktops" category
#    When I select "Monitors" from "Components" category
#    And I log Off
#
#  Scenario: TC06 - Additional Images
#    Given I open Opencart Application
#    And I login with Valid Username and password
#    And I enter Search text "Sony"
#    And I click on the product
#    When I verify product description is displayed
#    Then I view additional Images for the item
#    And I log Off
#
#  Scenario: TC07 - Edit Account Informaiton
#    Given I open Opencart Application
#    And I login with Valid Username and password
#    When I click on Edit Account
#    Then I Edit the phone number 7688900001
#    And I log Off
#
#  Scenario: TC08 - Sort By Combo
#    Given I open Opencart Application
#    And I login with Valid Username and password
#    And I select "Show All Laptops & Notebooks" from "Laptops & Notebooks" category
#    When I Sort and verify using Name (A - Z)
#    Then I Sort and verify using Name (Z - A)
#    And I log Off
#
#  Scenario: TC09 - Shopping using Empty Cart
#    Given I open Opencart Application
#    And I login with Valid Username and password
#    When I click on CheckOut
#    Then I validate the error message
#    And I log Off
#
#  Scenario: TC10 - Change Address
#    Given I open Opencart Application
#    And I login with Valid Username and password
#    And I enter Search text "Sony"
#    And I click on the product and Add to Cart
#    When I enter Quantity and update cart
#    Then I click on CheckOut
#    And I add New Address details
#    And I log Off