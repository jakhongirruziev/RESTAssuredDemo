package tests.measureService;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import tests.productKindService.GetAllProductKind;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CreateMeasure extends BaseClassMeasure {

    // Create measure
    @Test
    public void createMeasure(){
        GetAllProductKind get = new GetAllProductKind();
        get.getAllProductKind();

        JSONObject requestBody = new JSONObject();
        requestBody.put("description", "this is a description");
        requestBody.put("name", "Some measure");
        requestBody.put("order_no", new Random().nextInt(100));
        requestBody.put("product_kind_id", GetAllProductKind.productId);
        requestBody.put("short_name", "This is short name");

        given().
                body(requestBody.toJSONString()).
        when().
                post(baseUrl+"/v1/measure").
        then().
                log().all().
                statusCode(200);
    }
}
