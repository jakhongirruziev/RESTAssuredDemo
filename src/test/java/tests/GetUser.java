package tests;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetUser extends TestBase {

    @DataProvider(name = "getUsr")
    public Object[][] data(){
        return new Object[][] {
                {"bigbear280", 200}, // Case 1 - positive case
                {"дамми.дата", 404} // Case 2 - negative case
        };
    }

    @Test(dataProvider = "getUsr")
    public void getUser(String username, int expectedStatus){
        // Request
        Response response =
        when().
                get(baseUrl+"/user/"+username).
        then().
                log().all().
                statusCode(expectedStatus).extract().response();
    }
}
;