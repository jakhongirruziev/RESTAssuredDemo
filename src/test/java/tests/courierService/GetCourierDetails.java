package tests.courierService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetCourierDetails extends BaseClassCourier {

    // Get courier details
    @Test
    public void getCourierDetails() {
        GetAllCouriers get = new GetAllCouriers();
        get.getAllCouriers();

        System.out.println(courierId);
        when().
                get(baseUrl+"/v1/couriers/"+courierId+"/courier-details").
        then().
                log().all().
                statusCode(200);
    }

}
