package com.demo.dnb.controller;

import com.demo.dnb.entity.UserInfo;
import com.demo.dnb.service.ApplicantionService;
import com.demo.dnb.entity.ApplicantInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application/v1")
public class ApplicationController {

    private static ApplicantionService applicantionService;

    @Autowired
    public ApplicationController(ApplicantionService applicantionService) {
        this.applicantionService = applicantionService;
    }

    @GetMapping("/check")
    public String healthCheck() {
        return "Check Okay from loanappen";
    }

    @PostMapping("/data")
    //@PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity submitApplicationInformation(@RequestBody ApplicantInformation applicantInformation) {
       try {
        String result = applicantionService.submitApplication(applicantInformation);
        if (result != null && !result.contains("INVALID")) {
            return ResponseEntity.status(HttpStatus.OK).body("Application Sent to Advisor , " +
                    "your application ID is -> "+result);
        } else if(result != null && result.contains("INVALID")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("EQUITY AMOUNT IS NOT 15 % of LOAN Amount");
        }}catch (DataAccessException e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Oops!!! Something went wrong");

    }

    @GetMapping("/allApplications")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<?>> fetchApplications() {
        return ResponseEntity.status(200).body(applicantionService.fetchApplications());
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return applicantionService.addUser(userInfo);
    }

}
