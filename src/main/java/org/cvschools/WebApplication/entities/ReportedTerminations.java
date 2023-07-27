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
@Table(name = "ReportedTerminations", schema = "403b")
public class ReportedTerminations {
    
    @Id
    @Column(length = 255, columnDefinition = "nvarchar(255)", nullable = false, name = "staffId")
    private String staffId;
}
