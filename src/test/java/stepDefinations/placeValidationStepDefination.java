package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.requestEndPoints;
import resources.utils;


public class placeValidationStepDefination extends utils {
	
	static RequestSpecification reqSpec;
	RequestSpecification placeReq;
	Response resp;
	String placeId;

	@Given("Add Place Payload")
	public void add_place_payload(DataTable dataTable) throws IOException {
		
		
		
		List<Map<String, String>> placeData= dataTable.asMaps();
		
		double lat = Double.parseDouble(placeData.get(0).get("lat"));
		double lng = Double.parseDouble(placeData.get(0).get("lng"));
		int accuracy=Integer.parseInt(placeData.get(0).get("accuracy"));
		String name = placeData.get(0).get("name");
		String phoneNo = placeData.get(0).get("phoneNo");
		String address = placeData.get(0).get("address");
		String website = placeData.get(0).get("website");
		String language= placeData.get(0).get("language");
	
		List<String> types = new ArrayList<String>();
		String typesData[] = placeData.get(0).get("types").split(",");
		for(int i=0;i<typesData.length;i++)
		{
			types.add(typesData[i]);
		}
		
		placeReq= given().spec(reqSpec)
		.body(TestDataBuild.createAddPlace(lat, lng, accuracy, name, phoneNo, address, types, website, language));
	}
	
	@When("user calls {string} with {string} request")
	public void user_calls_with_post_request(String resource,String method) {
		
		requestEndPoints resourceApi=requestEndPoints.valueOf(resource);
		
	    if(method.equals("POST")) {
		resp= placeReq.when().post(resourceApi.getResource());
	    }
	    else if(method.equals("GET"))
	    {
	    	resp= given().spec(reqSpec).queryParam("place_id",placeId).when().get(resourceApi.getResource());
	    }
	    else if(method.equals("DELETE"))
	    {
	    	resp= placeReq.when().delete(resourceApi.getResource());
	    }
	}
	
	@Then("API call is success with success code {int}")
	public void api_call_is_success_with_success_code(int statusCode) {
		
//		ResponseSpecification resp= new ResponseSpecBuilder().expectStatusCode(statusCode).expectContentType(ContentType.JSON).build();
//		addPlaceResp.then().spec(resp);
		int actualStatusCode=resp.getStatusCode();
		Assert.assertEquals(actualStatusCode, statusCode);
		
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		
		String response=resp.then().assertThat().body(key, equalTo(value)).extract().asString();
		placeId = getResponseKeyValue(response,"place_id").toString();
			

	}
	
	@Then("verify {string} in getPlace response body")
	public void verify_name_in_response_body(String expectedName) {
		
		String response=resp.then().extract().asString();
		String actualName=getResponseKeyValue(response,"name").toString();
		Assert.assertEquals(actualName,expectedName);

	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() {
	    
		placeReq= given().spec(reqSpec)
		.body(TestDataBuild.deletePlace(placeId));
	}






}
