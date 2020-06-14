package tests.shipperService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateShipper extends BaseClassShippers {

    // CreateCategory shipper
    @Test
    public void updateShipper() {
        GetAllShippers get = new GetAllShippers();
        get.getAllShippers();

        String phone= phoneNumber;
        String updateName = shipperName.toUpperCase();
        String updateUsername = shipperUsername.toLowerCase();

        JSONObject requestBody = new JSONObject();
        requestBody.put("id", shipperId);
        requestBody.put("name", updateName);
        requestBody.put("description", "This is a description");
        requestBody.put("logo", "string");
        requestBody.put("password", "delever123");
        requestBody.put("username", updateUsername);

        String[] data = phone.split(","); // Adding phones
        JSONArray phones = new JSONArray();
        for (int i = 0; i < data.length; i++) {
            phones.add(data[i]);
        }
        requestBody.put("phone", phones);


        System.out.println(requestBody);

        given().
                body(requestBody.toJSONString()).
        when().
                put(baseUrl + "/v1/shippers").
        then().
                log().all().
                statusCode(200).
                // Asserts the response body
                body("is_active", equalTo(true)).
                extract().response();
    }
}
