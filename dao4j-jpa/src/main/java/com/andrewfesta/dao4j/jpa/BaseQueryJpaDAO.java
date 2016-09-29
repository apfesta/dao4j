package com.andrewfesta.dao4j.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.andrewfesta.dao4j.dao.OrderByClause;
import com.andrewfesta.dao4j.dao.WhereClause;
import com.andrewfesta.dao4j.domain.Entity;


/**
 * Provides an implementation of CriteriaDAO and CollectionsDAO (but does not
 * expose those methods) using a base class of BaseCrudJpaDAO.
 * 
 * @author apfesta
 *
 * @param <D>
 * @param <ID>
 */
public class BaseQueryJpaDAO<D extends Entity<ID>,ID extends Serializable> 
	extends BaseCrudJpaDAO<D,ID> {
	
	protected String alias = "a";
		
	public BaseQueryJpaDAO(Class<D> domainClass) {
		super(domainClass);
	}
		
	protected int delete(WhereClause where) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append("delete from ");
		queryString.append(this.domainClass.getName());
		queryString.append(" ").append(this.getAlias());
		
		queryString.append(where.getSql());
		
		return queryForBulkUpdateOrDelete(queryString.toString(), 
				where.getValueMap());
	}
				
	/**
	 * Allows a subclass to create its own custom Where or OrderBy clause
	 * 
	 * @param where
	 * @param orderBy
	 * @param maxElements
	 * @return
	 */
	protected List<D> find(WhereClause where, OrderByClause orderBy, int maxElements) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append("from ");
		queryString.append(this.domainClass.getName());
		queryString.append(" ").append(this.getAlias());
		
		queryString.append(where.getSql());
		
		queryString.append(orderBy.getSql());
		return query(queryString.toString(), where.getValueMap(), maxElements);		
	}
	
		
	/**
	 * Allows a subclass to create its own custom Where or OrderBy clause based
	 * on an inherited table
	 * 
	 * @param where
	 * @param orderBy
	 * @param maxElements
	 * @param domainClass
	 * @return
	 */
	protected <T extends D> List<T> find(WhereClause where, OrderByClause orderBy, int maxElements, Class<T> domainClass) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append("from ");
		queryString.append(domainClass.getName());
		queryString.append(" ").append(this.getAlias());
		
		queryString.append(where.getSql());
		
		queryString.append(orderBy.getSql());
		return query(queryString.toString(), where.getValueMap(), maxElements, domainClass);		
	}
	
	/**
	 * @deprecated
	 */
	protected Collection<D> query(String queryString, List<Object> valueList) {
		return query(queryString, valueList, 0);
	}
	
	protected Collection<D> query(String queryString, Map<String, Object> valueMap) {
		return query(queryString, valueMap, 0);
	}
	
	/**
	 * @deprecated
	 */
	protected <T> Collection<T> query(String queryString, List<Object> valueList, Class<T> clazz) {
		return query(queryString, valueList, 0, clazz);
	}
	
	protected <T> Collection<T> query(String queryString, Map<String, Object> valueMap, Class<T> clazz) {
		return query(queryString, valueMap, 0, clazz);
	}
	
	/**
	 * @deprecated
	 */
	protected List<D> query(String queryString, 
			List<Object> valueList, int maxElements) {
		return query(queryString, valueList, maxElements, domainClass);
	}
	
	protected List<D> query(String queryString, 
			Map<String, Object> valueMap, int maxElements) {
		return query(queryString, valueMap, maxElements, domainClass);
	}
	
	/**
	 * @deprecated use {@link #query(String, Map, int, Class)} instead.
	 * @param queryString
	 * @param valueList
	 * @param maxElements
	 * @param clazz
	 * @return
	 */
	protected <T> List<T> query(final String queryString, 
			final List<Object> valueList, final int maxElements, Class<T> clazz) {
		
		TypedQuery<T> q = em.createQuery(queryString, clazz);
		int i =1;
		for (Iterator<Object> it=valueList.iterator(); it.hasNext(); i++) {
			q.setParameter(i, it.next());
		}
		if (maxElements>0){
			q.setMaxResults(maxElements);
		}
		return q.getResultList();
	}
	
	protected <T> List<T> query(final String queryString, 
			final Map<String, Object> valueMap, final int maxElements, Class<T> clazz) {
		
		TypedQuery<T> q = em.createQuery(queryString, clazz);
		for (Entry<String, Object> entry: valueMap.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		if (maxElements>0){
			q.setMaxResults(maxElements);
		}
		return q.getResultList();
	}
	
	/**
	 * @deprecated
	 */
	protected <T> T queryForFirstRow(String queryString, List<Object> valueList, Class<T> clazz) {
		TypedQuery<T> q = em.createQuery(queryString, clazz);
		int i =1;
		for (Iterator<Object> it=valueList.iterator(); it.hasNext(); i++) {
			q.setParameter(i, it.next());
		}
		
		return q.getSingleResult();
	}
	
	protected <T> T queryForFirstRow(String queryString, Map<String, Object> valueMap, Class<T> clazz) {
		TypedQuery<T> q = em.createQuery(queryString, clazz);
		for (Entry<String, Object> entry: valueMap.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		
		return q.getSingleResult();
	}
	
	/**
	 * @deprecated
	 */
	protected int queryForBulkUpdateOrDelete(String queryString, List<Object> valueList) {
		Query q = em.createQuery(queryString);
		int i =1;
		for (Iterator<Object> it=valueList.iterator(); it.hasNext(); i++) {
			q.setParameter(i, it.next());
		}
		return q.executeUpdate();
	}
	
	protected int queryForBulkUpdateOrDelete(String queryString, Map<String, Object> valueMap) {
		Query q = em.createQuery(queryString);
		for (Entry<String, Object> entry: valueMap.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		return q.executeUpdate();
	}
	
	protected int count(final WhereClause where) {
		final StringBuffer queryString = new StringBuffer();
		queryString.append("select");
		queryString.append(" count("+this.getAlias()+".id) ");
		queryString.append("from ");
		queryString.append(domainClass.getName());
		queryString.append(" ").append(this.getAlias());
		
		queryString.append(where.getSql());
		
		TypedQuery<Number> q = em.createQuery(queryString.toString(), Number.class);
		for (Entry<String, Object> entry: where.getValueMap().entrySet()) {
			q.setParameter(entry.getKey(),entry.getValue());
		}
		return q.getSingleResult().intValue();
	}
		

	/**
	 * @return Returns the alias.
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * @param alias The alias to set.
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}


	/**
	 * @return the domainClass
	 */
	public Class<D> getDomainClass() {
		return domainClass;
	}


	/**
	 * @param domainClass the domainClass to set
	 */
	public void setDomainClass(Class<D> domainClass) {
		this.domainClass = domainClass;
	}

		
	/**
	 * 
	 * @author apfesta
	 *
	 */
	public class DefaultOrderByClause extends AbstractJpqlOrderByClause{
		public DefaultOrderByClause(String[] orderOptions) {
			super(getAlias(), orderOptions);
		}
	}
	
}
