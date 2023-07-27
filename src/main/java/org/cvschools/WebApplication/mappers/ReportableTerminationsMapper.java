package org.cvschools.WebApplication.mappers;

import org.cvschools.WebApplication.entities.ReportableTerminations;
import org.cvschools.WebApplication.models.ReportableTerminationsDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ReportableTerminationsMapper {
    
    ReportableTerminations DtoToReportable(ReportableTerminationsDTO dto);

    ReportableTerminationsDTO reportableToDto(ReportableTerminations employee);
}
