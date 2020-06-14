package tests.courierService;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CourierLogin extends BaseClassCourier {
    String code;

    // Check login
    @Test
    public void checkLogin() {
        GetAllCouriers get = new GetAllCouriers();
        get.getAllCouriers();

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", phoneNumber);
        Response response =
        given().
                body(requestBody.toJSONString()).
        when().
                post(baseUrl+"/v1/couriers/check-login").
        then().
                log().all().
                statusCode(200).
                extract().response();
        code = response.path("code").toString();
    }

    // Confirm login
    @Test(dependsOnMethods = "checkLogin")
    public void confirmLogin() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("phone", phoneNumber);
        requestBody.put("code", code);

        Response response =
        given().
                body(requestBody.toJSONString()).
        when().
                post(baseUrl+"/v1/couriers/confirm-login").
        then().
                log().all().
                statusCode(200).
                extract().response();
        courierToken = response.path("access_token").toString();
        System.out.println(courierToken);
    }
}
