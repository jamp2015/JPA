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
import com.staffmanager.persistence.dao.ProjectDao;
import com.staffmanager.persistence.dao.ProjectDaoImpl;

public class ProjectDemo {
	
	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StaffManager");
	private static EntityManager entitymanager = emfactory.createEntityManager();
	
	public static void main(String[] args) {
		
		ProjectDemo service = new ProjectDemo();
		service.demo();
		
		entitymanager.close();
		emfactory.close();
	}
	
	public void demo(){

		ProjectDao projectDao  = new ProjectDaoImpl(entitymanager);
		
		// create
		createProjectDemo();
		
		// delete
		projectDao.delete(1);
				
		// find
		Project project = projectDao.find(2);

		// update
		project.setTitle("New project title");
		projectDao.update(project);
	}
	
	private void createProjectDemo()
	{
		ProjectDao projectDao  = new ProjectDaoImpl(entitymanager);
		
		// Create addresses
		Address address[] = {
				new Address(21, "Minsk", "Slobodskaya, 222, apt. 34"), 
				new Address(22, "Minsk", "Slobodskaya, 244, apt. 20"),
				new Address(23, "Minsk", "Nezavisimosty, 15, apt. 18")
				};		
		
		for (int i = 0; i < address.length; i++)
		{
			entitymanager.persist(address[i]);
		}
		
		// ----------------------------------------------------------------------
		// Create EmployeePersonalInfo
		
		EmployeePersonalInfo employeePersonalInfo[] = {
				new EmployeePersonalInfo(21, "MP26375462"), 
				new EmployeePersonalInfo(22, "MK98434534"),
				new EmployeePersonalInfo(23, "ET38749834"),
				new EmployeePersonalInfo(24, "PI23545643"),
				};		
		
		for (int i = 0; i < employeePersonalInfo.length; i++)
		{
			entitymanager.persist(employeePersonalInfo[i]);
		}
		
		// --------------------------------------------------------------------
		// Create an employee
		
		Employee employee[] = {
				new Employee(21, "Peter", EmployeeStatus.ACTIVE, 3000, address[0], null, employeePersonalInfo[0]), 
				new Employee(22, "Mike", EmployeeStatus.NON_ACTIVE, 5000, address[1], null, employeePersonalInfo[1]),
				new Employee(23, "Ann", EmployeeStatus.NON_ACTIVE, 3000, address[2], null, employeePersonalInfo[2]),
				new Employee(24, "Mary", EmployeeStatus.ACTIVE, 4000, address[0], null, employeePersonalInfo[3])
				};		
		
		for (int i = 0; i < employee.length; i++)
		{
			entitymanager.persist(employee[i]);
		}

		// Create a list of employees for the units
		
		Set<Employee> employeeSet1 = new HashSet<Employee>();
		employeeSet1.add(employee[0]);
		employeeSet1.add(employee[1]);
		employeeSet1.add(employee[2]);
		employeeSet1.add(employee[3]);
		
		Project newProject = new Project(45, "New project", employeeSet1);
		projectDao.create(newProject);
	}
	
}
