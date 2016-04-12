package com.staffmanager.persistence.dao;

import javax.persistence.EntityManager;

import com.staffmanager.model.Unit;

public class UnitDaoImpl implements UnitDao {

	private EntityManager entitymanager;
	
	public UnitDaoImpl(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}

	@Override
	public void create(Unit unit) {
		entitymanager.getTransaction().begin();
		entitymanager.persist(unit);
		entitymanager.getTransaction().commit();
	}

	@Override
	public Unit find(int unitId) {
		return entitymanager.find(Unit.class, unitId);
	}

	@Override
	public void delete(int unitId) {
		entitymanager.getTransaction().begin();
		Unit unit = entitymanager.find(Unit.class, unitId);
		entitymanager.remove(unit);
		entitymanager.getTransaction().commit();
	}

	@Override
	public void update(Unit newUnit) {
		Unit unit = entitymanager.find(Unit.class, newUnit.getUnitId());
		entitymanager.getTransaction().begin();
		unit = newUnit;
		entitymanager.getTransaction().commit();
	}
}
