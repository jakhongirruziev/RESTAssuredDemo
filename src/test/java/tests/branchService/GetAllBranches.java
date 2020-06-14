package tests.branchService;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllBranches extends BaseClassBranch {

    // Get all branches
    @Test
    public void getAllBranches() {
        Response response =
                given().
                        param("page", 2).
                when().
                        get(baseUrl + "/v1/branches").
                then().
                        statusCode(200).log().all().
                        extract().response();

        int random = 1; //new Random().nextInt(2);
        // Gets random user and stores data in a class level variables
        branchName = response.path("branches.name["+random+"]").toString();
        phoneNumber = response.path("branches.phone["+random+"]").toString().replace("[","").replace("]","");
        branchId = response.path("branches.id["+random+"]").toString();
        branchUsername = response.path("branches.username["+random+"]").toString();
        branchAddress = response.path("branches.address["+random+"]").toString();
    }
}
