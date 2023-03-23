package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;

public class PlaceValidationsStepDefinition extends Utils {
    static String place_ID;
    TestDataBuilder data;
    RequestSpecification request;
    Response response;
    @Given("^Add Place Payload with (.+), (.+) and (.+)$")
    public void add_place_payload_with_and(String name, String language, String address) throws Throwable {
        request = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
    }


    @When("^user calls (.+) with (.+) http request$")
    public void user_calls_with_http_request(String apiname, String requesttype) {
        APIResources apiResources = APIResources.valueOf(apiname);
        if(requesttype.equalsIgnoreCase("POST")) {
            response = request.when().post(apiResources.getResourceValue());
        }else if(requesttype.equalsIgnoreCase("GET")) {
            response = request.when().get(apiResources.getResourceValue());
        }else if(requesttype.equalsIgnoreCase("PUT")) {
            response = request.when().put(apiResources.getResourceValue());
        }else if(requesttype.equalsIgnoreCase("DELETE")){
            response = request.when().delete(apiResources.getResourceValue());
        }

    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(), 200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        assertEquals(getJsonPath(response, key), value);
    }
    @Then("^verify place_id created maps to (.+) using \"([^\"]*)\"$")
    public void verify_placeid_created_maps_to_using_something(String name, String resource) throws Throwable {
        //get the placeID
        place_ID = getJsonPath(response, "place_id");

        //trigger getPlaceID API
        request =  given().spec(requestSpecification()).queryParam("place_id", place_ID);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, name);
    }
    @Given("^DeletePlace Payload$")
    public void deleteplace_payload() throws Throwable {
        request = given().spec(requestSpecification()).body(data.deletePlacePayload(place_ID));
    }
}
