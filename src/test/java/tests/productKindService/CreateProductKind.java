package tests.productKindService;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class CreateProductKind extends BaseClassProductKind {

    // Create product-kind
    @Test
    public void createProductKind(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("description", "this is a description");
        requestBody.put("name", "Some product kind");
        requestBody.put("order_no", new Random().nextInt(100));

        given().
                body(requestBody.toJSONString()).
        when().
                post(baseUrl+"/v1/measure").
        then().
                log().all().
                statusCode(200);
    }
}
