package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class DeleteUser extends TestBase {

    @DataProvider(name = "deleteUsr")
    public Object[][] data(){
        randomUser(); // get random user data
        CreateUser create = new CreateUser();
        create.createUser(username, firstName, lastName, email, password, phone, userStatus, 200);

        return new Object[][] {
                {username, 200}, // Case 1 - positive case
                {username, 404}  // Case 2 - negative case
        };
    }

    @Test(dataProvider = "deleteUsr")
    public void deleteUser(String username, int expectedStatusCode){
        // Delete user Request
        when().
                delete(baseUrl+"/user/"+username).
        then().
                log().all().
                statusCode(expectedStatusCode);


        // Validate if data is deleted
        if (expectedStatusCode==200){
            GetUser get = new GetUser();
            get.getUser(username, 404);
        }
    }
}
