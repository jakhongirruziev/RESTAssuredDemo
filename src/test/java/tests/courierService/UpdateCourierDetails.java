package tests.courierService;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static java.lang.String.valueOf;

public class UpdateCourierDetails extends BaseClassCourier {

    // Update courier details
    @Test(dataProvider = "courierDetails")
    public void updateDetails(String address, int birthDate, String gender, int givenDate, int expiryDate) {
        GetAllCouriers get = new GetAllCouriers();
        get.getAllCouriers();

        JSONObject requestBody = new JSONObject();
        requestBody.put("address", address);
        requestBody.put("birth_date", valueOf(birthDate));
        requestBody.put("courier_id", courierId);
        requestBody.put("gender", gender);
        //requestBody.put("img", "string");
        requestBody.put("lisense_given_date", valueOf(givenDate));
        requestBody.put("lisense_expiry_date", valueOf(expiryDate));
        //requestBody.put("lisense_number", "string");
        requestBody.put("passport_number", "AA"+new Random().nextInt(100000000));

        given().
                body(requestBody.toJSONString()).
        when().
                put(baseUrl+"/v1/couriers/courier-details").
        then().
                log().all().
                statusCode(200);
    }
}
