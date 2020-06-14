package tests.branchService;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetNearestBranch extends BaseClassBranch {

    // GetStepsOrder nearest branch
    @Test
    public void nearestBranch() {
        Response response = given().get("https://randomuser.me/api").then().extract().response();
        String longitude = response.path("results.location.coordinates.longitude").toString().replace("[", "").replace("]", "");
        String latitude = response.path("results.location.coordinates.latitude").toString().replace("[", "").replace("]", "");

        System.out.println("Lat, Long: "+latitude+", "+longitude);
        given().
                param("long", longitude).
                param("lat", latitude).
        when().
                get(baseUrl+"/v1/nearest-branch").
        then().
                log().all().
                statusCode(200);
        System.out.println(longitude+" "+latitude);
    }
}
