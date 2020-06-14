package tests.productKindService;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetAllProductKind extends BaseClassProductKind {
    public static String productId;

    // Get all product-kind
    @Test
    public void getAllProductKind() {
        Response response =
                when().
                        get(baseUrl + "/v1/product-kind").
                then().
                        log().all().
                        statusCode(200).
                        extract().response();

        productId = response.path("product_kinds.id[0]").toString();
    }
}
