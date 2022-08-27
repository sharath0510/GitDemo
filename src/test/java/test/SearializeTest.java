package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class SearializeTest {

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
		
		
		Response res = given().log().all().queryParam("key", "qaclick123").body(p)
		
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response();
		
		String responseString =res.asString();
		System.out.println(responseString);
		

		
	}

}
