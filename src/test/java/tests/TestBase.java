package tests;

import io.restassured.response.Response;

import java.util.Random;

import static io.restassured.RestAssured.when;

public class TestBase {
    public static String baseUrl = "https://petstore.swagger.io/v2";
    public static String username;
    public static String firstName;
    public static String lastName;
    public static String email;
    public static String password;
    public static String phone;
    public static int userStatus;

    public static void randomUser() {
        // Get random user data
        Response randomUser = when().get("https://randomuser.me/api/").then().extract().response();
        username = randomUser.path("results.login.username").toString().replace("[", "").replace("]", "");
        firstName = randomUser.path("results.name.first").toString().replace("[", "").replace("]", "");
        lastName = randomUser.path("results.name.last").toString().replace("[", "").replace("]", "");
        email = randomUser.path("results.email").toString().replace("[", "").replace("]", "");
        password = randomUser.path("results.login.password").toString().replace("[", "").replace("]", "");
        phone = randomUser.path("results.phone").toString().replace("[", "").replace("]", "");
        userStatus = new Random().nextInt(3);
    }
}