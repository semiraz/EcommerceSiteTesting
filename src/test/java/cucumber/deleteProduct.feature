Feature: Delete the products- Ecommerce Website

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Delete the product from Cart Page
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And I go to Cart and click on delete button on <productName>
    Then <productName> is deleted and <message> is displayed

    Examples:
      | name           | password | productName     | message                    |
      | haha@gmail.com | 12345678 | ZARA COAT 3     | No Products in Your Cart ! |
      | some@gmail.com | 321@Abcv | ADIDAS ORIGINAL | No Products in Your Cart ! |