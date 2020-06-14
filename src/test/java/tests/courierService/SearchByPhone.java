package tests.courierService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SearchByPhone extends BaseClassCourier {

    // Search by phone
    @Test
    public void getCourierByPhone() {
        GetAllCouriers get = new GetAllCouriers();
        get.getAllCouriers();

        given().
                param("phone", phoneNumber).
        when().
                get(baseUrl+"/v1/search-couriers/").
        then().
                log().all().
                statusCode(200).
                body("couriers.first_name[0]", equalTo(firstName)).
                body("couriers.last_name[0]", equalTo(lastName)).
                body("couriers.id[0]", equalTo(courierId));
    }
}
