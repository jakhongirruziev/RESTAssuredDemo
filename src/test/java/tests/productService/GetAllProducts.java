package tests.productService;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetAllProducts extends BaseClassProduct {

    // GetStepsOrder all couriers
    @Test
    public void getAllProducts(){
        Response response =
        when().
                get(baseUrl+"/v1/product").
        then().
                statusCode(200).
                extract().response();

        int n = 0;
        productName = response.path("products.name[" + n + "]").toString();
        productPrice = response.path("products.price[" + n + "]").toString();
        productId = response.path("products.id[" + n + "]").toString();
        System.out.println(productName);
    }
}
