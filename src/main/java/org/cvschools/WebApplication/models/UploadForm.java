package org.cvschools.WebApplication.models;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadForm {
    
    private MultipartFile uploadFile;
}
