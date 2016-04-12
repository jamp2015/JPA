package com.staffmanager.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity 
public class Employee {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int employeeId;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private EmployeeStatus status;
	
	private double salary;
	
	// Many employees per one address
	@ManyToOne
	private Address address;
		
	// Many-to-Many with project
	@ManyToMany(targetEntity=Project.class, cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	private Set<Project> projectSet;
	
	@OneToOne
	private EmployeePersonalInfo employeePersonalInfo;
	
	public Employee() {
    }
	
	public Employee(int employeeId, String name, EmployeeStatus status,
			double salary, Address address, Set<Project> projectSet,
			EmployeePersonalInfo employeePersonalInfo) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.status = status;
		this.salary = salary;
		this.address = address;
		this.projectSet = projectSet;
		this.employeePersonalInfo = employeePersonalInfo;
	}

	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public EmployeeStatus getStatus() {
		return status;
	}

	public void setStatus(EmployeeStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Project> getProjectSet() {
		return projectSet;
	}

	public void setProjectSet(Set<Project> projectSet) {
		this.projectSet = projectSet;
	}


	public EmployeePersonalInfo getEmployeePersonalInfo() {
		return employeePersonalInfo;
	}


	public void setEmployeePersonalInfo(EmployeePersonalInfo employeePersonalInfo) {
		this.employeePersonalInfo = employeePersonalInfo;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name
				+ ", status=" + status + ", salary=" + salary + ", address="
				+ address + ", projectSet=" + projectSet
				+ ", employeePersonalInfo=" + employeePersonalInfo + "]";
	}
	
	
	
}
