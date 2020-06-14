package tests.orderService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetStepsOrder extends BaseClassOrder {

    // Take order steps
    @Test
    public void orderSteps() {
        when().
                patch(baseUrl + "/v1/order-step/" + "/take").
                then().
                log().all().
                statusCode(200);
    }

}
