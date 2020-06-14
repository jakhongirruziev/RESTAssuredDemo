package tests.branchService;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BranchLogin extends BaseClassBranch {

    // Check branch login
    @Test
    public void checkLogin(){
        GetAllBranches get = new GetAllBranches();
        get.getAllBranches();

        JSONObject requestBody = new JSONObject();
        requestBody.put("phone", phoneNumber);

        System.out.println(requestBody);
        given().
                body(requestBody.toJSONString()).
        when().
                post(baseUrl+"/v1/branches/check-login").
        then().
                log().all().
                statusCode(200);
    }

    // Confirm branch login
    @Test
    public void confirmLogin(){
        GetAllBranches get = new GetAllBranches();
        get.getAllBranches();

        JSONObject requestBody = new JSONObject();
        requestBody.put("phone", phoneNumber);
        requestBody.put("code", "7777");

        given().
                body(requestBody.toJSONString()).
        when().
                post(baseUrl+"/v1/branches/confirm-login").
        then().
                log().all().
                // Asserts the response body
                        body("phone", equalTo(phoneNumber)).
                statusCode(200);
    }
}
