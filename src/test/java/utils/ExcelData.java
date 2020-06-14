package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelData {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    // Constructor
    public ExcelData(String excelPath, String sheetName) {
        try {
            workbook = new XSSFWorkbook(excelPath);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methods
    public int rowCount() {  //
        return sheet.getPhysicalNumberOfRows();
    }

    public int colCount() {
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }

    public Object getCellData(int rowNum, int colNum) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
    }
}
