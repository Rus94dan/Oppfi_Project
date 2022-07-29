package utilities;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    /**
     custom created POST request function based on Rest Assured lib.
     */

    public static Response postCall(String endPoint, Object body, String baseUrl, String apiKey){
        return given().baseUri(baseUrl)
                .and()
                .header("Content-Type","application/json").header("x-api-key",apiKey)
                .when().body(body)
                .and().post(endPoint);
    }
}
