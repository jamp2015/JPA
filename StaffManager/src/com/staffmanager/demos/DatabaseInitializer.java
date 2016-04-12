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

/** 
 * Creates the appropriate tables in the database.
 * 
 * @author Igor_Shingaryov
 *
 */
public class DatabaseInitializer {
	
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("StaffManager");
	private EntityManager entitymanager = emfactory.createEntityManager();
	
	public static void main(String[] args) {
		
		DatabaseInitializer service = new DatabaseInitializer();
		service.createDatabaseTables();
    }
	
	
	public void createDatabaseTables()
	{
		entitymanager.getTransaction().begin();
		// ----------------------------------------------------------------------
		
		// Create projects
				Project project[] = {
						new Project(1, "Project X", null), 
						new Project(2, "Project Y", null),
						new Project(3, "Project Z", null)
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
						new Address(1, "Minsk", "Slobodskaya, 22, apt. 34"), 
						new Address(2, "Minsk", "Slobodskaya, 24, apt. 20"),
						new Address(3, "Minsk", "Nezavisimosty, 155, apt. 18")
						};		
				
				for (int i = 0; i < address.length; i++)
				{
					entitymanager.persist(address[i]);
				}
				
				// ----------------------------------------------------------------------
				// Create EmployeePersonalInfo
				
				EmployeePersonalInfo employeePersonalInfo[] = {
						new EmployeePersonalInfo(1, "MP26375462"), 
						new EmployeePersonalInfo(2, "MK98434534"),
						new EmployeePersonalInfo(3, "ET38749834"),
						new EmployeePersonalInfo(4, "PI23545643"),
						};		
				
				for (int i = 0; i < employeePersonalInfo.length; i++)
				{
					entitymanager.persist(employeePersonalInfo[i]);
				}
				
				// --------------------------------------------------------------------
				// Create an employee
				
				Employee employee[] = {
						new Employee(1, "Peter", EmployeeStatus.ACTIVE, 3000, address[0], projectSet1, employeePersonalInfo[0]), 
						new Employee(2, "Mike", EmployeeStatus.NON_ACTIVE, 5000, address[1], projectSet1, employeePersonalInfo[1]),
						new Employee(3, "Ann", EmployeeStatus.NON_ACTIVE, 3000, address[2], projectSet2, employeePersonalInfo[2]),
						new Employee(4, "Mary", EmployeeStatus.ACTIVE, 4000, address[0], projectSet3, employeePersonalInfo[3])
						};		
				
				for (int i = 0; i < employee.length; i++)
				{
					entitymanager.persist(employee[i]);
				}
				// ---------------------------------------------------------------------
				// Create a list of employees for the units
				
				Set<Employee> employeeList1 = new HashSet<Employee>();
				employeeList1.add(employee[0]);
				employeeList1.add(employee[1]);
				employeeList1.add(employee[3]);
				
				
				Set<Employee> employeeList2 = new HashSet<Employee>();
				employeeList2.add(employee[0]);
				employeeList2.add(employee[1]);
				employeeList2.add(employee[2]);
				employeeList2.add(employee[3]);		
				
				//-----------------------------------------------------------------------
				// Create units
				Unit unit[] = {
						new Unit(1, "Unit X", employeeList1), 
						new Unit(2, "Unit Y", employeeList2)
						};		
				
				for (int i = 0; i < unit.length; i++)
				{
					entitymanager.persist(unit[i]);
				}
				
		
		// ----------------------------------------------------------------------
		// Commit transaction
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
	}
}
