package tests.shipperService;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllShippers extends BaseClassShippers {

    // Get all branches
    @Test(priority = 1)
    public void getAllShippers() {
        Response response =
                given().
                        param("page", 1).
                when().
                        get(baseUrl + "/v1/shippers").
                then().
                        statusCode(200).log().all().
                        extract().response();

        int random = 0;
        // Gets random user and stores data in a class level variables
        shipperId = response.path("shippers.id["+random+"]").toString();
        shipperName = response.path("shippers.name["+random+"]").toString();
        shipperUsername = response.path("shippers.username["+random+"]").toString();
        phoneNumber = response.path("shippers.phone["+random+"]").toString().replace("[","").replace("]","");
        System.out.println(shipperId+shipperName+shipperUsername+phoneNumber);
    }
}
