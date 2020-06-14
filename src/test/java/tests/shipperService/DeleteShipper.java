package tests.shipperService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class DeleteShipper extends BaseClassShippers {

    // Delete shipper
    @Test
    public void deleteShipper(){
        GetAllShippers get = new GetAllShippers();
        get.getAllShippers();

        when().
                delete(baseUrl+"/v1/shippers/"+shipperId).
        then().
                log().all().
                statusCode(200);

        when().get(baseUrl+"/v1/shippers/"+shipperId).then().statusCode(404);
    }
}
