package tests.branchService;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import tests.courierService.BaseClassCourier;
import tests.courierService.GetAllCouriers;

import static io.restassured.RestAssured.given;

public class RemoveCourierFromBranch extends BaseClassBranch {

    // Remove courier from branch
    @Test
    public void removeCourierFromBranch(){
        GetAllBranches get1 = new GetAllBranches();
        get1.getAllBranches();

        GetAllCouriers get2 = new GetAllCouriers();
        get2.getAllCouriers();

        JSONObject requestBody = new JSONObject();
        requestBody.put("branch_id", branchId);
        requestBody.put("courier_id", BaseClassCourier.courierId );

        System.out.println(requestBody);
        given().
                body(requestBody.toJSONString()).
        when().
                post(baseUrl+"/v1/branches/remove-courier").
        then().
                log().all().
                statusCode(200);
    }
}
