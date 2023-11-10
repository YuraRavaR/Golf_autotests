package golf.utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

import static golf.utilities.Constant.xlsxFileWithTestData;

public class Readxls {


    //read .xls file and use it as data provider of "valid log in credentials" (use first page of the file with 2 columns: login and password
    public Object[][] readInvalidLoginCredentials() throws IOException {

        InputStream fis = getClass().getClassLoader().getResourceAsStream(xlsxFileWithTestData);
        assert fis != null;
        XSSFWorkbook workbook = new XSSFWorkbook(fis); //work env where we work with file
        XSSFSheet sheet = workbook.getSheet("loginnegativetests"); // choose the "list" name in .xls
        int totalRows = sheet.getLastRowNum();
        int totalColumns = sheet.getRow(0).getPhysicalNumberOfCells() - 1; // "-1" - minus one column for the "explanation" field

        Object[][] obj = new Object[totalRows][totalColumns]; //organizing data into object
        for (int i = 0; i < totalRows; i++) {
            XSSFCell cell1 = sheet.getRow(i + 1).getCell(0);
            XSSFCell cell2 = sheet.getRow(i + 1).getCell(1);
            XSSFCell cell3 = sheet.getRow(i + 1).getCell(2);

            obj[i][0] = (cell1 != null) ? cell1.toString() : "";
            obj[i][1] = (cell2 != null) ? cell2.toString() : "";
            obj[i][2] = (cell3 != null) ? cell3.toString() : "";
        }
        return obj;
    }

    public Object[][] readValidLoginCredentials() throws IOException {

        InputStream fis = getClass().getClassLoader().getResourceAsStream(xlsxFileWithTestData);
        assert fis != null;
        XSSFWorkbook workbook = new XSSFWorkbook(fis); //work env where we work with file
        XSSFSheet sheet = workbook.getSheet("loginvalidcredentials"); // choose the "list" name in .xls
        int totalRows = sheet.getLastRowNum();
        int totalColumns = sheet.getRow(0).getPhysicalNumberOfCells() - 1; // "-1" - minus one column for the "explanation" field

        Object[][] obj = new Object[totalRows][totalColumns]; //organizing data into object
        for (int i = 0; i < totalRows; i++) {
            obj[i][0] = sheet.getRow(i + 1).getCell(0).toString();
            obj[i][1] = sheet.getRow(i + 1).getCell(1).toString();

        }
        return obj;
    }

    public Object[][] readInvalidRegisterCredentials() throws IOException {

        InputStream fis = getClass().getClassLoader().getResourceAsStream(xlsxFileWithTestData);
        assert fis != null;
        XSSFWorkbook workbook = new XSSFWorkbook(fis); //work env where we work with file
        XSSFSheet sheet = workbook.getSheet("registerNegativeTests"); // choose the "list" name in .xls
        int totalRows = sheet.getLastRowNum();
        int totalColumns = sheet.getRow(0).getPhysicalNumberOfCells() - 1; // "-1" - minus one column for the "explanation" field

        Object[][] obj = new Object[totalRows][totalColumns]; //organizing data into object
        for (int i = 0; i < totalRows; i++) {
            XSSFCell cell1 = sheet.getRow(i + 1).getCell(0);
            XSSFCell cell2 = sheet.getRow(i + 1).getCell(1);
            XSSFCell cell3 = sheet.getRow(i + 1).getCell(2);
            XSSFCell cell4 = sheet.getRow(i + 1).getCell(3);
            XSSFCell cell5 = sheet.getRow(i + 1).getCell(4);

            obj[i][0] = (cell1 != null) ? cell1.toString() : "";
            obj[i][1] = (cell2 != null) ? cell2.toString() : "";
            obj[i][2] = (cell3 != null) ? cell3.toString() : "";
            obj[i][3] = (cell4 != null) ? cell4.toString() : "";
            obj[i][4] = (cell5 != null) ? cell5.toString() : "";
        }
        return obj;
    }

    public Object[][] readInvalidChangePasswordData() throws IOException {

        InputStream fis = getClass().getClassLoader().getResourceAsStream(xlsxFileWithTestData);
        assert fis != null;
        XSSFWorkbook workbook = new XSSFWorkbook(fis); //work env where we work with file
        XSSFSheet sheet = workbook.getSheet("changePasswordNegativeTests"); // choose the "list" name in .xls
        int totalRows = sheet.getLastRowNum();
        int totalColumns = sheet.getRow(0).getPhysicalNumberOfCells() - 1; // "-1" - minus one column for the "explanation" field

        Object[][] obj = new Object[totalRows][totalColumns]; //organizing data into object
        for (int i = 0; i < totalRows; i++) {
            XSSFCell cell1 = sheet.getRow(i + 1).getCell(0);
            XSSFCell cell2 = sheet.getRow(i + 1).getCell(1);
            XSSFCell cell3 = sheet.getRow(i + 1).getCell(2);
            XSSFCell cell4 = sheet.getRow(i + 1).getCell(3);


            obj[i][0] = (cell1 != null) ? cell1.toString() : "";
            obj[i][1] = (cell2 != null) ? cell2.toString() : "";
            obj[i][2] = (cell3 != null) ? cell3.toString() : "";
            obj[i][3] = (cell4 != null) ? cell4.toString() : "";

        }
        return obj;
    }
}