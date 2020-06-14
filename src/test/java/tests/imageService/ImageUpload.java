package tests.imageService;


import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;


public class ImageUpload {
    String baseUrl = "https://api.delever.uz";


    // Upload image
    @Test
    public void uploadImage(){

        given().
                accept("application/json").
        when().
                multiPart("file", new File("./data/testImage.png"), "image/png").
                post(baseUrl+"/v1/upload").
        then().
                log().all().
                assertThat().statusCode((200));
    }

}
