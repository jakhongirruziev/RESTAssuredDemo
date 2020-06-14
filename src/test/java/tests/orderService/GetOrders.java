package tests.orderService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetOrders extends BaseClassOrder {

    // GetStepsOrder orders
    @Test
    public void getOrders() {
        GetAllStatuses get = new GetAllStatuses();
        get.getAllStatuses();

        given().
                //param("status_id", statusId).
                header("Authorization", courierToken).
        when().
                get(baseUrl + "/v1/order").
        then().
                log().all().
                statusCode(200);
    }
}
