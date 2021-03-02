package com.mindex.challenge.service.impl;

/*
 * 
@author: Saurabh Shukla

Class explanation: This reporting structure implementation class is implementation of read method
                    with recursive call. 

*/


import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{
	private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
	
	@Autowired
    private EmployeeRepository employeeRepository;
	
	private  int counter = 0;
	private  HashMap<String,Integer> visitedMap = new HashMap<String,Integer>();
	
	/*
	 * 
	@author: Saurabh Shukla

	method explanation: This method is implementation of read method to read reporting structure of 
						employee with help of recursion method.

	@param: id              : Employee id for reporting structure record
    @return: rs             : reporting structure as per requested employee id

	*/
	
	@Override
	public ReportingStructure read(String id) {
		LOG.debug("Reading employee with id [{}]", id);

        //logic for calculating numberofReports
		Employee employee = employeeRepository.findByEmployeeId(id);
		System.out.println("employee id is "+employee.getEmployeeId());
		
		 if (employee == null) {
	            throw new RuntimeException("Invalid employeeId: ");
	        }
		 
        Employee eeee = recursive(employee);
        
        System.out.println("counter is "+counter);
		ReportingStructure rs = new ReportingStructure();
		rs.setEmployee(eeee);
		rs.setNumberOfReports(counter);
		visitedMap = new HashMap<String,Integer>();
		counter=0;
		return rs;
	}
	
	
	/*
	 * 
	@author: Saurabh Shukla

	method explanation: This method checks the size of direct report field and recursively calls the method
	                    to fill all attribute in employee object. Once child object is done with their call
	                    parent object combines them in one main node and attach them in employee object before
	                    returning to calling function.

	@param: Employee         : Employee object is created to store employee details
	@return: Employee        : Employee object is returned to display employee details
  

	*/
	
	private Employee recursive(Employee employee) {
		
        if (employee == null) {
           return null;// throw new RuntimeException("Invalid employeeId: ");
        }
        if(employee.getDirectReports()==null) {
        	return employee;
        }
        if(visitedMap.containsKey(employee.getEmployeeId())) {
        	return employee;
        }
        if(employee.getDirectReports().size() > 0) {
        	visitedMap.put(employee.getEmployeeId(),1);
            List<Employee> listOfEmployees = employee.getDirectReports();
            List<Employee> children = new ArrayList(); 
            for(int index = 0 ;index < listOfEmployees.size();index++) {
            	System.out.println();
            	Employee temp = (Employee) listOfEmployees.get(index);
            	Employee child = employeeRepository.findByEmployeeId(temp.getEmployeeId());
            	recursive(child);
            	
            	counter++;
            	children.add(child);
            	
            }
            employee.setDirectReports(children);
        }
        return employee;
	}
	
}
