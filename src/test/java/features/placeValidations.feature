Feature: RSA Maps API's validations

  @AddPlaceAPIValidation @Regression
  Scenario Outline: Verify whether the Add Place API is working properly or not
    Given Add Place Payload with <name>, <language> and <address>
    When user calls <APIName> with <requestType> http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    Then verify place_id created maps to <name> using "GetPlaceAPI"

    Examples:
      | name         | language | address           | APIName     | requestType|
      | Akshay Reddy | Telugu   | Lingi,Maharashtra | AddPlaceAPI | Post       |
#      | Nidhi Joshi  | Hindi    | Balaghat, MP      | AddPlaceAPI | Post       |

  @DeletePlaceAPIValidation @Regression
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls DeletePlaceAPI with DELETE http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
