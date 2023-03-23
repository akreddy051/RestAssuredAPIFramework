package resources;

public enum APIResources {
    AddPlaceAPI("/maps/api/place/add/json"),
    DeletePlaceAPI("/maps/api/place/delete/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    PutPlaceAPI("/maps/api/place/update/json");

    private String resource;

    APIResources(String resource) {
        this.resource=resource;
    }

    public String getResourceValue(){
        return this.resource;
    }


}
