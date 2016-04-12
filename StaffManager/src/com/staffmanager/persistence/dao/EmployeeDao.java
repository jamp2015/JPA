package com.staffmanager.persistence.dao;

import com.staffmanager.model.Employee;

public interface EmployeeDao {
	
	public void create(Employee employee);
	public Employee find(int employeeId);
	public void delete(int employeeId);
	public void update(Employee employee);
	
	public void add(int employeeId, int unitId);
	public void assign(int employeeId, int projectId);
}
