package resources;

import java.util.ArrayList;
import java.util.List;

import test.AddPlace;
import test.Location;

public class TestDataBuild {
	
	
	public AddPlace addPlacePayload(String name, String language,String address) {
		
		
		AddPlace p = new AddPlace();
		
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("8798968352");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		
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
		
		return p;
		
	}

	public String deletePlacePayload(String place_id) {
		
		return "{\r\n  \"place_id\":\""+place_id+"\"\r\n}";
	}
}
