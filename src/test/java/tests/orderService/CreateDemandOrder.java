package tests.orderService;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class CreateDemandOrder extends BaseClassOrder {


    // CreateCategory demand order
    @Test(dataProvider = "data")
    public void createDemandOrder(String name, String phone, String address, String lat, String lon){
        // *****************  Building request body ***************
        JSONObject requestBody = new JSONObject();
        requestBody.put("client_name", name);
        requestBody.put("client_phone_number", phone);
        requestBody.put("co_delivery_price", (int)(10000+ (Math.random() * (30000 - 10000)))); // Generates num in the range 10000 to 30000
        requestBody.put("description", "This is a description");
        requestBody.put("external_order_id", new Random().nextInt(100000)); // Generates random num bound 100000

        // ---------- Order steps ------------
        JSONArray steps = new JSONArray(); // --------------- Steps array
        for (int i = 0; i < 1; i++) { // i equals to step count
            getRandomBranch(); // Gets random branch
            JSONObject step = new JSONObject();
            step.put("address", branchAddress);
            step.put("branch_name", branchName);
            step.put("description", "This is a description");
            step.put("destination_address", branchDestinationAddress);
            step.put("external_step_id", new Random().nextInt(100000));
            JSONObject location = new JSONObject();
            location.put("lat", lat);
            location.put("long", lon);
            step.put("location", location); // put lat long in to location body
            step.put("phone_number", branchPhone);

            getRandomProduct(); // Gets random product
            JSONArray products = new JSONArray(); // ---- Product array
            JSONObject product = new JSONObject();
            product.put("external_product_id", new Random().nextInt(100000));
            product.put("name", productName);
            product.put("price", productPrice);
            product.put("quantity", new Random().nextInt(10));
            products.add(product); // ---- Add product to array

            step.put("products", products); // put product to step body

            steps.add(step); // ------------------------ Add step to steps array

            requestBody.put("steps", steps); // put steps in the request body
        }
        // -----------------------------------

        requestBody.put("to_address", address);
        JSONObject toLocation = new JSONObject(); // Lat long
        toLocation.put("lat", lat);
        toLocation.put("long",lon);
        requestBody.put("to_location", toLocation); // put lat long in to location body

        System.out.println(requestBody.toJSONString());
        // Request
        Response response =
                given().
                        header("Authorization",token).
                        body(requestBody.toJSONString()).
                when().
                        post(baseUrl+"/v1/demand-order").
                then().
                        log().all().
                        statusCode(200).
                        extract().response();
        System.out.println("******** This is order id "+response.path("order_id").toString());
    }
}
