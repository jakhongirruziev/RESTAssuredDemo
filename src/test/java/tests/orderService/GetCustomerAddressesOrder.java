package tests.orderService;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.when;

public class GetCustomerAddressesOrder extends BaseClassOrder {

    // GetStepsOrder GetStepsOrder customer order addresses
    @Test
    public void getCustomerOrderAddresses(){
        String phoneNumber;
        // GetStepsOrder random customer phone number
        Response response = when().get(baseUrl+"/v1/customers").then().statusCode(200).extract().response();
        Random r = new Random();
        int random = r.nextInt(9);
        phoneNumber = response.path("customers.phone["+random+"]").toString();

        when().
                get(baseUrl+"/v1/customer-addresses/"+phoneNumber).
        then().
                log().all().
                statusCode(200);
    }

}
