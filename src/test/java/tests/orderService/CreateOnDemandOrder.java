package tests.orderService;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import tests.productService.BaseClassProduct;
import tests.productService.GetAllProducts;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class CreateOnDemandOrder extends BaseClassOrder {


    // CreateCategory demand order
    @Test
    public void createOnDemandOrder(){
        // Building request body
        getClients();
        JSONObject requestBody = new JSONObject();
        requestBody.put("apartment", new Random(100)+"");
        requestBody.put("building", new Random(100)+"");
        requestBody.put("floor", new Random(20)+"");
        requestBody.put("extra_phone_number", null);
        requestBody.put("payment_type", "cash");
        requestBody.put("source", "website");
        requestBody.put("client_id", clientId);
        requestBody.put("co_delivery_price", (int)(10000+ (Math.random() * (30000 - 10000))));
        requestBody.put("description", "This is a description");

        // ---------- Order steps ------------
        getRandomBranch();
        JSONArray steps = new JSONArray(); // --------------- Steps array
        for (int i = 0; i < 1; i++) { // i equals to step count
            JSONObject step = new JSONObject();
            step.put("branch_id", branchId);
            step.put("description", "This is a description");

            new GetAllProducts().getAllProducts();
            JSONArray products = new JSONArray(); // ---- Product array
            JSONObject product = new JSONObject();
            product.put("price", (int)(10000+ (Math.random() * (30000 - 10000))));
            new BaseClassProduct();
            product.put("product_id", BaseClassProduct.productId);
            product.put("quantity", new Random().nextInt(10));
            products.add(product); // ---- Add product to array

            step.put("products", products); // put product to step body
            steps.add(step); // ------------------------ Add step to steps array

            requestBody.put("steps", steps); // put steps in the request body
        }
        // -----------------------------------

        requestBody.put("to_address", branchAddress);
        JSONObject toLocation = new JSONObject(); // Lat long
        toLocation.put("lat", branchLat);
        toLocation.put("long", branchLong);
        requestBody.put("to_location", toLocation); // put lat long in to location body

        System.out.println(requestBody.toJSONString());
        // Request
        Response response =
                given().
                        config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"))).
                        header("Authorization",token).
                        body(requestBody.toJSONString()).
                when().
                        post(baseUrl+"/v1/ondemand-order").
                then().
                        log().all().
                        statusCode(200).
                        extract().response();
        System.out.println("******** This is order id "+response.path("order_id").toString());
    }
}
