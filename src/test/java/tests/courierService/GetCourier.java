package tests.courierService;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class GetCourier extends BaseClassCourier {

    // Get courier
    @Test
    public void getCourier(){
        GetAllCouriers get = new GetAllCouriers();
        get.getAllCouriers();

        when().
                get(baseUrl+"/v1/couriers/"+courierId).
        then().
                log().all().
                statusCode(200).
                body("first_name", equalTo(firstName)).
                body("last_name", equalTo(lastName)).
                body("id", equalTo(courierId));
    }
}
