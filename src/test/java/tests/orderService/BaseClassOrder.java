package tests.orderService;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BaseClassOrder {
    static String baseUrl = "https://api.test.delever.uz";
    // Branch
    static String branchAddress;
    static String branchId;
    static String branchName;
    static String branchDestinationAddress;
    static String branchLat;
    static String branchLong;
    static String branchPhone;
    static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1ODc1MTE4ODQsImlzcyI6InVzZXIiLCJyb2xlIjoiY2FyZ29fb3duZXIiLCJzdWIiOiI0OGU1M2IxMS1jNDg0LTRkYzItODZlYi00ZDMyM2ZhYTU1YWEifQ.hQvkJxmgnrqKUmuERID6HA1sy5-qViB3Rr-UtNci7cA";
    // Product
    static String productName;
    static String productPrice;
    static String productId;
    // Order
    static String orderId;
    static String stepId;
    static String statusId;
    // Courier
    static String courierId;
    static String courierToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1ODg3NzQwNTUsImlzcyI6InVzZXIiLCJyb2xlIjoiY291cmllciIsInN1YiI6ImQ2N2I0OGM1LWYxZDEtNGJjNy04OGY4LTM5MzIxMzQxZDViNCJ9.nFr_UTsguQrY-Ojos60Wg9XDzEBikx8xUjQF1qc-9Lk";
    // Client
    static String clientId;
    static String clientName;
    static String clientPhone;
    static String clientAddress;
    static String lat;
    static String lon;
static
    // For getting random user
    @DataProvider(name = "data")
    public Object[][] getRandomDetails() {
        Object[][] data = new Object[1][5];
        // Gets random user
        Response response = given().get("https://randomuser.me/api").then().extract().response();
        // Gets random number
        data[0][0] = response.path("results.name.first").toString().replace("[", "").replace("]", "");
        data[0][1] = "+9989" + (int) (10000000 + (Math.random() * (100000000 - 10000000)));
        data[0][2] = response.path("results.location.street.name").toString().replace("[", "").replace("]", "");
        data[0][3] = response.path("results.location.coordinates.latitude").toString().replace("[", "").replace("]", "");
        data[0][4] = response.path("results.location.coordinates.longitude").toString().replace("[", "").replace("]", "");

        return data;
    }

    // For getting random user
    @DataProvider(name = "data2")
    public Object[][] getRandomDetails2() {
        Object[][] data = new Object[1][5];
        // Gets random user
        Response response = given().get("https://randomuser.me/api").then().extract().response();
        // Gets random number
        data[0][0] = response.path("results.name.first").toString().replace("[", "").replace("]", "");
        data[0][1] = "+9989" + (int) (10000000 + (Math.random() * (100000000 - 10000000)));
        data[0][2] = response.path("results.location.street.name").toString().replace("[", "").replace("]", "");
        data[0][3] = response.path("results.location.coordinates.latitude").toString().replace("[", "").replace("]", "");
        data[0][4] = response.path("results.location.coordinates.longitude").toString().replace("[", "").replace("]", "");

        return data;
    }

    // GetStepsOrder couriers
    public void getClients() {
        Response response = when().get(baseUrl + "/v1/customers").then().statusCode(200).extract().response();
        Random r = new Random();
        int random;
        if (Integer.parseInt(response.path("count").toString()) < 9)
            random = Integer.parseInt(response.path("count").toString());
        else
            random = 9;
        int n = r.nextInt(random);
        // Gets random user and stores data in a class level variables
        clientId = response.path("customers.id[" + n + "]").toString();
    }

    // GetStepsOrder branches
    public void getRandomBranch() {
        Response response = when().get(baseUrl + "/v1/branches").then().statusCode(200).extract().response();
        Random r = new Random();

        int n = 1;
        // Gets random user and stores data in a class level variables
        branchAddress = response.path("branches.address[" + n + "]").toString();
        branchId = response.path("branches.id[" + n + "]").toString();
        branchName = response.path("branches.name[" + n + "]").toString();
        branchDestinationAddress = response.path("branches.destination[" + n + "]").toString();
        branchLat = response.path("branches.location[" + n + "].lat").toString();
        branchLong = response.path("branches.location[" + n + "].long").toString();
        branchPhone = response.path("branches.phone[" + n + "]").toString().replace("[","").replace("]","");
        System.out.println(branchName);
}

    // GetStepsOrder products
    @Test
    public void getRandomProduct() {
        Response response = when().get(baseUrl + "/v1/product").then().statusCode(200).extract().response();

        int n = 0;
        // Gets random user and stores data in a class level variables
        productName = response.path("products.name[" + n + "]").toString();
        productPrice = response.path("products.price[" + n + "]").toString();
        productId = response.path("products.id[" + n + "]").toString();
    }

    // GetStepsOrder couriers
    public void getCouriers() {
        Response response =when().get(baseUrl+"/v1/couriers").then().statusCode(200).extract().response();
        // Gets random user index
        Random r = new Random();
        int random = r.nextInt(Integer.parseInt(response.path("count").toString()));
        // Gets random user and stores data in a class level variables
        courierId = response.path("couriers.id["+random+"]").toString();
    }
}

