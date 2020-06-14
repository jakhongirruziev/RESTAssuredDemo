package tests.shipperService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class CreateShipper extends BaseClassShippers {

    // CreateCategory shipper
    @Test(dataProvider = "randomShipper")
    public void createShipper(String name, String phone, String username) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Operator");
        requestBody.put("description", "This is a description");
        requestBody.put("logo", "string");
        requestBody.put("password", "operator123");
        requestBody.put("username", "operator");

        String[] data = phone.split(","); // Adding phones
        JSONArray phones = new JSONArray();
        for (int i = 0; i < data.length; i++) {
            phones.add(data[i]);
        }
        requestBody.put("phone", phones);

        System.out.println(requestBody);

        given().
                body(requestBody.toJSONString()).
        when().
                post(baseUrl + "/v1/shippers").
        then().
                log().all().
                statusCode(200);
    }

    // Delete shipper
    @Test
    public void deleteShipper(){
        GetAllShippers get = new GetAllShippers();
        get.getAllShippers();

        when().
                post(baseUrl+"/v1/shippers/").
        then().
                log().all().
                statusCode(200);

        //when().get(baseUrl+"/v1/shippers/"+shipperId).then().statusCode(404);
    }
}
