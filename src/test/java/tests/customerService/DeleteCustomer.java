package tests.customerService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class DeleteCustomer extends BaseClassCustomer {

    // Delete user customers
    @Test
    public void deleteUser(){
        GetAllCustomers get = new GetAllCustomers();
        get.getAllUsers();

        when().
                delete(baseUrl+"/v1/customers/"+clientID). // Deletes user
        then().
                log().all().
                statusCode(200);

        //when().get(baseUrl+"/v1/customers/"+clientID).then().statusCode(404); // Verifies if user is deleted by searching
    }
}
