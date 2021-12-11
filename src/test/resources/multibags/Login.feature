Feature: Login
  As an end user
  I would like to login on the ecommerce
  So I can buy some bags.

  Scenario Outline: Access the Sign In screen
    Given that the user accessed the functionality login
      | My Account |
      | Sign In    |
    And the customer email address "<emailAddress>"
    And the password "<password>"
    When click on the SIGN IN button
    Then should login "<successfully>"
    Examples:
      | emailAddress          | password      | successfully |
      | carlos-wrong@adao.com | 123456        | false        |
      | carlos@adao.com       | wrongPassword | false        |
      | carlos.com            | 123456        | false        |
      | carlos.com            | wrongPassword | false        |
      | teste@teste.com       | 123456        | true         |