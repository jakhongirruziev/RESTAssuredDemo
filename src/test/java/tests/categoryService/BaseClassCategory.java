package tests.categoryService;

import org.testng.annotations.DataProvider;
import utils.ExcelData;

public class BaseClassCategory {
    String baseUrl = "https://api.test.delever.uz";
    String excelPath = "./data/category.xlsx";
    String sheetName = "Sheet1";

    // *********************  Provider ************************
    @DataProvider(name = "data")
    public Object[][] getData(){
        ExcelData excelData = new ExcelData(excelPath, sheetName);
        Object[][] data = new Object[excelData.rowCount()-1][excelData.colCount()];
        for (int i=1; i<excelData.rowCount(); i++){
            for (int j=0; j<excelData.colCount(); j++){
                data[i-1][j] = excelData.getCellData(i, j);
            }
        }
        return data;
    }
}
