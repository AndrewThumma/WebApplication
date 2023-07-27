package org.cvschools.WebApplication.mappers;

import org.cvschools.WebApplication.models.ExportedEmployeeDTO;
import org.cvschools.WebApplication.entities.ExportEmployee;
import org.mapstruct.Mapper;

@Mapper
public interface ExportedEmployeeMapper {
    
    ExportEmployee exportedDtoToExported(ExportedEmployeeDTO dto);

    ExportedEmployeeDTO exportedToExportedDto(ExportEmployee employee);
}
