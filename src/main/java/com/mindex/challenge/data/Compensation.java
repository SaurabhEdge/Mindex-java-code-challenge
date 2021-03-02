package com.mindex.challenge.data;

import org.springframework.data.mongodb.core.mapping.Document;

/*
 * 
@author: Saurabh Shukla

Class explanation: This Compensation class is POJO class. 

@param: id              : Unique id for compensation record
@param: Employee        : Employee object is created to store employee details
@param: salary          : Employee salary field is added to store employee salary
@param: effective_date  : Effective date field is set to store date value as string


*/

@Document
public class Compensation {
	
	private String id;
	private Employee employee;
	private double salary;
	private String effective_date;
	
	public Compensation() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getEffective_date() {
		return effective_date;
	}
	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}
	
}
	