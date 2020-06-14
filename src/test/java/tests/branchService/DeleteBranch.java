package tests.branchService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class DeleteBranch extends BaseClassBranch {

    // Delete branch
    @Test
    public void deleteBranch(){
        GetAllBranches get = new GetAllBranches();
        get.getAllBranches();

        when().
                delete(baseUrl+"/v1/branches/"+branchId).
        then().
                log().all().
                statusCode(200);
        System.out.println(branchId);

        when().get(baseUrl+"/v1/branches/"+branchId).then().statusCode(404); // Verifies if branch is deleted by searching
    }
}
