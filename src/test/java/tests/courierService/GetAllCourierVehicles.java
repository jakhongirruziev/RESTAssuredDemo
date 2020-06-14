package tests.courierService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetAllCourierVehicles extends BaseClassCourier {

    // Get courier vehicles
    @Test
    public void getCourierVehicles() {
        GetAllCouriers get = new GetAllCouriers();
        get.getAllCouriers();

        when().
                get(baseUrl+"/v1/couriers/"+courierId+"vehicles").
        then().
                log().all().
                statusCode(200);
    }
}
