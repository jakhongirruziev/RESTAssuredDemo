package tests.courierService;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import utils.ExcelData;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class BaseClassCourier {

    public static String baseUrl = "https://api.delever.uz";
    public static String excelPath = "./data/couriers.xlsx";
    public static String sheetName = "Sheet1";
    public static String firstName;
    public static String lastName;
    public static String phoneNumber;
    public static String courierId;
    public static String courierToken;

    // ************************* Data providers ***********************************
    // For creating couriers through excel file
    @DataProvider(name = "excelCouriers")
    public Object[][] getData() {
        ExcelData excelData = new ExcelData(excelPath, sheetName);
        Object[][] data = new Object[excelData.rowCount() - 1][4];

        for (int i = 1; i < excelData.rowCount(); i++) {
            for (int j = 0; j < 4; j++) {
                data[i - 1][j] = excelData.getCellData(i, j);
            }
        }
        return data;
    }

    // For getting random user
    @DataProvider(name = "randomUser")
    public Object[][] getRandomUser() {
        Object[][] data = new Object[1][3];
        // Gets random user
        Response response = given().get("https://randomuser.me/api").then().extract().response();
        // Gets random number
        int random = (int) (10000000 + (Math.random() * (100000000 - 10000000)));
        data[0][0] = response.path("results.name.first").toString().replace("[", "").replace("]", "");
        data[0][1] = response.path("results.name.last").toString().replace("[", "").replace("]", "");
        data[0][2] = "+9989" + random;
        return data;
    }

    // For getting random user
    @DataProvider(name = "courierDetails")
    public Object[][] getRandomDetails() {
        Object[][] data = new Object[1][5];
        // Gets random user
        Response response = given().get("https://randomuser.me/api").then().extract().response();
        // Gets random number
        data[0][0] = response.path("results.location.street.name").toString().replace("[", "").replace("]", "");
        data[0][1] = 2000 - new Random().nextInt(30);
        data[0][2] = response.path("results.gender").toString().replace("[", "").replace("]", "");
        int ran = new Random().nextInt(10);
        data[0][3] = 2020 - ran;
        data[0][4] = 2030 - ran;

        return data;
    }
}
