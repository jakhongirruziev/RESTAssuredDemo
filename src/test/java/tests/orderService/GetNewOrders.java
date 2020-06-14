package tests.orderService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetNewOrders extends BaseClassOrder {

    // GetStepsOrder New Orders
    @Test
    public void getNewOrders() {
        when().
                get(baseUrl + "/v1/new-order").
        then().
                log().all().
                statusCode(200);
    }
}
