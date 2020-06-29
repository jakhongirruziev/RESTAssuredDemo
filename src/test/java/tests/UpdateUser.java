package tests;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class UpdateUser extends TestBase {

    @DataProvider(name = "updateUsr")
    public Object[][] data(){
       randomUser(); // gets random user data
        return new Object[][]
                {
                        {username, firstName, lastName, email, password, phone, userStatus, 200}
                };
    }

    @Test(dataProvider = "updateUsr")
    public void updateUser(String username, String firstName, String lastName, String email, String password, String phone, int userStatus, Integer expectedStatusCode){
        // Create user before update
        CreateUser create = new CreateUser();
        create.createUser(username, firstName, lastName, email, password, phone, userStatus, 200);

        // Request body
        JSONObject body = new JSONObject();
        body.put("id", new Random().nextInt(9999));
        body.put("username", username.toLowerCase()); // update username
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("email", email);
        body.put("password", password);
        body.put("phone", phone.replace(" ", "")); // update phone number
        body.put("userStatus", userStatus);

        System.out.println(body.toJSONString());
        // Request
        given().
                contentType(ContentType.JSON).
                body(body.toJSONString()).
        when().
                put(baseUrl+"/user/"+username).
        then().
                log().all().
                statusCode(expectedStatusCode).
                // Assert the response body
                body("$", hasKey("code")).
                body("$", hasKey("type")).
                body("$", hasKey("message"));
    }
}
