package com.staffmanager.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


/**
 * Many-to-many with employee.
 * 
 * @author Igor_Shingaryov
 *
 */
@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int projectId;
	private String title;
	
	@ManyToMany(targetEntity=Employee.class, cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	private Set<Employee> employeeSet;
	
	public Project() {
	}
	
	public Project(int projectId, String title, Set<Employee> employeeSet) {
		super();
		this.projectId = projectId;
		this.title = title;
		this.employeeSet = employeeSet;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
		return "Project [projectId=" + projectId + ", title=" + title
				+ ", employeeSet=" + employeeSet + "]";
	}
}
