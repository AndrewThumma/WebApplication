package org.cvschools.WebApplication.controllers;

import java.util.List;

import org.cvschools.WebApplication.entities.ExportEmployee;
import org.cvschools.WebApplication.models.ReportableForm;
import org.cvschools.WebApplication.models.ReportedForm;
import org.cvschools.WebApplication.models.UploadForm;
import org.cvschools.WebApplication.services.ExcelService;
import org.cvschools.WebApplication.services.BuisinessService;
import org.cvschools.WebApplication.utilities.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BusinessController {
    
    @Autowired
    ExcelService fileService;

    @Autowired
    BuisinessService service;

    public Boolean fileUploaded;    

    /*
     * mappings for main 403(b) page
     */
    @GetMapping("/403b")
    public String get403b(Model model){                        
        
        if(service.getReportableTerminations().isEmpty()){
            fileUploaded = false;
        }else{
            fileUploaded = true;
        }        

        model.addAttribute("uploadForm", new UploadForm());
        model.addAttribute("fileUploaded", fileUploaded);
        
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

                service.createReportableTerminations();
                
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


       
    /*
     * mappings for reportable terminations
     */
    
    @GetMapping("/ReportableTerminations")
    public String getReportableTerminations(Model model){                       
        //get list of reportable terminations                
        ReportableForm form = new ReportableForm();
        form.setReportableTerminations(service.getReportableTerminations());

        model.addAttribute("form", form);        

        return "ReportableTerminations";
    }


    @PostMapping("ReportableTerminations")
    public String updateReprotableTerminations(Model model, @ModelAttribute ReportableForm form){        
        //set fileUploaded to true
        fileUploaded = true;

        //update reportable terminations
        service.updateReportableTerminations(form.getReportableTerminations());


        model.addAttribute("uploadForm", new UploadForm());
        model.addAttribute("fileUploaded", fileUploaded);        

        return "403b";
    }


    /*
     * mappings for reported terminations
     */

     @GetMapping("/ReportedTerminations")
     public String viewReportedTerminations(Model model){
        //get list of reported terminations
        ReportedForm form = new ReportedForm();
        form.setReported(service.getReportedTerminations());

        if(service.getReportableTerminations().isEmpty()){
            fileUploaded = false;
        }else {
            fileUploaded = true;
        }

        model.addAttribute("fileUploaded", fileUploaded);
        model.addAttribute("form", form);
        
        return "ReportedTerminations";
     }

     @PostMapping("/ReportedTerminations/{id}")
     public String updateReportedTerminations(Model model, @PathVariable String id){
        //remove selected employee from terminated list
        service.deleteReportedById(id);

        //get updated list
        ReportedForm form = new ReportedForm();
        form.setReported(service.getReportedTerminations());

        if(service.getReportableTerminations().isEmpty()){
            fileUploaded = false;
        }else{
            fileUploaded = true;
        }

        model.addAttribute("fileUploaded", fileUploaded);
        model.addAttribute("form", form);
        return "ReportedTerminations";
     }

     /*
      * mappings for getting download file
      */

    //still needs work to actually create and download the excel file
    @GetMapping("/403b/download")
    public String getUploadFile(Model model){
        try{
            service.createUploadTable();

            List<ExportEmployee> employees = fileService.getUploadData();

            if(employees.isEmpty()){
                model.addAttribute("error", "Please upload new data first");
                model.addAttribute("uploadForm", new UploadForm());
                return "403b";
            }            

            //call options to cleanup            
            service.updateReportedTerminations();
            service.clearReportableTerminations();
            service.clearActiveStaff();
            service.clearImportedData();
            fileUploaded = false;            

            model.addAttribute("file", employees);
            model.addAttribute("uploadForm", new UploadForm());
            model.addAttribute("fileUploaded", fileUploaded);

            return "403b";
        } catch (Exception e){
            fileUploaded = true;
            
            model.addAttribute("fileUploaded", e);
            model.addAttribute("error", e);
            model.addAttribute("uploadForm", new UploadForm());

            return "403b";
        }
    }

    /*
     * mapping to reset all
     */
    @GetMapping("/403b/clear")
    public String reset(Model model){
        fileUploaded = false;

        service.clearActiveStaff();
        service.clearExportData();
        service.clearImportedData();
        service.clearReportableTerminations();

        model.addAttribute("uploadForm", new UploadForm());
        model.addAttribute("fileUploaded", fileUploaded);

        return "403b";
    }
}
