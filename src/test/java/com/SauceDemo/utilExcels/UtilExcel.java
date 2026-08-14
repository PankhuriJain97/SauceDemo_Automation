package com.SauceDemo.utilExcels;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class UtilExcel {

    public static Object[][] getTestData(String fileName, String sheetName) {
        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/testdata/" + fileName;

        Object[][] data = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            int totalRows = sheet.getLastRowNum();
            int totalColumns = sheet.getRow(0).getLastCellNum();

            data = new Object[totalRows][totalColumns];
            for (int i = 0; i < totalRows; i++) {
                for (int j = 0; j < totalColumns; j++) {


                    // First row email, password -> column name - skip - header
                    data[i][j] = sheet.getRow(i + 1).getCell(j);

                    if(data[i][j] != null)
                    {
                        data[i][j] = data[i][j].toString();
                    }

                    else
                    {
                        data[i][j] = "";
                    }




                }
            }
        } catch (IOException e) {
            System.out.println("File not Found");
        }


        return data;
    }
}
