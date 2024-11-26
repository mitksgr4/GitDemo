Feature: Validating place API's
@Addplace @Regression
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
   Given Add place payload with "<name>" "<language>" "<address>"
   When user calls "AddPlaceAPI" with "POST" http request
   Then the API call is successful with status code 200
   And "status" in response body is "OK"
   And "scope" in response body is "APP"
   And verify place_Id created maps to "<name>" using "getPlaceAPI"
   
 Examples: 
     |name       |language |address   |
     |SShouse    |English  |wtc center|
     
@DeletePlace @Regression
 Scenario: Verify if Delete Place functionality is working
 Given DeletePlace Payload
 When user calls "deletePlaceAPI" with "POST" http request
 Then the API call is successful with status code 200 
 And "status" in response body is "OK"
   
   

