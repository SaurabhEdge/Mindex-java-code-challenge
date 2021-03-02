package com.mindex.challenge.controller;

/*
 * 
@author: Saurabh Shukla

Class explanation: This Compensation controller class is navigation class for read & create methods. 

*/

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private CompensationService compensationService;

    /*
     * 
    @author: Saurabh Shukla

    Method explanation: this method read the compensation value from passed id from user. 

    @param:  id              : Employee id for compensation record
    @return: value           : This method returns the read value of compensation record from passed value


    */
    @GetMapping("/compensation/{id}")
    public List<Compensation> read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return compensationService.read(id);
    }

    
    /*
     * 
    @author: Saurabh Shukla

    Method explanation: this method created the compensation value. 

    @param:  compensation    : Compensation object is passed to create value 
    @return: value           : This method returns the created value of compensation record from passed object

	*/
    
    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {

        return compensationService.create(compensation);
    }
}
