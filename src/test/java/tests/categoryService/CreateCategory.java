package tests.categoryService;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateCategory extends BaseClassCategory {

    // CreateCategory category
    @Test
    public void createCategory(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("description", "Fast food");
        requestBody.put("name", "Fast food");
        requestBody.put("order_no", 2);
        requestBody.put("parent_id", null);

        Response response =
                given().
                        config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"))).
                        contentType(ContentType.JSON).
                        body(requestBody.toJSONString()).
                when().
                        post(baseUrl+"/v1/category").
                then().
                        log().all().
                        statusCode(201).
                        extract().response();
        System.out.println("******** This product id "+response.path("id").toString());
    }
}
