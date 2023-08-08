package org.cvschools.WebApplication.utilities;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cvschools.WebApplication.entities.ExportEmployee;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class ExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ExportEmployee> employees;
    

    public ExcelExporter(List<ExportEmployee> e){
        employees = e;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(){
        sheet = workbook.createSheet("Sheet1");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);

        //create header row cell by cell
        createCell(row, 0, "Staff ID", style);
        createCell(row, 1, "Staff Social Security Number", style);
        createCell(row, 2, "Last Name", style);
        createCell(row, 3, "First Name", style);
        createCell(row, 4, "Middle Initial", style);
        createCell(row, 5, "Birth Date", style);
        createCell(row, 6, "Hire DAte", style);
        createCell(row, 7, "Termination Date", style);
        createCell(row, 8, "Email", style);
        createCell(row, 9, "Phone Number", style);
        createCell(row, 10, "Address1", style);
        createCell(row, 11, "Address2", style);
        createCell(row, 12, "City", style);
        createCell(row, 13, "State", style);
        createCell(row, 14, "Zip", style);        
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style){
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if(value instanceof Integer){
            cell.setCellValue((Integer) value);
        }else if(value instanceof Boolean){
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    public void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        //interate through each record
        for (ExportEmployee e : employees){
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            //create data row cell by cell
            createCell(row, columnCount++, e.getStaffId(), style);
            createCell(row, columnCount++, e.getSsn(), style);
            createCell(row, columnCount++, e.getLastName(), style);
            createCell(row, columnCount++, e.getFirstName(), style);
            createCell(row, columnCount++, e.getMiddleInitial(), style);
            createCell(row, columnCount++, e.getBirthDate(), style);
            createCell(row, columnCount++, e.getHireDate(), style);
            createCell(row, columnCount++, e.getTerminationDate(), style);
            createCell(row, columnCount++, e.getEmail(), style);
            createCell(row, columnCount++, e.getPhoneNumber(), style);
            createCell(row, columnCount++, e.getAddress1(), style);
            createCell(row, columnCount++, e.getAddress2(), style);
            createCell(row, columnCount++, e.getCity(), style);
            createCell(row, columnCount++, e.getState(), style);
            createCell(row, columnCount++, e.getZip(), style);            
        }
    }

    public void export(HttpServletResponse response) throws IOException{
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream output = response.getOutputStream();
        workbook.write(output);
        workbook.close();
        
        output.close();
    }
}
