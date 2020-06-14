package tests.courierService;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourier extends BaseClassCourier {

    // CreateCategory courier
    @Test(dataProvider = "excelCouriers")
    public void createCourier(String firstName, String lastName, String phone, String branch_id) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("first_name", firstName);
        requestBody.put("last_name", lastName);
        requestBody.put("phone", phone);

        Response response =
        given().
                config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"))).
                body(requestBody.toJSONString()).
        when().
                post(baseUrl+"/v1/couriers").
        then().
                log().all().
                // Asserts response body
                body("phone", equalTo(phone)).
                statusCode(200).
                extract().response();

        // Add courier  to branch
        JSONObject requestBody2 = new JSONObject();
        requestBody2.put("branch_id", branch_id);
        requestBody2.put("courier_id", response.path("id").toString() );

        given().
                body(requestBody2.toJSONString()).
        when().
                post(baseUrl+"/v1/branches/add-courier").
        then().
                log().all().
                statusCode(200);
    }
}
