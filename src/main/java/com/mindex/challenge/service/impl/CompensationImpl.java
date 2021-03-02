package com.mindex.challenge.service.impl;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import com.mindex.challenge.DataBootstrap;
import java.io.FileOutputStream;
import java.util.List;
import java.util.ArrayList;

/*
 * 
@author: Saurabh Shukla

Class explanation: This compensation structure implementation class is implementation of read & create method
              

*/


@Service
public class CompensationImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DataBootstrap dbs;
    
	/*
	 * 
	@author: Saurabh Shukla

	method explanation: This method is implementation of create method to create Compensation structure of 
						employee with persistent feature.

	@param: Compensation    : Compensation object for Compensation structure record
	@return: Compensation    : Compensation object for compensation record 

	*/    
    
    
    
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);
        compensation.setId(UUID.randomUUID().toString());
        Employee employee = employeeRepository.findByEmployeeId(compensation.getEmployee().getEmployeeId());
        
        compensation.setEmployee(employee);
        compensationRepository.insert(compensation);
        //check for validation
        
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + compensation.getEmployee().getEmployeeId().toString());
        }
       
        //persist the new info in file
        
        String pa="C:\\Users\\Saurabh Shukla RIT\\Desktop\\mindex-java-code-challenge\\src\\main\\resources\\static\\compensation_database.json";
        try {
	        FileOutputStream fos = new FileOutputStream(pa,false);
	        dbs.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	        dbs.objectMapper.writeValue(fos, compensationRepository.findAll());
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return compensation;
    }

	/*
	 * 
	@author: Saurabh Shukla

	method explanation: This method is implementation of read method to read Compensation structure of 
						employee.

	@param: id              : Employee id for compensation structure record
    @return: matched        : Compensation structure as per requested employee id

	*/
    
    
    
    @Override
    public List<Compensation> read(String id) {
        LOG.debug("Creating compensation with id [{}]", id);
        List<Compensation> compensations = new ArrayList<Compensation>();
        List<Compensation>matched = new ArrayList<Compensation>();
        compensations = compensationRepository.findAll();
        for(Compensation compensation: compensations) {
        	if(compensation.getEmployee().getEmployeeId().equals(id)) {
        		matched.add(compensation);
        	}
        }
        return matched;
    }

}
