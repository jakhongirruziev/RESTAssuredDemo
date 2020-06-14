package tests.branchService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetAllBranchCouriers extends BaseClassBranch {
    // Get all branch couriers
    @Test
    public void getAllBranchCouriers() {
        GetAllBranches get = new GetAllBranches();
        get.getAllBranches();

        when().
                get(baseUrl+"/v1/branches/"+branchId+"/couriers").
        then().
                log().all().
                statusCode(200);
    }
}
