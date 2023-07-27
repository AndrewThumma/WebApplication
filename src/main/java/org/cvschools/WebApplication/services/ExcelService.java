package org.cvschools.WebApplication.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.cvschools.WebApplication.entities.ExportEmployee;
import org.cvschools.WebApplication.entities.ImportedEmployee;
import org.cvschools.WebApplication.mappers.ExportedEmployeeMapper;
import org.cvschools.WebApplication.mappers.ImportedEmployeeMapper;
import org.cvschools.WebApplication.models.ExportedEmployeeDTO;
import org.cvschools.WebApplication.models.ImportedEmployeeDTO;
import org.cvschools.WebApplication.repositories.ExportEmployeeRepository;
import org.cvschools.WebApplication.repositories.ImportedEmployeeRepository;
import org.cvschools.WebApplication.utilities.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelService {

    @Autowired
    ImportedEmployeeRepository repo;

    @Autowired
    ExportEmployeeRepository exportRepo;

    @Autowired
    ImportedEmployeeMapper mapper;

    @Autowired
    ExportedEmployeeMapper exportMapper;

    public void save(MultipartFile file){
        try{
            repo.deleteAll();
            
            List<ImportedEmployee> employees = new ArrayList<>();

            List<ImportedEmployeeDTO> dtoEmployees = ExcelHelper.excelToDto(file.getInputStream());

            for (ImportedEmployeeDTO dto : dtoEmployees) {
                ImportedEmployee e = mapper.impotedDtoToImported(dto);
                employees.add(e);
            }

            repo.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<ExportedEmployeeDTO> getUploadData(){
        List<ExportEmployee> employees = exportRepo.findAll();

        List<ExportedEmployeeDTO> dtoEmployees = new ArrayList<>();
        
        for (ExportEmployee e : employees) {
            ExportedEmployeeDTO dto = exportMapper.exportedToExportedDto(e);
            dtoEmployees.add(dto);
        }
        
        return dtoEmployees;
    }
    
}
