package com.staffmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeePersonalInfo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int employeePersonalInfoId;
	
	private String passportData;
	
	public EmployeePersonalInfo() {
	}

	public EmployeePersonalInfo(int employeePersonalInfoId, String passportData) {
		super();
		this.employeePersonalInfoId = employeePersonalInfoId;
		this.passportData = passportData;
	}

	public int getEmployeePersonalInfoId() {
		return employeePersonalInfoId;
	}

	public void setEmployeePersonalInfoId(int employeePersonalInfoId) {
		this.employeePersonalInfoId = employeePersonalInfoId;
	}

	public String getPassportData() {
		return passportData;
	}

	public void setPassportData(String passportData) {
		this.passportData = passportData;
	}

	@Override
	public String toString() {
		return "EmployeePersonalInfo [employeePersonalInfoId="
				+ employeePersonalInfoId + ", passportData=" + passportData
				+ "]";
	}
}
