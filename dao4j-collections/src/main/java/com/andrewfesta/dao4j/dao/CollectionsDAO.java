package com.andrewfesta.dao4j.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.andrewfesta.dao4j.domain.Entity;

	public interface CollectionsDAO<D extends Entity<ID>,ID extends Serializable> {

	public int count(Map<String, Object> criteria);
		
	public Collection<D> find(Map<String, Object> criteria);
	
	public List<D> find(Map<String, Object> criteria, String[] orderOptions);
	
	public List<D> find(Map<String, Object> criteria, String[] orderOptions, final int maxElements);
	
	public <E extends D> List<E> find(Map<String, Object> criteria, String[] orderOptions, Class<E> domainClass);
		
	public D findOne(Map<String, Object> criteria);
		
	public D findOne(Map<String, Object> criteria, String[] orderOptions);	
	
	public <E extends D> E findOne(Map<String, Object> criteria, String[] orderOptions, Class<E> domainClass);	
		
	public int delete(Map<String, Object> criteria);
	
}
