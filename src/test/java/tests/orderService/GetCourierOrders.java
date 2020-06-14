package tests.orderService;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetCourierOrders extends BaseClassOrder {

    // GetStepsOrder courier orders
    @Test
    public void getCourierOrders() {
        getCouriers();

        Response response =
        given().
                param("courier_id", courierId).
                header("Authorization", courierToken).
        when().
                get(baseUrl + "/v1/courier/order").
        then().
                statusCode(200).
                extract().response();

        stepId = response.path("orders.steps.id").toString();
        orderId = response.path("orders.id").toString();
        clientId = response.path("orders.client_id").toString();
        clientName = response.path("orders.client_name").toString();
        clientPhone = response.path("orders.client_phone_number").toString();
        clientAddress = response.path("orders.to_address").toString();
        lat = response.path("orders.to_location.lat").toString();
        lon = response.path("orders.to_location.lon").toString();

        System.out.println(lat+" "+lon);
        System.out.println(response);
        System.out.println("**************** Step id " + stepId);
        System.out.println("**************** Order id " + orderId);
    }
}
