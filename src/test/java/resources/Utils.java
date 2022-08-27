package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	
	public static RequestSpecification req; 
	JsonPath j;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		//using request and response spec builder
		//PrintStream is used for logging request and response to an output stream(like a file)
		// To enable append capability to logging, we are using if logic below.
		
		if(req==null) {
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

		 req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		 
		 return req;
		}
		
		return req;
	}
	
	//Here we are trying to read the baseUrl value from a properties file

	public String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
	
		FileInputStream fis = new FileInputStream("C:\\Users\\User\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		
		return prop.getProperty(key);
		
	}
	
	//utility to extract value from a json file
	public String getJsonPath(Response response, String key) {
		
		String res = response.asString();
    	j = new JsonPath(res);
    	return j.get(key).toString();
		
	}
	
	
}
