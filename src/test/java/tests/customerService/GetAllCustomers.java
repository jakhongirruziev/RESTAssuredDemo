package tests.customerService;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.when;

public class GetAllCustomers extends BaseClassCustomer {

    // GetStepsOrder all user customers
    @Test(priority = 1)
    public void getAllUsers() {
        Response response =
                when().
                        get(baseUrl + "/v1/customers").
                then().
                        statusCode(200).
                        extract().response();
        // Gets random user index
        int random = new Random().nextInt(9);
        // Gets random user and stores data in a class level variables
        clientName = response.path("customers.name[" + random + "]").toString();
        phoneNumber = response.path("customers.phone[" + random + "]").toString();
        clientID = response.path("customers.id[" + random + "]").toString();
    }

}
