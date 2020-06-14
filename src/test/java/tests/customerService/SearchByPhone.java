package tests.customerService;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SearchByPhone extends BaseClassCustomer {

    // Search by phone
    @Test
    public void searchByPhone(){
        GetAllCustomers get = new GetAllCustomers();
        get.getAllUsers();

        Response response =
                given().
                        param("phone", phoneNumber).
                when().
                        get(baseUrl+"/v1/search-customers").
                then().
                        log().all().
                        statusCode(200).
                        extract().response();
        // Asserts if result is correct
        Assert.assertEquals(response.path("customers.name").toString().replace("[", "").replace("]", ""), clientName);
        Assert.assertEquals(response.path("customers.phone").toString().replace("[", "").replace("]", ""), phoneNumber);
    }
}
