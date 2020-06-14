package tests.categoryService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetAllCategories extends BaseClassCategory {

    // GetStepsOrder all category
    @Test
    public void getAllCategory(){
        when().
                get(baseUrl+"/v1/category").
        then().
                log().all().
                statusCode(200);
    }

}
