package org.cvschools.WebApplication.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ReportName", schema = "dv")
public class ReportName {
    
    @Id
    @Column(columnDefinition = "INT", nullable = false, name = "id")
    private Integer id;

    @Column(columnDefinition = "nvarchar(50)", nullable = false, name = "reportName")
    private String reportName;
}
