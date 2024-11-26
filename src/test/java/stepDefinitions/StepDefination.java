package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import pojo.addPlace;
import pojo.location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination extends utils {
	RequestSpecification response;
	ResponseSpecification resspec;
	Response res;
	TestDataBuild data=new TestDataBuild(); //object of TestDataBuild class created to access data
	static String place_id;
	
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		response=given().spec(requestSpecification())
				.body(data.addPlacePayload(name,language,address));
		
	    
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		//constructor will be called with value of resource which you pass
		
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		System.out.println("got responsefor 1");
		System.out.println("got responsefor 2");
		System.out.println("got responsefor 3");
		
		resspec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST"))
			res=response.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			res=response.when().get(resourceAPI.getResource());
				
	}
	@Then("the API call is successful with status code {int}")
	public void the_api_call_is_successful_with_status_code(Integer int1) {
	   assertEquals(res.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String Actualkeyvalue, String Expectedvalue) throws IOException {
	 
	    
	    assertEquals(getJsonPath(res, Actualkeyvalue), Expectedvalue);
	    
	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		
		place_id=getJsonPath(res,"place_id");
		response=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		
		String ActualName=getJsonPath(res,"name");
		
		assertEquals(ActualName,expectedName);
		
		System.out.println(ActualName);
		System.out.println(expectedName);
		System.out.println("Assertion done");
		
		
		
		
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	   response= given().spec(requestSpecification())
	    .body(data.deletePlacePayload(place_id));
	}



}

