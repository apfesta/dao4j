package com.andrewfesta.dao4j.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.andrewfesta.dao4j.criteria.Criteria;
import com.andrewfesta.dao4j.domain.Entity;

public interface CriteriaDAO<D extends Entity<ID>,ID extends Serializable> {
	
	public int count(Criteria criteria);
	
	public Collection<D> find(Criteria criteria);

	public List<D> find(Criteria criteria, String[] orderOptions);
	
	public List<D> find(Criteria criteria, String[] orderOptions, final int maxElements);
	
	public <E extends D> List<E> find(Criteria criteria, String[] orderOptions, int maxElements, Class<E> domainClass);
	
	public D findOne(Criteria criteria);
		
	public D findOne(Criteria criteria, String[] orderOptions);	
	
	public <E extends D> E findOne(Criteria criteria, String[] orderOptions, 
			Class<E> domainClass);
	
	public int delete(Criteria criteria);
}
