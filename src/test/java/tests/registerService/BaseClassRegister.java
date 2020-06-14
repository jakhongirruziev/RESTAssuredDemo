package tests.registerService;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import utils.ExcelData;

import static io.restassured.RestAssured.given;

public class BaseClassRegister {
    String baseUrl = "https://api.delever.uz";
    String excelPath = "./data/users.xlsx";
    String sheetName = "Sheet1";
    String Phone;

    // *********************** Data providers *********************
    // For creating users through excel file
    @DataProvider(name = "excelRegisterData")
    public Object[][] getData(){
        ExcelData excelData = new ExcelData(excelPath, sheetName);
        Object[][] data = new Object[excelData.rowCount()-1][2];

        for (int i=1; i<excelData.rowCount(); i++){
            for (int j=0; j<2; j++){
                data[i-1][j] = excelData.getCellData(i, j);
            }
        }
        return data;
    }

    // For getting random user
    @DataProvider(name = "randomUser")
    public Object[][] getRandomUser(){
        Object[][] data = new Object[1][2];
        // Gets random user
        Response response = given().get("https://randomuser.me/api").then().extract().response();
        // Gets random number
        int random = (int)(10000000+ (Math.random() * (100000000 - 10000000)));
        data[0][0] = response.path("results.name.first").toString().replace("[", "").replace("]", "");
        data[0][1] = "+9989" + random;
        return data;
    }
}
