package resources;

import pojo.AddPlaceAPIRequestPayload;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {
    public static AddPlaceAPIRequestPayload addPlacePayload(String name,String language,String address){
        AddPlaceAPIRequestPayload ap = new AddPlaceAPIRequestPayload();
        Location location = new Location();
        location.setLat(33.427362);
        location.setLng(-38.383494);
        List<String> placeTypes = new ArrayList<String>();
        placeTypes.add("kirana");
        placeTypes.add("super market");
        ap.setAccuracy(50);
        ap.setAddress(address);
        ap.setLanguage(language);
        ap.setPhone_number("84848484848");
        ap.setWebsite("www.akreddy.com");
        ap.setName(name);
        ap.setTypes(placeTypes);
        ap.setLocation(location);
        return ap;    }

    public static String deletePlacePayload(String place_ID){
        return "{\n" +
                "    \"place_id\":\""+place_ID+"\"\n" +
                "}";
    }
}
