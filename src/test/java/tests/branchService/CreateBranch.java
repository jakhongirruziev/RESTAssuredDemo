package tests.branchService;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateBranch extends BaseClassBranch {
    String url = "jdbc:postgresql://api.delever.uz:5434/deleverdb";
    String usr = "delever";
    String pass = "CnuKqgBQgeq9rzUx";
    Connection connection;
    Statement statement;
    String idCreated;

    @BeforeClass
    public void setup(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, usr, pass);
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CreateCategory branches
    @Test(dataProvider = "randomBranches")
    public void createBranch(String name, String phone, String username, String address, String lat, String lon) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("address", address);
        requestBody.put("destination", name + ", " + address);
        JSONObject location = new JSONObject(); // Lat long
        location.put("lat", lat);
        location.put("long", lon);
        requestBody.put("location", location); // put lat long body to location body
        requestBody.put("name", "Oqtepa-Lavash " + address);
        requestBody.put("password", "delever123");

        String[] data = phone.split(","); // Adding phones
        JSONArray phones = new JSONArray();
        for (int i = 0; i < data.length; i++) {
            phones.add(data[i]);
        }

        requestBody.put("phone", phones);
        requestBody.put("shipper_id", "45d47b2b-f1ce-448b-af73-37ec0dd8addd");
        requestBody.put("username", username);

        System.out.println(requestBody);

        Response response =
        given().
                config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"))).
                body(requestBody.toJSONString()).
        when().
                post(baseUrl + "/v1/branches").
        then().
                log().all().
                statusCode(200).
                // Asserts the response body
                body("name", equalTo("Oqtepa-Lavash " + address)).
                body("is_active", equalTo(true)).
                extract().response();
        idCreated = response.path("id").toString();
        System.out.println(idCreated);
    }

   /* // CreateCategory from excel file
    @Test(dataProvider = "excelBranches")
    public void createFromExcelFile(String name, String phone, String username, String address) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("address", address);
        requestBody.put("destination", "not given");
        JSONObject location = new JSONObject(); // Lat long
        location.put("lat", 41.2995);
        location.put("long", 69.2401);
        requestBody.put("location", location); // put lat long body to location body
        requestBody.put("name", name);
        requestBody.put("password", "123");

        String[] data = phone.split(","); // Adding phones
        JSONArray phones = new JSONArray();
        for (int i = 0; i < data.length; i++) {
            phones.add(data[i]);
        }

        requestBody.put("phone", phones);
        requestBody.put("shipper_id", "e70deff2-3446-4cc0-872e-79e78af432b9");
        requestBody.put("username", username);

        System.out.println(requestBody);

        given().
                config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().defaultContentCharset("UTF-8"))).
                contentType(ContentType.JSON).
                body(requestBody.toJSONString()).
        when().
                post(baseUrl + "/v1/branches").
        then().
                log().all().
                statusCode(200).
                // Asserts the response body
                body("name", equalTo(name)).
                body("is_active", equalTo(true)).
                extract().response();
    }
*/
    @Test
    public void tearDown(){
        try {
            // Delete created branch
            statement = connection.createStatement();
            statement.execute("DELETE FROM branches WHERE id='"+idCreated+"'");
            statement.close();
        }catch ( Exception e ){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }


}
