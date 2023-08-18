package org.cvschools.WebApplication.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import org.cvschools.WebApplication.entities.ExportEmployee;
import org.cvschools.WebApplication.entities.ImportedEmployee;
import org.springframework.web.multipart.MultipartFile;


/*
 * helper class for converting spreadsheet to dto objects
 */

public class ExcelHelper {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "403b Loan", "Staff Social Security Number", "Staff Id", "Last Name", "First Name", "Middle Initial", "Birth Date", "Hire Date", 
                              "Termination Date", "Email", "Phone Number", "Address1", "Address2", "City", "State", "Zip" };
  static String SHEET = "403bFile"; //verify sheet name
  static String[] outHeaders = {"Staff ID", "Staff Social Security Number","Last Name", "First Name", "Middle Initial", "Birth Date", "Hire Date", 
                              "Termination Date", "Email", "Phone Number", "Address1", "Address2", "City", "State", "Zip"};
  static String outSheet = "Sheet1";

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
            case 2:
                employee.setStaffId(currentCell.getStringCellValue());            
                break;

            case 1:
                employee.setSsn(currentCell.getStringCellValue());            
                break;

            case 3:
                employee.setLastName(currentCell.getStringCellValue());            
                break;

            case 4:
                employee.setFirstName(currentCell.getStringCellValue());            
                break;

            case 5:
                employee.setMiddleInitial(currentCell.getStringCellValue());            
                break;

            case 6:
                employee.setBirthDate(currentCell.getStringCellValue());            
                break;

            case 7:
                employee.setHireDate(currentCell.getStringCellValue());            
                break;

            case 8:
                employee.setTerminationDate(currentCell.getStringCellValue());            
                break;

            case 9:
                employee.setEmail(currentCell.getStringCellValue());            
                break;

            case 10:
                employee.setPhoneNumber(currentCell.getStringCellValue());            
                break;

            case 11:
                employee.setAddress1(currentCell.getStringCellValue());            
                break;

            case 12:
                employee.setAddress2(currentCell.getStringCellValue());            
                break;

            case 13:
                employee.setCity(currentCell.getStringCellValue());            
                break;

            case 14:
                employee.setState(currentCell.getStringCellValue());            
                break;

            case 15:
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

  public static ByteArrayInputStream exportToExcel(List<ExportEmployee> employees){
    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
      Sheet sheet = workbook.createSheet(outSheet);

      // Header
      Row headerRow = sheet.createRow(0);

      for (int col = 0; col < outHeaders.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(outHeaders[col]);
      }

      int rowIdx = 1;
      for (ExportEmployee e : employees) {
        Row row = sheet.createRow(rowIdx++);

        row.createCell(0).setCellValue(e.getStaffId());
        row.createCell(1).setCellValue(e.getSsn());
        row.createCell(2).setCellValue(e.getLastName());
        row.createCell(3).setCellValue(e.getFirstName());
        row.createCell(4).setCellValue(e.getMiddleInitial());
        row.createCell(5).setCellValue(e.getBirthDate());
        row.createCell(6).setCellValue(e.getHireDate());
        row.createCell(7).setCellValue(e.getTerminationDate());
        row.createCell(8).setCellValue(e.getEmail());
        row.createCell(9).setCellValue(e.getPhoneNumber());
        row.createCell(10).setCellValue(e.getAddress1());
        row.createCell(11).setCellValue(e.getAddress2());
        row.createCell(12).setCellValue(e.getCity());
        row.createCell(13).setCellValue(e.getState());
        row.createCell(14).setCellValue(e.getZip());
        
      }

      workbook.write(out);
      workbook.close();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    }
    
  }
}