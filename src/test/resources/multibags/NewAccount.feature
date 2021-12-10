Feature: Create new account
  As an end user
  I would like to make my registration
  So I can use the ecommerce features.

  Scenario Outline: Access the Register screen
    Given that the user accessed the functionality register
      | My Account |
      | Register   |
    And the first name "<firstName>"
    And the last name "<lastName>"
    And the country "<country>"
    And the state/province "<state>"
    And the email address "<emailAddress>"
    And the password "<password>"
    And the repeat password "<repeatPassword>"
    When click on the CREATE AN ACCOUNT button
    Then redirected to the profile page
    Examples:
      | firstName | lastName | country | state     | emailAddress    | password | repeatPassword | successfully |
      | Carlos    | Adão     | Brasil  | São Paulo | carlos@adao.com | 123456   | 123456         | true         |
      |           | Adão     | Brasil  | São Paulo | carlos@adao.com | 123456   | 123456         | false        |
      | Carlos    |          | Brasil  | São Paulo | carlos@adao.com | 123456   | 123456         | false        |
      | Carlos    | Adão     |         | São Paulo | carlos@adao.com | 123456   | 123456         | false        |
      | Carlos    | Adão     | Brasil  |           | carlos@adao.com | 123456   | 123456         | false        |
      | Carlos    | Adão     | Brasil  | São Paulo |                 | 123456   | 123456         | false        |
      | Carlos    | Adão     | Brasil  | São Paulo | carlos.com      | 123456   | 123456         | false        |
      | Carlos    | Adão     | Brasil  | São Paulo | carlos@adao.com |          |                | false        |
      | Carlos    | Adão     | Brasil  | São Paulo | carlos@adao.com | 12345    | 12345          | false        |
      | Carlos    | Adão     | Brasil  | São Paulo | carlos@adao.com | 123456   |                | false        |
      | Carlos    | Adão     | Brasil  | São Paulo | carlos@adao.com | 123456   | different      | false        |