Feature:Submit Order scenarios

  Background:
  Given Launch a ecommerce website

  @Regression
  Scenario Outline:Add a product to cart and submit order
    Given Logged in with username <username> and Password <password>
    When I add the <Product> to cart
    And I checkout <Product> and submit order
    Then Verify "Thankyou for the order." message is displayed on confirmation page
  Examples:
      |username             | password      |   Product      |
      |Punu@xyz.com         | Abc@1234      | ZARA COAT 3    |
      |sunnyd1998@gmail.com | Abc@1234      | ADIDAS ORIGINAL|
