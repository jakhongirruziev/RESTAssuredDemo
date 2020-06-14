package tests.orderService;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.*;

public class GetAllStatuses extends BaseClassOrder {

    // GetStepsOrder all statuses
    @Test
    public void getAllStatuses() {

        Response response =
                given().
                        header("Authorization", courierToken).
                when().
                        get(baseUrl + "/v1/order-statuses").
                then().
                        statusCode(200).
                        extract().response();
        //  All  statuses
        String[] array = {"vendor_ready", "vendor_cancelled", "vendor_accepted", "new", "finished", "delivered", "courier_picked_up", "courier_cancelled", "courier_accepted"};
        int random = new Random().nextInt(array.length); // Generates random number in the range of array length
        String randomStatus = array[random]; // Gets random status
        statusId = response.path(randomStatus); // Storing courier id in a class level variable for the use in the next method
        System.out.println("****************** Status id: " + randomStatus+ statusId);
    }

}
