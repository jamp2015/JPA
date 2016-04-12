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
import com.staffmanager.model.Unit;
import com.staffmanager.persistence.dao.UnitDao;
import com.staffmanager.persistence.dao.UnitDaoImpl;

public class UnitDemo {
	
	private static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StaffManager");
	private static EntityManager entitymanager = emfactory.createEntityManager();
	
	public static void main(String[] args) {
		
		UnitDemo service = new UnitDemo();
		service.demo();
		
		entitymanager.close();
		emfactory.close();
	}
	
	public void demo()
	{
		UnitDao unitDao  = new UnitDaoImpl(entitymanager);
		
		// create
		createUnitDemo();
		
		// delete
		//unitDao.delete(876);
				
		// find
		Unit unit = unitDao.find(2);

		// update
		unit.setTitle("New unit title");
		unitDao.update(unit);
	}

	/**
	 * Demonstrates how unit is created.
	 */
	private void createUnitDemo()
	{
		UnitDao unitDao  = new UnitDaoImpl(entitymanager);
		
		entitymanager.getTransaction().begin();
		// ----------------------------------------------------------------------
		
		// Create projects
				Project project[] = {
						new Project(111, "Project X", null), 
						new Project(112, "Project Y", null),
						new Project(311, "Project Z", null)
						};		
				
				for (int i = 0; i < project.length; i++)
				{
					entitymanager.persist(project[i]);
				}
				
			
				// Create sets of projects in witch employees take part
				Set<Project> projectSet1 = new HashSet<Project>();
				projectSet1.add(project[0]);
				projectSet1.add(project[1]);
				
				Set<Project> projectSet2 = new HashSet<Project>();
				projectSet1.add(project[1]);
				projectSet1.add(project[2]);
				
				Set<Project> projectSet3 = new HashSet<Project>();
				projectSet1.add(project[0]);
				projectSet1.add(project[2]);
					
				// Create addresses
				Address address[] = {
						new Address(111, "Minsk", "Slobodskaya, 22, apt. 34"), 
						new Address(112, "Minsk", "Slobodskaya, 24, apt. 20"),
						new Address(113, "Minsk", "Nezavisimosty, 155, apt. 18")
						};		
				
				for (int i = 0; i < address.length; i++)
				{
					entitymanager.persist(address[i]);
				}
				
				// ----------------------------------------------------------------------
				// Create EmployeePersonalInfo
				
				EmployeePersonalInfo employeePersonalInfo[] = {
						new EmployeePersonalInfo(111, "MP26375462"), 
						new EmployeePersonalInfo(211, "MK98434534"),
						new EmployeePersonalInfo(113, "ET38749834"),
						new EmployeePersonalInfo(114, "PI23545643"),
						};		
				
				for (int i = 0; i < employeePersonalInfo.length; i++)
				{
					entitymanager.persist(employeePersonalInfo[i]);
				}
				
				// --------------------------------------------------------------------
				// Create an employee
				
				Employee employee[] = {
						new Employee(111, "Peter", EmployeeStatus.ACTIVE, 3000, address[0], projectSet1, employeePersonalInfo[0]), 
						new Employee(112, "Mike", EmployeeStatus.NON_ACTIVE, 5000, address[1], projectSet1, employeePersonalInfo[1]),
						new Employee(113, "Ann", EmployeeStatus.NON_ACTIVE, 3000, address[2], projectSet2, employeePersonalInfo[2]),
						new Employee(114, "Mary", EmployeeStatus.ACTIVE, 4000, address[0], projectSet3, employeePersonalInfo[3])
						};		
				
				for (int i = 0; i < employee.length; i++)
				{
					entitymanager.persist(employee[i]);
				}
				// ---------------------------------------------------------------------
				// Create a list of employees for the units
				
				Set<Employee> employeeSet = new HashSet<Employee>();
				employeeSet.add(employee[0]);
				employeeSet.add(employee[1]);
				employeeSet.add(employee[2]);
				employeeSet.add(employee[3]);
					
				// ----------------------------------------------------------------------
				// Commit transaction
				entitymanager.getTransaction().commit();
				
				//-----------------------------------------------------------------------
				// Create units
				Unit unit = new Unit(876, "Unit X", employeeSet);
				
				unitDao.create(unit);
	}
}
