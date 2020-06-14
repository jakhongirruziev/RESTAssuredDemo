package tests.productService;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class CreateProduct extends BaseClassProduct {

    @Test(dataProvider = "createProduct", invocationCount = 20)
    public void createProduct(String name, String shortName, String previewText, String description, String price, String categoryId){
        JSONObject requestBody = new JSONObject();
        requestBody.put("category_id", categoryId);
        requestBody.put("code", new Random().nextInt(10)+"");
        requestBody.put("description", description);
        requestBody.put("image", "string");
        requestBody.put("measure_id", "5ed4d796f35ff86fd41112f6");
        requestBody.put("name", name);
        requestBody.put("order_no", 1);
        requestBody.put("preview_text", previewText);
        requestBody.put("price", price);
        requestBody.put("product_kind_id", "5ed4d763f35ff86fd41112f5");
        requestBody.put("short_name", shortName);

        System.out.println(requestBody);
        given().
                config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"))).
                contentType(ContentType.JSON).
                body(requestBody.toJSONString()).
        when().
                post(baseUrl+"/v1/product").
        then().
                log().all().
                statusCode(201);
    }
}
