package com.mindex.challenge.data;


/*
 * 
@author: Saurabh Shukla

Class explanation: This reporting structure class is POJO class. 

@param: Employee           : Employee object is created to store employee details
@param: numberOfReports    : Number of Reports field is to hold data for number of employee
							 under parent employee



*/

public class ReportingStructure {
	private Employee employee;
	private int numberOfReports;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public int getNumberOfReports() {
		return numberOfReports;
	}
	public void setNumberOfReports(int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}
	

}
