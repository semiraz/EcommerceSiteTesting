Feature: Purchase the order form Ecommerce Website

  Background:
    Given I landed on Ecommerce Page

    @Regression
    Scenario Outline: Positive Test of Submitting the order
      Given Logged in with username <name> and password <password>
      When I add product <productName> to Cart
      And Checkout <productName> and submit the order
      Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

      Examples:
        | name           | password | productName     |
        | haha@gmail.com | 12345678 | ZARA COAT 3     |
        | some@gmail.com | 321@Abcv | ADIDAS ORIGINAL |

      Scenario Outline: Verify if Search button filter products
        Given Logged in with username <name> and password <password>
        When I enter <productName> to SearchBox
        Then 1 element is filtered

        Examples:
          | name           | password | productName     |
          | haha@gmail.com | 12345678 | zara coat 3     |
          | some@gmail.com | 321@Abcv | adidas original |

        Scenario Outline: Check Product Details
          Given Logged in with username <name> and password <password>
          When I click on view button on <productName> and go to view page
          Then "zara coat 3" text is displayed

          Examples:
            | name           | password | productName |
            | haha@gmail.com | 12345678 | ZARA COAT 3 |
