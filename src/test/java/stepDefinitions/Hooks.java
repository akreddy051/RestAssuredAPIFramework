package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
    @Before("@DeletePlaceAPIValidation")
    public void generatePlaceID() throws Throwable {
        PlaceValidationsStepDefinition sd = new PlaceValidationsStepDefinition();
        if(PlaceValidationsStepDefinition.place_ID==null) {
            sd.add_place_payload_with_and("Rajesh", "Spanish", "Madrid, Spain");
            sd.user_calls_with_http_request("AddPlaceAPI", "POST");
            String place_id = sd.getJsonPath(sd.response, "place_id");
            PlaceValidationsStepDefinition.place_ID = place_id;
        }
    }
}
