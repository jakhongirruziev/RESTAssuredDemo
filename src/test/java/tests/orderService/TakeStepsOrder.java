package tests.orderService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TakeStepsOrder extends BaseClassOrder {

    // Take order steps
    @Test
    public void takeOrderSteps(){
        new GetCourierOrders().getCourierOrders();

        given().
                header("Authorization", courierToken).
        when().
                patch(baseUrl+"/v1/order-step/"+stepId+"/take").
        then().
                log().all().
                statusCode(200);
    }
}
