Feature: Change password
  As an end user
  I would like to change my current password
  So I can keep my account safe.

  Scenario Outline: Access the Profile screen
    Given an logged user accessed the functionality
      | My Account      |
      | Change Password |
    And the current password "<currentPassword>"
    And the new password "<newPassword>"
    And the repeat new password "<repeatPassword>"
    When click on the CHANGE PASSWORD button
    Then show message "<message>"
    Examples:
      | currentPassword | newPassword | repeatPassword | message                                |
      | 123456          | 123456      | 123456         | Request completed with success         |
      | 1234            | 123456      | 123456         | Invalid password                       |
      | 123456          | 123         | 123            | Password must be at least 6 characters |
      | 123456          | 123456      | 654321         | Both password must match               |
      |                 |             |                | Please provide your current password   |
      | 123456          |             |                | Please provide a new password          |
      | 123456          | 123         |                | Repeat password                        |