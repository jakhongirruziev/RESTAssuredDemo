package tests.customerService;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateCustomer extends BaseClassCustomer {

    // CreateCategory user customers
    @Test(dataProvider = "randomUser")
    public void createUser(String name, String phone){
        JSONObject requestBody = new JSONObject(); // CreateCategory body of the request
        requestBody.put("name", name);
        requestBody.put("phone", "+998944130909");

        Response response =
                given().
                        config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"))).
                        body(requestBody.toJSONString()).
                when().
                        post(baseUrl+"/v1/customers").
                then().
                        log().all().
                        statusCode(200).
                // Asserts the response body
                body("name", equalTo(name)).
                body("phone", equalTo(phone)).
                body("is_active", equalTo(true)).
                extract().response();
        System.out.println("******* This is id "+response.path("id").toString());
        System.out.println("******* This is access token "+response.path("access_token").toString());
    }
}
