package tests.courierService;

import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetAllCourierBranchIds extends BaseClassCourier {


    // Get courier branches
    @Test
    public void getCourierBranchesIds() {
        GetAllCouriers get = new GetAllCouriers();
        get.getAllCouriers();

        when().
                get(baseUrl+"/v1/couriers/"+courierId+"/branches").
        then().
                log().all().
                statusCode(200);
    }

}
