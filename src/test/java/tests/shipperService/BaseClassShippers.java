package tests.shipperService;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;

import static io.restassured.RestAssured.given;

public class BaseClassShippers {
    static String baseUrl = "https://api.delever.uz";
    static String shipperId;
    static String phoneNumber;
    static String shipperName;
    static String shipperUsername;

    // Gets random data
    @DataProvider(name = "randomShipper")
    public Object[][] getRandom(){
        Object[][] data = new Object[1][3];
        // Gets random user
        Response response = given().get("https://randomuser.me/api").then().extract().response();
        // Gets random number
        data[0][0] = response.path("results.location.street.name").toString().replace("[", "").replace("]", "");
        int random = (int)(10000000+ (Math.random() * (100000000 - 10000000)));
        data[0][1] = "+9989" + random;
        data[0][2] = response.path("results.login.username").toString().replace("[", "").replace("]", "");

        return data;
    }
}
