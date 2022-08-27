package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before("@DeletePlace") //Execute this method before running DeletePlace scenario
	public void beforeScenario() throws Throwable {
		
		//we would run this scenario only when place_id is null
		
		placeValidation p = new placeValidation();
		
		if(placeValidation.place_id==null) { //place_id is static so we are calling it using class name.We can also c
			//all it using class object 'p'
		
		p.Add_place_payload_with_something_something_something("Sharath", "Kannada", "Malaysia");
		
		p.user_calls_with_http_request("AddPlaceAPI", "POST");
		
		p.verify_place_id_created_maps_to_using("Sharath", "getPlaceAPI");
		
		}
		
	}

}
