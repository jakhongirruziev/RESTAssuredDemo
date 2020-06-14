package tests.courierService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class DeleteCourier extends BaseClassCourier {

    // Delete courier
    @Test
    public void deleteCourier(){
        GetAllCouriers get = new GetAllCouriers();
        get.getAllCouriers();

        when().
                delete(baseUrl+"/v1/couriers/"+courierId).
        then().
                log().all().
                statusCode(200);

       // when().get(baseUrl+"/v1/couriers/"+courierId).then().statusCode(404); // Verifies if courier deleted
    }
}
