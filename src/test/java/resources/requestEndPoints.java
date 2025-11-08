package resources;

public enum requestEndPoints {
	
	// enum constanst with associated string value
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;

	requestEndPoints(String resource) {
		
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}

}
