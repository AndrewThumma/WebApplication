package org.cvschools.WebApplication.controllers;

import org.cvschools.WebApplication.models.UploadForm;
import org.cvschools.WebApplication.services.ExcelService;
import org.cvschools.WebApplication.utilities.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BusinessController {
    
    @Autowired
    ExcelService fileService;

    public Boolean fileUploaded;

    @GetMapping("/403b")
    public String get403b(Model model){
        fileUploaded = false;

        model.addAttribute("fileUploaded", fileUploaded);
        return "403b";
    }

    @PostMapping("/403b")
    public String post403b(Model model, @ModelAttribute UploadForm uploadForm){
        //check to verify that file is an excel file
        if (ExcelHelper.hasExcelFormat(uploadForm.getUploadFile())){
            
            //if excel file attempt to save
            try{
                fileService.save(uploadForm.getUploadFile());
                fileUploaded = true;
                
                model.addAttribute("fileUploaded", fileUploaded);
                return "403b";
            } catch (Exception e) {
                fileUploaded = false;
                
                model.addAttribute("error", "Could Not Upload the File");
                model.addAttribute("fileUploaded", fileUploaded);
                return "403b";
            }            
        }
        
        //if not an excel file return error
        fileUploaded = false;
        model.addAttribute("error", "Please upload an excel file!");
        model.addAttribute("fileUploaded", fileUploaded);
        return "403b";
    }
}
