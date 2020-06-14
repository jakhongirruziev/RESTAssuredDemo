package tests.courierService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class BlockingCourier extends BaseClassCourier {

    // Block courier
    @Test
    public void blockCourier() {
        GetAllCouriers get = new GetAllCouriers();
        get.getAllCouriers();

        when().
                patch(baseUrl+"/v1/couriers/"+courierId+"/block").
        then().
                log().all().
                statusCode(200);
    }
}
