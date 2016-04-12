package com.staffmanager.persistence.dao;

import javax.persistence.EntityManager;

import com.staffmanager.model.Employee;
import com.staffmanager.model.Project;
import com.staffmanager.model.Unit;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private EntityManager entitymanager;
	
	public EmployeeDaoImpl(EntityManager entitymanager) {
		super();
		this.entitymanager = entitymanager;
	}

	@Override
	public void create(Employee employee) {
		entitymanager.getTransaction().begin();
		entitymanager.persist(employee);
		entitymanager.getTransaction().commit();
	}

	@Override
	public Employee find(int employeeId) {
		entitymanager.getTransaction().begin();
		Employee employee = entitymanager.find(Employee.class, employeeId);
		entitymanager.getTransaction().commit();
		return employee;
	}

	@Override
	public void delete(int employeeId) {
		entitymanager.getTransaction().begin();
		Employee employee = entitymanager.find(Employee.class, employeeId);
		entitymanager.remove(employee);
		entitymanager.getTransaction().commit();
	}

	@Override
	public void update(Employee newEmployee) {
		entitymanager.getTransaction().begin();
		Employee employee = entitymanager.find(Employee.class, newEmployee.getEmployeeId());
		employee = newEmployee;
		entitymanager.getTransaction().commit();
	}

	@Override
	public void add(int employeeId, int unitId) {
		Unit unit = entitymanager.find(Unit.class, unitId);
		Employee employee = entitymanager.find(Employee.class, employeeId);

		entitymanager.getTransaction().begin();
		unit.getEmployeeSet().add(employee);
		entitymanager.getTransaction().commit();
	}

	@Override
	public void assign(int employeeId, int projectId) {
		Project project = entitymanager.find(Project.class, projectId);
		Employee employee = entitymanager.find(Employee.class, employeeId);

		entitymanager.getTransaction().begin();
		employee.getProjectSet().add(project);
		entitymanager.getTransaction().commit();
	}
}
