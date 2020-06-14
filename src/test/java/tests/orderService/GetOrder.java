package tests.orderService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetOrder extends BaseClassOrder {

    // Get order
    @Test
    public void getOrder(){
        GetCourierOrders get = new GetCourierOrders();
        get.getCourierOrders();

        given().
                header("Authorization",  courierToken).
               // param("order_id", "5d03c037-cae6-40f8-8a55-e95256891685").
        when().
                get(baseUrl+"/v1/order/5d03c037-cae6-40f8-8a55-e95256891685").
        then().
                log().all().
                statusCode(200);
    }
}
