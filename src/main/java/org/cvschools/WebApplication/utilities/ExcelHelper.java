package org.cvschools.WebApplication.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cvschools.WebApplication.entities.ImportedEmployee;
import org.springframework.web.multipart.MultipartFile;


/*
 * helper class for converting spreadsheet to dto objects
 */

public class ExcelHelper {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "StaffId", "Title", "Description", "Published" }; //replace with actual file headers
  static String SHEET = "Sheet1"; //verify sheet name

  public static boolean hasExcelFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<ImportedEmployee> excelToDto(InputStream is) {
    try {
      Workbook workbook = new XSSFWorkbook(is);

      Sheet sheet = workbook.getSheet(SHEET);
      Iterator<Row> rows = sheet.iterator();

      List<ImportedEmployee> employees = new ArrayList<ImportedEmployee>();

      int rowNumber = 0;
      while (rows.hasNext()) {
        Row currentRow = rows.next();

        // skip header
        if (rowNumber == 0) {
          rowNumber++;
          continue;
        }

        Iterator<Cell> cellsInRow = currentRow.iterator();

        ImportedEmployee employee = new ImportedEmployee();

        int cellIdx = 0;
        while (cellsInRow.hasNext()) {
          Cell currentCell = cellsInRow.next();

            //set fields based on cell values
            switch (cellIdx) {
            case 0:
                employee.setStaffId(currentCell.getStringCellValue());            
                break;

            case 1:
                employee.setSsn(currentCell.getStringCellValue());            
                break;

            case 2:
                employee.setLastName(currentCell.getStringCellValue());            
                break;

            case 3:
                employee.setFirstName(currentCell.getStringCellValue());            
                break;

            case 4:
                employee.setMiddleInitial(currentCell.getStringCellValue());            
                break;

            case 5:
                employee.setBirthDate(currentCell.getStringCellValue());            
                break;

            case 6:
                employee.setHireDate(currentCell.getStringCellValue());            
                break;

            case 7:
                employee.setTerminationDate(currentCell.getStringCellValue());            
                break;

            case 8:
                employee.setEmail(currentCell.getStringCellValue());            
                break;

            case 9:
                employee.setPhoneNumber(currentCell.getStringCellValue());            
                break;

            case 10:
                employee.setAddress1(currentCell.getStringCellValue());            
                break;

            case 11:
                employee.setAddress2(currentCell.getStringCellValue());            
                break;

            case 12:
                employee.setCity(currentCell.getStringCellValue());            
                break;

            case 13:
                employee.setState(currentCell.getStringCellValue());            
                break;

            case 14:
                employee.setZip(currentCell.getStringCellValue());            
                break;

            default:
                break;
        }

          cellIdx++;
        }

        employees.add(employee);
      }

      workbook.close();

      return employees;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    }
  }
}
