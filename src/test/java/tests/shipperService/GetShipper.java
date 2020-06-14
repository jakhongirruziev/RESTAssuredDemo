package tests.shipperService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetShipper extends BaseClassShippers {

    // Get shipper
    @Test
    public void getShipper(){
        GetAllShippers get = new GetAllShippers();
        get.getAllShippers();

        when().
                get(baseUrl+"/v1/shippers/"+shipperId).
        then().
                log().all().
                statusCode(200);
    }
}
