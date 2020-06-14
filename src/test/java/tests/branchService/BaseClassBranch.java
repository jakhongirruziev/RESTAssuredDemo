package tests.branchService;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import utils.ExcelData;

import static io.restassured.RestAssured.given;

public class BaseClassBranch {
    static String baseUrl = "https://api.delever.uz";
    static String excelPath = "./data/branches.xlsx";
    static String sheetName = "Sheet1";
    static String branchName;
    static String phoneNumber;
    static String branchId;
    static String branchUsername;
    static String branchAddress;

    // *********************** Data providers **************************
    // Gets data from excel file
    @DataProvider(name = "excelBranches")
    public Object[][] getData(){
        ExcelData excelData = new ExcelData(excelPath, sheetName);
        Object[][] data = new Object[excelData.rowCount()-1][excelData.colCount()];
        try {
        for (int i=1; i<excelData.rowCount(); i++){
            for (int j=0; j<excelData.colCount(); j++){
                data[i-1][j] = excelData.getCellData(i, j);
            }
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    // Gets random data
    @DataProvider(name = "randomBranches")
    public Object[][] getRandom(){
        Object[][] data = new Object[1][6];
        // Gets random user
        Response response = given().get("https://randomuser.me/api").then().extract().response();
        // Gets random number
        int random = (int)(10000000+ (Math.random() * (100000000 - 10000000)));
        data[0][0] = response.path("results.location.street.name").toString().replace("[", "").replace("]", "");
        data[0][1] = "+9989" + random;
        data[0][2] = response.path("results.login.username").toString().replace("[", "").replace("]", "");
        data[0][3] = response.path("results.location.city").toString().replace("[", "").replace("]", "");
        data[0][4] = response.path("results.location.coordinates.latitude").toString().replace("[", "").replace("]", "");
        data[0][5] = response.path("results.location.coordinates.longitude").toString().replace("[", "").replace("]", "");

        return data;
    }
}
