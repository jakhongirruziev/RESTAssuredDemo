package tests.registerService;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegisterConfirm extends BaseClassRegister {

    // RegisterService confirm
    @Test(dependsOnMethods = "register")
    public void registerConfirm(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("activation_code", "7777");
        requestBody.put("phone", Phone);

        Response response =
                given().
                        body(requestBody.toJSONString()).
                        when().
                        post(baseUrl+"/v1/customers/register-confirm").
                        then().
                        log().all().
                        // Assertion of the response
                                body("phone", equalTo(Phone)).
                        statusCode(200).
                        extract().response();
        System.out.println("****** This is access token "+response.path("access_token"));
        System.out.println("****** This is id "+response.path("id"));

    }
}
