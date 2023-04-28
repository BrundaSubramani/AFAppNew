/*
 * Added by Archana for Mobile Automation Testing
 */

package nttdata.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class Excel {
    static Logger logger = LogManager.getLogger(Excel.class);
    private Configuration configuration;

    @Autowired
    public Excel(Configuration configuration) {
        this.configuration = configuration;
    }

    public String readExcelString(int rowNo, int colNo) {
        String filepath = configuration.getProperty("excel.path");
        FileInputStream fis = null;
        String value = null;
        try {
            fis = new FileInputStream(filepath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(rowNo);
            Cell cell = row.getCell(colNo);
            if (cell != null) {
                value = String.valueOf((sheet.getRow(rowNo).getCell(colNo)));
            }
        } catch (Exception e) {
            logger.debug(e.toString());
        }
        return value;
    }

    public int readExcelInteger(int rowNo, int colNo) {
        String filepath = configuration.getProperty("excel.path");
        FileInputStream fis = null;
        int value = 0;
        try {
            fis = new FileInputStream(filepath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(rowNo);
            Cell cell = row.getCell(colNo);
            if (cell != null) {
                double myDb = cell.getNumericCellValue();
                value = (int) myDb;
            }
        } catch (Exception e) {
            logger.debug(e.toString());
        }
        return value;
    }

    public  void writeExcel(int rowNo, int ColNo, String value) throws IOException {
       String filepath = configuration.getProperty("excel.path");
        //String filepath = "src//test//resources//excelFiles//TestData_PB.xlsx";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filepath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
           XSSFSheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(rowNo);
            Cell cell = row.createCell(ColNo);
            cell.setCellType(cell.CELL_TYPE_STRING);
            cell.setCellValue(value);
            FileOutputStream fos = new FileOutputStream(filepath);
            workbook.write(fos);
            fos.close();
            System.out.println("Try");
        } catch (Exception e) {
            logger.debug(e.toString());
       }
    }

    public int getRowCount() {
        String filepath = configuration.getProperty("excel.path");
        FileInputStream fis = null;
        int rowCount = 0;
        try {
            fis = new FileInputStream(filepath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Test");
            rowCount = sheet.getLastRowNum();
        } catch (Exception e) {
            logger.debug(e.toString());
        }
        return rowCount;
    }

   /* private String getCellValueString(Cell cell) {
        String value="";
        if(cell!=null) {
            switch(cell.getCellType()){
                case 0:
                    value= BigDecimal.valueOf(
                            cell.getNumericCellValue()).toPlainString();
                    break;
                case 1:
                    value=String.valueOf(cell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    value="";
                    break;
            }
        } *//*else {
            logger.error("Cell is null");
        }*//*
        return value.trim();
    }*/
}
