package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * 
@author: Saurabh Shukla

Class explanation: This ReportingStructure Controller class is navigation class for read method. 

*/


@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    

    /*
     * 
    @author: Saurabh Shukla

    Method explanation: this method read the reporting value from passed id from user. 

    @param:  id              : Employee id for reporting record
    @return: value           : This method returns the read value of reporting record from passed value


    */
    
    @GetMapping("/reporting/{id}")
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return reportingStructureService.read(id);
    }

  
}
