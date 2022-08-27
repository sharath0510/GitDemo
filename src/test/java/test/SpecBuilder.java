package test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace p = new AddPlace();
		
		p.setAccuracy(50);
		p.setAddress("563,Sumukha Nilaya, Bethuru");
		p.setLanguage("Kannada");
		p.setPhone_number("8798968352");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Sumukha Nilaya");
		
		List<String> myList = new ArrayList<String>(); // defining String array list
		myList.add("shoe park");
		myList.add("shop");
		
		p.setTypes(myList);
		
		Location l = new Location();
		
		/*now to set lng and lat values we need to create an object of Location class and set the values first.
		
		Then pass the location class object to setLocation method*/
		
		l.setLat(-38.23232);
		l.setLng(34.34839);
		p.setLocation(l);
		
		//using request and response spec builder
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
	ResponseSpecification resSpec =	new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		
		RequestSpecification reqspec = given().spec(req).body(p);
		
		Response response = reqspec.when().post("/maps/api/place/add/json").then().spec(resSpec).extract().response();
		
		String responseString =response.asString();
		System.out.println(responseString);

		
	}

}
