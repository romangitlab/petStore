Feature: Implementation of the following API for DEMO PET STORE: https://petstore.swagger.io/

  Scenario: I check the availability, addition, updating and deleting of pet
    Given I Get available pets
    Then I assert expected result
    When I post a new available pet to the store
    Then I assert new pet added
    When I update this pet status to sold
    Then I assert status updated
    When I delete this pet
    Then I assert deletion


