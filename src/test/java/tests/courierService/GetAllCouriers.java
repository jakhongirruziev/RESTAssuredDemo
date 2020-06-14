package tests.courierService;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetAllCouriers extends BaseClassCourier {

    //GetStepsOrder all couriers
    @Test
    public void getAllCouriers() {
        Response response =
                when().
                        get(baseUrl+"/v1/couriers").
                then().
                        statusCode(200).log().all().
                        extract().response();
        // Gets random user index
        int random = 9;
        // Gets random user and stores data in a class level variables
        courierId = response.path("couriers.id["+random+"]").toString();
        firstName = response.path("couriers.first_name["+random+"]").toString();
        lastName = response.path("couriers.last_name["+random+"]").toString();
        phoneNumber = response.path("couriers.phone["+random+"]").toString();
        System.out.println(courierId);
    }
}
