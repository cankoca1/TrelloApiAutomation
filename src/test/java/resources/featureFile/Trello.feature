@jsonServer1
Feature: Testing Trello APIs

  @setupLocal
  Scenario: Trello board and card actions
    Then I create a board on trello
    Then I create two card on trello
    And I update on of these cards randomly
    Then I delete the cards
    Then I delete the board






