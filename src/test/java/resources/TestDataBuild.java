package resources;

import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

public static AddPlace createAddPlace(double lat,double lng,int accuracy,String name,String phoneNo,String address,List<String>types,String website,String language){
		
		AddPlace addPlace = new AddPlace();
		
		addPlace.setLocation(createLoaction(lat,lng));
		addPlace.setAccuracy(accuracy);
		addPlace.setName(name);
		addPlace.setPhoneNumber(phoneNo);
		addPlace.setAddress(address);
		addPlace.setTypes(types);
		addPlace.setWebsite(website);
		addPlace.setLanguage(language);
		
		return addPlace;
		
	}
	
	  public static Location createLoaction(double lat,double lng)
	  {
		  Location location = new Location();
			
		  location.setLat(lat);
		  location.setLng(lng);
		  
		  return location;
	  }
	  
	  public static String deletePlace(String placeId)
	  {
		  return "{\r\n"
		  		+ "  \"place_id\": \""+placeId+"\"\r\n"
		  		+ "}";
	  }
}
