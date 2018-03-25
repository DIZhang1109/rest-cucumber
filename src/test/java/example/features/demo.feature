Feature: Demo

  Scenario: Json Demo
    Given I have a request
    When I send it
    Then the status code is 200
    And response equals to the following
      | Attribute     | Value |
      | lotto.lottoId | 5     |
      | lotto.price   | 1212  |
    And response contains the following
      | Attribute              | Value  |
      | lotto.winners.winnerId | 23, 54 |