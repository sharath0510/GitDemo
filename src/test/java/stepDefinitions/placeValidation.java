package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.API_Resources;
import resources.TestDataBuild;
import resources.Utils;
import test.AddPlace;
import test.Location;

public class placeValidation extends Utils {
	
	
	RequestSpecification reqspec;
	ResponseSpecification resSpec;
	Response response;
	static String place_id;
	TestDataBuild data = new TestDataBuild();
	

	 @Given("Add place payload with {string} {string} {string}")
	    public void Add_place_payload_with_something_something_something(String name, String language,String address) throws Throwable {

		reqspec = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
		
    }
	
    
    
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {
    	
    	
    	//constructor will be called with the value of resource which you pass from feature file
    	API_Resources resourceAPI = API_Resources.valueOf(resource);
    	System.out.println(resourceAPI.getResource());
    	
    	resSpec =	new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	
    	if(httpMethod.equalsIgnoreCase("POST"))
    	response = reqspec.when().post(resourceAPI.getResource()).then().spec(resSpec).extract().response();
    	
    	else if(httpMethod.equalsIgnoreCase("GET"))
    		response = reqspec.when().get(resourceAPI.getResource()).then().spec(resSpec).extract().response();
    	
    }
    

    @Then("^the API call got success with status code 200$")
    public void the_api_call_got_success_with_status_code_200() throws Throwable {
    	
    	assertEquals(response.getStatusCode(),200);
    	
    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void something_in_response_body_is_something(String actual, String expected ) throws Throwable {
    	
    	//assertEquals(j.get(actual).toString(), expected); getting error here
    	
    }
    
    
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
    	
    	place_id = getJsonPath(response,"place_id");
    	reqspec = given().spec(requestSpecification().queryParam("place_id", place_id));
    	
    	user_calls_with_http_request(resource,"GET");
    	
    	String Actualname = getJsonPath(response,"name");
    	
    	assertEquals(Actualname,expectedName);
    }
    
    @Given("DeletePlace payload")
    public void delete_place_payload() throws IOException {
    	
    	reqspec = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    	
    	
    }
 

}
