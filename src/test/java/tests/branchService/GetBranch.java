package tests.branchService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class GetBranch extends BaseClassBranch {

    // GetStepsOrder user branch by id
    @Test
    public void getBranch(){
        GetAllBranches get = new GetAllBranches();
        get.getAllBranches();

        when().
                get(baseUrl+"/v1/branches/"+branchId).
        then().
                log().all().
                // Asserts the response body
                body("name", equalTo(branchName)).
                body("id", equalTo(branchId)).
                statusCode(200);
    }

}
