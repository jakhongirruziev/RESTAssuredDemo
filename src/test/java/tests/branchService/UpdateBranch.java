package tests.branchService;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBranch extends BaseClassBranch {

    // Update branch
    @Test
    public void updateBranch() {
        GetAllBranches get = new GetAllBranches();
        get.getAllBranches();

        String updateName = branchName.toUpperCase();
        String updatePhone = phoneNumber.replace(" ", "");
        String updateUsername = branchUsername.toLowerCase();

        JSONObject requestBody = new JSONObject(); // CreateCategory response body
        requestBody.put("address", branchAddress);
        requestBody.put("destination", "string");
        requestBody.put("id", branchId);
        JSONObject location = new JSONObject(); // Lat long
        location.put("lat", 41.2995);
        location.put("long", 69.2401);
        requestBody.put("location", location); // put lat long body to location body
        requestBody.put("name", updateName);
        requestBody.put("password", "delever123");
        JSONArray phones = new JSONArray(); // Lat long
        phones.add(updatePhone);
        requestBody.put("phone", phones);
        requestBody.put("shipper_id", "45d47b2b-f1ce-448b-af73-37ec0dd8addd");
        requestBody.put("username", updateUsername);

        given().
                config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"))).
                body(requestBody.toJSONString()).
        when().
                put(baseUrl + "/v1/branches").
        then().
                log().all().
                // Asserts the response body
                body("name", equalTo(updateName)).
                statusCode(200);
        System.out.println(branchName);
    }
}
