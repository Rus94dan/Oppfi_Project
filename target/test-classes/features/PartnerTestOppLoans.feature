@Oppfi_Api
Feature: Oppfi potential customers approval feature

  Scenario: Validate successfully approved offer request
    Given base URL with end point and "valid" for approval request data
    When user submits request for approval with POST call
    Then validate user gets response with accepted value as "true" and the code "201" and status "APPROVED"

  Scenario: Validate declined approval request
    Given base URL with end point and "invalid" for approval request data
    When user submits request for approval with POST call
    Then validate user gets response with accepted value as "false" and the code "315" and status "DECLINED"

  Scenario: Validate request without system making the decision
    Given base URL with end point and "malformed" for approval request data
    When user submits request for approval with POST call
    Then validate user gets response with accepted value as "false" and the code "314" and status "DECLINED"