package tests.courierService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class UnblockingCourier extends BaseClassCourier {

    // Unblock courier
    @Test
    public void unblockCourier() {
        GetAllCouriers get = new GetAllCouriers();
        get.getAllCouriers();

        when().
                patch(baseUrl+"/v1/couriers/"+courierId+"/unblock").
        then().
                log().all().
                statusCode(200);
    }
}
