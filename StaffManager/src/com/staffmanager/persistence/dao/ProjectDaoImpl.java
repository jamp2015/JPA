package com.staffmanager.persistence.dao;

import javax.persistence.EntityManager;

import com.staffmanager.model.Project;

public class ProjectDaoImpl implements ProjectDao {
	
	private EntityManager entitymanager;

	public ProjectDaoImpl(EntityManager entitymanager) {
		super();
		this.entitymanager = entitymanager;
	}

	@Override
	public void create(Project project) {
		entitymanager.getTransaction().begin();
		entitymanager.persist(project);
		entitymanager.getTransaction().commit();
	}

	@Override
	public Project find(int projectId) {
		return entitymanager.find(Project.class, projectId);
	}

	@Override
	public void delete(int projectId) {
		entitymanager.getTransaction().begin();
		Project project = entitymanager.find(Project.class, projectId);
		entitymanager.remove(project);
		entitymanager.getTransaction().commit();
	}

	@Override
	public void update(Project newProject) {
		Project project = entitymanager.find(Project.class, newProject.getProjectId());
		entitymanager.getTransaction().begin();
		project = newProject;
		entitymanager.getTransaction().commit();
	}
}
