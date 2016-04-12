package com.staffmanager.persistence.dao;

import com.staffmanager.model.Unit;

public interface UnitDao {
	
	public void create(Unit unit);
	public Unit find(int unitId);
	public void delete(int unitId);
	public void update(Unit unit);
}
