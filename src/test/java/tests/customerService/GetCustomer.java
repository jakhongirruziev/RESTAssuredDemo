package tests.customerService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class GetCustomer extends BaseClassCustomer {

    // GetStepsOrder user customers by id
    @Test
    public void getUser(){
        GetAllCustomers get = new GetAllCustomers();
        get.getAllUsers();

        when().
                get(baseUrl+"/v1/customers/"+clientID).
        then().
                log().all().
                statusCode(200).
                // Asserts the response body
                body("id", equalTo(clientID)).
                body("name", equalTo(clientName));
    }
}
