Feature: Validating add place API

@AddPlace
Scenario Outline: Verify if place is being added successfully
	Given Add place payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
	
	Examples:
		|name|language|address|
		|Gandha House|English|Davanagere KA|
		
		
@DeletePlace
Scenario: Verify if delete place functionality is working

	Given DeletePlace payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
		
		