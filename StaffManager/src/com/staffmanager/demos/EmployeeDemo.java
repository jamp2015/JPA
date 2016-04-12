package com.staffmanager.demos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.staffmanager.model.Address;
import com.staffmanager.model.Employee;
import com.staffmanager.model.EmployeePersonalInfo;
import com.staffmanager.model.EmployeeStatus;
import com.staffmanager.model.Project;
import com.staffmanager.persistence.dao.EmployeeDao;
import com.staffmanager.persistence.dao.EmployeeDaoImpl;

public class EmployeeDemo {
	
	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StaffManager");
	private static EntityManager entitymanager = emfactory.createEntityManager();
	
	public static void main(String[] args) {
		
		EmployeeDemo service = new EmployeeDemo();
		service.demo();
		
		entitymanager.close();
		emfactory.close();
	}
	
	public void demo(){
		
		EmployeeDao employeeDao = new EmployeeDaoImpl(entitymanager);
		
		// create
		createAnEmployeeDemo();
		
		// delete
		employeeDao.delete(8);
				
		// find
		Employee employee = employeeDao.find(1);
		System.out.println(employee.toString());
		
		// update
		employee = employeeDao.find(2);
		employee.setName("Mike");
		employeeDao.update(employee);
		
		// Add to unit
		employeeDao.add(3, 2);
		// Assign to project
		employeeDao.assign(2, 1);
	}
	
	private void createAnEmployeeDemo(){
		
		entitymanager.getTransaction().begin();
		
		// Create tables for appropriate employee attributes
		// Create projects
		Project project[] = {
				new Project(11, "Project M1", null), 
				new Project(12, "Project M2", null),
				new Project(13, "Project M3", null)
				};		
		
		for (int i = 0; i < project.length; i++)
		{
			entitymanager.persist(project[i]);
		}
	
		// Create sets of projects in witch employees take part
		Set<Project> projectSet1 = new HashSet<Project>();
		projectSet1.add(project[0]);
		projectSet1.add(project[1]);
		projectSet1.add(project[2]);
			
		// Create addresses
		Address address[] = {
				new Address(10, "Minsk", "Slobodskaya, 22, apt. 34")
				};		
		
		for (int i = 0; i < address.length; i++)
		{
			entitymanager.persist(address[i]);
		}
		
		// ----------------------------------------------------------------------
		// Create EmployeePersonalInfo
		
		EmployeePersonalInfo employeePersonalInfo[] = {
				new EmployeePersonalInfo(10, "MP2898462")
				};		
		
		for (int i = 0; i < employeePersonalInfo.length; i++)
		{
			entitymanager.persist(employeePersonalInfo[i]);
		}
		entitymanager.getTransaction().commit();

		// Now create an employee
		Employee newEmployee = new Employee(8, "Jane", EmployeeStatus.ACTIVE, 1000, 
		address[0], projectSet1, employeePersonalInfo[0]);
		
		EmployeeDao employeeDao = new EmployeeDaoImpl(entitymanager);
		
		employeeDao.create(newEmployee);
	}
}
