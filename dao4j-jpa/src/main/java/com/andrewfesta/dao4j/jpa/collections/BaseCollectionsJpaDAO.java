package com.andrewfesta.dao4j.jpa.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.andrewfesta.dao4j.domain.Entity;
import com.andrewfesta.dao4j.jpa.BaseQueryJpaDAO;
import com.andrewfesta.dao4j.util.DataAccessUtils;

public class BaseCollectionsJpaDAO<D extends Entity<ID>,ID extends Serializable> 
extends BaseQueryJpaDAO<D,ID> {

	public BaseCollectionsJpaDAO(Class<D> domainClass) {
		super(domainClass);
	}
	
	public int delete(Map<String, Object> criteria) {
		return delete(new DefaultWhereClause(getAlias(),criteria));
	}
	
	public Collection<D> find(Map<String, Object> criteria) {
		return find(criteria, null);
	}
	
	public List<D> find(Map<String, Object> criteria, String[] orderOptions)
	{
		return find(criteria, orderOptions, 0);
	}
	
	public List<D> find(Map<String, Object> criteria, String[] orderOptions, int maxElements) {
		return find(new DefaultWhereClause(getAlias(),criteria), new DefaultOrderByClause(orderOptions), maxElements);
	}	
	
	public <E extends D> List<E> find(Map<String, Object> criteria,
			String[] orderOptions, Class<E> domainClass) {
		return find(criteria, orderOptions, 0, domainClass);
	}
	
	public <E extends D> List<E> find(Map<String, Object> criteria,
			String[] orderOptions, int maxElements, Class<E> domainClass) {
		return find(new DefaultWhereClause(getAlias(),criteria), new DefaultOrderByClause(orderOptions), maxElements, domainClass);
	}
	
	public int count(Map<String, Object> criteria) {
		return count(new DefaultWhereClause(getAlias(),criteria));
	}
	
	public D findOne(Map<String, Object> criteria) {
		return (D) findOne(criteria, null);
	}
	
	public D findOne(Map<String, Object> criteria, String[] orderOptions) {
		Collection<D> c = this.find(criteria,orderOptions, 1);
		return DataAccessUtils.firstElement(c);
	}
	
	public <E extends D> E findOne(Map<String, Object> criteria,
			String[] orderOptions, Class<E> domainClass) {
		Collection<E> c = this.find(criteria,orderOptions, 1, domainClass);
		return DataAccessUtils.firstElement(c);
	}
			

	protected static class DefaultWhereClause extends AbstractJpqlCollectionsWhereClause {	
		public DefaultWhereClause(String alias, Map<String,Object> criteria){
			super(alias, criteria);
		}
	}

}
