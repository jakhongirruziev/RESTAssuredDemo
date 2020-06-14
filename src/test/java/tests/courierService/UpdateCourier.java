package tests.courierService;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateCourier extends BaseClassCourier {

    // Update courier
    @Test
    public void updateCourier() {
        GetAllCouriers get = new GetAllCouriers();
        get.getAllCouriers();

        String updatedFirstName = firstName.toUpperCase();
        String updatedLastName = lastName.toUpperCase();
        String updatePhone = phoneNumber.replace(" ", "");

        JSONObject requestBody = new JSONObject();
        requestBody.put("first_name", updatedFirstName);
        requestBody.put("last_name", updatedLastName);
        requestBody.put("phone", updatePhone);
        requestBody.put("id", "2ccf2edd-6cec-479f-a833-fa0979c050c7");
        //requestBody.put("distributor_id", "029c23a6-1314-4e60-9574-2a413fdb8c13");
        //requestBody.put("park_id", "3544b620-0ebc-4274-98d2-f638e2f815c2");
        given().
                body(requestBody.toJSONString()).
        when().
                put(baseUrl+"/v1/couriers").
        then().
                log().all().
                // Asserts response body
                body("first_name", equalTo(updatedFirstName)).
                body("last_name", equalTo(updatedLastName)).
                body("phone", equalTo(updatePhone)).
                statusCode(200);
    }
}
