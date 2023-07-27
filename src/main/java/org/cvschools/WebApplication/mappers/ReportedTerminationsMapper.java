package org.cvschools.WebApplication.mappers;

import org.cvschools.WebApplication.entities.ReportedTerminations;
import org.cvschools.WebApplication.models.ReportedTerminationsDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ReportedTerminationsMapper {
    
    ReportedTerminations dtoToReported(ReportedTerminationsDTO dto);

    ReportedTerminationsDTO reportedToDto(ReportedTerminations employee);
}
