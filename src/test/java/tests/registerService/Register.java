package tests.registerService;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Register extends BaseClassRegister {
    // Register
    @Test(dataProvider = "randomUser")
    public void register(String name, String phone){
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("phone", phone);

        given().
                body(requestBody.toJSONString()).
                when().
                post(baseUrl+"/v1/customers/register").
                then().
                log().all().
                statusCode(200);
        Phone = phone;
    }

}
