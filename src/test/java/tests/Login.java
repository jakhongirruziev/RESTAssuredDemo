package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Login extends TestBase {


    @DataProvider(name = "Login")
    public Object[][] data(){
        return new Object[][] {
                {"silverelephant938", "spencer", 200}, // Case 1 - positive case
                {"дамми.дата", "invalid_pass123", 401} // Case 2 - invalid data
        };
    }

    @Test(dataProvider = "Login")
    public void login(String username, String password, int expectedStatus){
        given().
                accept("application/json").
                param("username", username).
                param("password", password).
        when().
                get(baseUrl+"/user/login").
        then().
                log().all().
                statusCode(expectedStatus);
    }
}
