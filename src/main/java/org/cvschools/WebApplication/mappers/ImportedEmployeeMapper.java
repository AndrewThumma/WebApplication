package org.cvschools.WebApplication.mappers;

import org.cvschools.WebApplication.entities.ImportedEmployee;
import org.cvschools.WebApplication.models.ImportedEmployeeDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ImportedEmployeeMapper {
    
    ImportedEmployee impotedDtoToImported(ImportedEmployeeDTO dto);

    ImportedEmployeeDTO importedToImportedDto(ImportedEmployee employee);
}
