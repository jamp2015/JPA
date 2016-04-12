package com.staffmanager.persistence.dao;

import com.staffmanager.model.Project;

public interface ProjectDao {

	public void create(Project project);
	public Project find(int projectId);
	public void delete(int projectId);
	public void update(Project project);
}
