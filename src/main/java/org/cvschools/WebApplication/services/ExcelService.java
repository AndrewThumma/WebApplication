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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final ImportedEmployeeRepository repo;

    private final ExportEmployeeRepository exportRepo;
    
    private final ImportedEmployeeMapper mapper;

    private final ExportedEmployeeMapper exportMapper;

    public void save(MultipartFile file){
        try{
            repo.deleteAll();        

            List<ImportedEmployee> employees = ExcelHelper.excelToDto(file.getInputStream());

            repo.saveAll(employees);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<ExportEmployee> getUploadData(){
        List<ExportEmployee> employees = exportRepo.findAll();
        
        return employees;
    }
    
}
