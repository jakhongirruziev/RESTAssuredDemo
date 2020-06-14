package tests.customerService;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateCustomer extends BaseClassCustomer {

    // Update user customers
    @Test
    public void updateUser(){
        GetAllCustomers get = new GetAllCustomers();
        get.getAllUsers();

        String updatedName = clientName.toUpperCase();  // Update name
        String updatedPhone = phoneNumber.replace(" ", ""); // Update phone

        JSONObject requestBody = new JSONObject(); // CreateCategory request body
        requestBody.put("id", clientID);
        requestBody.put("name", updatedName);
        requestBody.put("phone", updatedPhone);

        Response response =
                given().
                        body(requestBody.toJSONString()).
                when().
                        put(baseUrl+"/v1/customers").
                then().
                        log().all().
                        statusCode(200).
                        // Asserts the response body
                                body("name", equalTo(updatedName)).
                        body("phone", equalTo(updatedPhone)).
                        extract().response();

        System.out.println("******** This is id "+response.path("id").toString());
        System.out.println("******** This is access token "+response.path("access_token").toString());
    }
}
