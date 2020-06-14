package tests.shipperService;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ShipperLogin extends BaseClassShippers {
    String code;

    // Check shipper login
    @Test
    public void login(){
        GetAllShippers get = new GetAllShippers();
        get.getAllShippers();

        JSONObject requestBody = new JSONObject();
        requestBody.put("login", shipperUsername);
        requestBody.put("password", "delever123");

        System.out.println(requestBody.toJSONString());
        given().
                body(requestBody.toJSONString()).
        when().
                post(baseUrl+"/v1/shippers/login").
        then().
                log().all().
                statusCode(200);
    }
}
