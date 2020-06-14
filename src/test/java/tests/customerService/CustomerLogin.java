package tests.customerService;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CustomerLogin extends BaseClassCustomer {
    String code;

    // Check user customers login
    @Test
    public void login(){
        GetAllCustomers get = new GetAllCustomers();
        get.getAllUsers();

        JSONObject requestBody = new JSONObject();
        requestBody.put("phone", phoneNumber);

        Response response =
        given().
                body(requestBody.toJSONString()).
        when().
                post(baseUrl+"/v1/customers/login").
        then().
                log().all().
                statusCode(200).
                extract().response();
    }

    @Ignore
    @Test(dependsOnMethods = "checkLogin")
    public void confirmLogin(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("phone", phoneNumber);
        requestBody.put("code", "7777");

        given().
                body(requestBody.toJSONString()).
        when().
                post(baseUrl+"/v1/customers/confirm-login").
        then().
                log().all().
                statusCode(200).
                // Asserts the response body
                body("id", equalTo(clientID));
    }
}
