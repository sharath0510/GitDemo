package resources;

//enum is special class in java which has collection of constants or methods

public enum API_Resources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	
	
	//declare a constructor for enum
	API_Resources(String resource){
		
		this.resource=resource;
	}
	
	public String getResource() {
		
		return resource;
	}

}
