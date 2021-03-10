Feature: To verify API automation with RestAssured

  Scenario Outline: create pets and verify
    Given The end point exists
    When I create three new pets with "<name>" "<id>" "<status>"
    Then Response status code should be 200
    And Retrieve the pet and check the name is the same "<name>" "<id>"
    Examples:
      | name         | id | status    |
      | Pegasus      | 10 | available |
      | Unicorn      | 11 | available |
      | Snake-Turtle | 12 | available |

  Scenario: create pets with categories and tags
    Given When I create three new pets with
      | name        | id  | status    | categoryName | categoryId | tagName     | tagId |
      | Fairy       | 100 | available | Imaginary    | 901        | Tale, Movie | 700   |
      | SuperDoggie | 101 | available | Imaginary    | 902        | Childhood   | 701   |
      | Snake       | 102 | available | Real         | 903        | Scary       | 702   |
    When Pets are added successfully
    Then Pets with name are added to the store
