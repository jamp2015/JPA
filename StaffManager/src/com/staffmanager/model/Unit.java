package com.staffmanager.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Unit {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int unitId;
	private String title;

	@OneToMany(targetEntity=Employee.class, cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	private Set<Employee> employeeSet;
	
	public Unit() {
	}

	public Unit(int unitId, String title, Set<Employee> employeeSet) {
		super();
		this.unitId = unitId;
		this.title = title;
		this.employeeSet = employeeSet;
	}

	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Employee> getEmployeeSet() {
		return employeeSet;
	}

	public void setEmployeeSet(Set<Employee> employeeSet) {
		this.employeeSet = employeeSet;
	}

	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", title=" + title + ", employeeSet="
				+ employeeSet + "]";
	}
}
