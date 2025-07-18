Feature: Error Validations

    Scenario Outline: Login error Validations
      Given Launch a ecommerce website
      And Logged in with username <username> and Password <password>
      Then Verify Error message is displayed
      Examples:
        |username             | password      |   Product      |
        |Punu@xyz.com         | Ac@1234       | ZARA COAT 3    |
        |sunnyd1998@gmail.com | c@1234        | ADIDAS ORIGINAL|