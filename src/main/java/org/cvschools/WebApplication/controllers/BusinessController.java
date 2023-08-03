package org.cvschools.WebApplication.controllers;

import java.util.List;

import org.cvschools.WebApplication.entities.ExportEmployee;
import org.cvschools.WebApplication.entities.ReportableTerminations;
import org.cvschools.WebApplication.models.ReportableForm;
import org.cvschools.WebApplication.models.UploadForm;
import org.cvschools.WebApplication.services.ExcelService;
import org.cvschools.WebApplication.services.BuisinessService;
import org.cvschools.WebApplication.utilities.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BusinessController {
    
    @Autowired
    ExcelService fileService;

    @Autowired
    BuisinessService service;

    public Boolean fileUploaded;
    public Boolean downloadFileReady;


    /*
     * mappings for main 403(b) page
     */
    @GetMapping("/403b")
    public String get403b(Model model, @RequestParam(required = false) Boolean fileUploaded,
                            @RequestParam(required = false) Boolean downloadFileReady){                        

        //check for values in fileUploaded and downloadFileReady
        
        model.addAttribute("uploadForm", new UploadForm());
        model.addAttribute("fileUploaded", fileUploaded);
        model.addAttribute("downloadFileReady", downloadFileReady);
        return "403b";
    }

    @PostMapping("/403b")
    public String post403b(Model model, @ModelAttribute UploadForm uploadForm){
        
        
        //check to verify that file is an excel file
        if (ExcelHelper.hasExcelFormat(uploadForm.getUploadFile())){
            
            //if excel file attempt to save
            try{
                //clear any current data for clean slate
                service.clearImportedData();
                service.clearActiveStaff();
                service.clearReportableTerminations();
                service.clearExportData();

                fileService.save(uploadForm.getUploadFile());
                fileUploaded = true;
                downloadFileReady = false;

                service.createReportableTerminations();
                
                model.addAttribute("fileUploaded", fileUploaded);
                model.addAttribute("downloadFileReady", downloadFileReady);
                return "403b";
            } catch (Exception e) {
                fileUploaded = false;
                downloadFileReady = false;
                
                model.addAttribute("error", "Could Not Upload the File");
                model.addAttribute("fileUploaded", fileUploaded);
                model.addAttribute("downloadFileReady", downloadFileReady);
                return "403b";
            }            
        }
        
        //if not an excel file return error
        fileUploaded = false;
        downloadFileReady = false;
        model.addAttribute("error", "Please upload an excel file!");
        model.addAttribute("fileUploaded", fileUploaded);
        model.addAttribute("downloadFileReady", downloadFileReady);
        return "403b";
    }


    //still needs work to actually create and download the excel file
    @GetMapping("/403b/download")
    public String getUploadFile(Model model){
        try{
            service.createUploadTable();

            List<ExportEmployee> employees = fileService.getUploadData();

            if(employees.isEmpty()){
                model.addAttribute("error", "Please upload new data first");
                return "403b";
            }

            model.addAttribute("file", employees);

            //call options to cleanup
            service.updateReportedTerminations();
            service.clearReportableTerminations();
            service.clearActiveStaff();
            service.clearImportedData();
            return "403b";
        } catch (Exception e){
            model.addAttribute("error", e);
            return "403b";
        }
    }

    
    /*
     * mappings for reportable terminations
     */
    
    @GetMapping("/ReportableTerminations")
    public String getReportableTerminations(Model model){                       
        //get list of reportable terminations
        List<ReportableTerminations> reportable = service.getReportableTerminations();

        model.addAttribute("reportableTerminations", reportable);

        return "ReportableTerminations";
    }


    @PostMapping("ReportableTerminations")
    public String updateReprotableTerminations(Model model, @ModelAttribute ReportableForm form){        
        //set fileUploaded to true
        fileUploaded = true;

        //update reportable terminations
        service.updateReportableTerminations(form.reportableTerminations);
                
        //code to create uploadFile
        service.createUploadTable();
        
        //set downloadFileReady to true
        downloadFileReady = true;


        model.addAttribute("fileUploaded", fileUploaded);
        model.addAttribute("downloadFileReady", downloadFileReady);

        return "403b";
    }
}
