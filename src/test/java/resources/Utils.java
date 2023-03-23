package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {
        if(req==null){
            PrintStream printStream = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key","qaclick123")
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                    .build();
        }
        return req;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("C:\\Users\\Akshay Singadiwar\\Documents\\API Automation\\MavenJava\\src\\test\\java\\resources\\global.properties");
        properties.load(file);
        return properties.getProperty(key);
    }

    public String getJsonPath(Response response,String keyPath) {
        JsonPath js = new JsonPath(response.asString());
        return js.get(keyPath).toString();
    }
}
