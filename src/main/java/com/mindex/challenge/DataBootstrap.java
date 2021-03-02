package com.mindex.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.Compensation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import java.beans.Transient;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

@Component
public class DataBootstrap {
    public static final String DATASTORE_LOCATION = "/static/employee_database.json";
    public static final String COMPENSATION_DATASTORE_LOCATION = "/static/compensation_database.json";

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    public  ObjectMapper objectMapper;
    
    @Autowired
    public ObjectMapper compensationObjectMapper;

    @PostConstruct
    public void init() {
        InputStream inputStream = this.getClass().getResourceAsStream(DATASTORE_LOCATION);
        
        Employee[] employees = null;
        try {
            employees = objectMapper.readValue(inputStream, Employee[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       
        for (Employee employee : employees) {
            employeeRepository.save(employee);
        }
        
        System.out.println("Employee repository loaded");
        System.out.println("Compensation Loading.......");
        //compensation repository load during bootup
        InputStream compensationInputStream = this.getClass().getResourceAsStream(COMPENSATION_DATASTORE_LOCATION);
        
        Compensation[] compensations = null;
        try {
        	compensations = compensationObjectMapper.readValue(compensationInputStream, Compensation[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List <Compensation> arrayList = new ArrayList<Compensation>();
        Collections.addAll(arrayList, compensations);
        compensationRepository.saveAll(arrayList);
        System.out.println("Compensation repository loaded");
    }
   
}
