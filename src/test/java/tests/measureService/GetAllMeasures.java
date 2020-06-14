package tests.measureService;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetAllMeasures extends BaseClassMeasure{

    // Get all measures
    @Test
    public void getAllMeasures(){
        when().
                get(baseUrl+"/v1/measure").
        then().
                log().all().
                statusCode(200);
    }
}
