package tests.orderService;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.*;

public class UpdateOrder extends BaseClassOrder {

    // Update Order
    @Test
    public void updateOrder(){
        GetCourierOrders get = new GetCourierOrders();
        get.getCourierOrders();

        JSONObject requestBody = new JSONObject();
        requestBody.put("client_name", clientName);
        requestBody.put("client_phone_number", clientPhone);
        requestBody.put("co_delivery_price", (int)(10000+ (Math.random() * (30000 - 10000))));
        requestBody.put("description", "This is a description");
        requestBody.put("external_order_id", new Random().nextInt(100000));
        requestBody.put("id", clientId);
        requestBody.put("to_address", clientAddress);
        JSONObject location = new JSONObject();
        location.put("lat", Integer.parseInt(lat));
        location.put("long", Integer.parseInt(lon));
        requestBody.put("location", location);

        given().
                param("order_id", orderId).
                body(requestBody.toJSONString()).
                header("Authorization", courierToken).
        when().
                put(baseUrl+"/v1/order/"+orderId).
        then().
                log().all().
                statusCode(200);

    }
}
