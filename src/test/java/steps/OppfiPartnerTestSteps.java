package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ApiUtils;
import utilities.ConfigReader;


public class OppfiPartnerTestSteps {

    private String baseUrl;
    private String endPoint;
    private String requestBody;
    private String apiKey;
    private Response response;

//    public static void main(String[] args) {

    /**
     * Before getting the env. var we need to assign them first
     * Then we should be able to fetch this variables by demand using .getenv() method as shown below:
     */
//        System.getenv();
//    }
    @Given("base URL with end point and {string} for approval request data")
    public void baseURLWithEndPointAndInvalidForApprovalRequestData(String dataType) {
        if (dataType.equals("invalid")) {
            apiKey = ConfigReader.getProperty("API_KEY");
            baseUrl = ConfigReader.getProperty("BASE_URL");
            endPoint = ConfigReader.getProperty("END_POINT");
            requestBody = ConfigReader.getProperty("JSON_BODY_INVALID");
        } else if (dataType.equals("valid")) {
            apiKey = ConfigReader.getProperty("API_KEY");
            baseUrl = ConfigReader.getProperty("BASE_URL");
            endPoint = ConfigReader.getProperty("END_POINT");
            requestBody = ConfigReader.getProperty("JSON_BODY_VALID");
        } else if (dataType.equals("malformed")) {
            apiKey = ConfigReader.getProperty("API_KEY");
            baseUrl = ConfigReader.getProperty("BASE_URL");
            endPoint = ConfigReader.getProperty("END_POINT");
            requestBody = ConfigReader.getProperty("JSON_BODY_MALFORMED");
        }

    }

    @When("user submits request for approval with POST call")
    public void user_submits_request_for_approval_with_POST_call() {
        response = ApiUtils.postCall(endPoint, requestBody, baseUrl, apiKey);
        System.out.println(response.asString());
    }


    @Then("validate user gets response with accepted value as {string} and the code {string} and status {string}")
    public void userGetsResponseWithStatusCodeBadRequest(String acceptedExpectedValue, String codeExpectedValue, String statusExpectedValue) {
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String acceptedActualValue = jsonPath.getString("accepted");
        String codeActualValue = jsonPath.getString("code");
        String statusActualValue = jsonPath.getString("status");
        System.out.println(acceptedActualValue);
        System.out.println(codeActualValue);

        Assert.assertEquals(acceptedExpectedValue, acceptedActualValue);
        Assert.assertEquals(codeExpectedValue, codeActualValue);
        Assert.assertEquals(statusExpectedValue, statusActualValue);
    }
}
