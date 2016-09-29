package com.andrewfesta.dao4j.jpa.criteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.andrewfesta.dao4j.criteria.Criteria;
import com.andrewfesta.dao4j.criteria.TermDecorator;
import com.andrewfesta.dao4j.dao.CriteriaDAO;
import com.andrewfesta.dao4j.domain.Entity;
import com.andrewfesta.dao4j.jpa.BaseQueryJpaDAO;
import com.andrewfesta.dao4j.util.DataAccessUtils;

public class BaseCriteriaJpaDAO<D extends Entity<ID>,ID extends Serializable> 
	extends BaseQueryJpaDAO<D,ID> implements CriteriaDAO<D,ID> {
	
	TermDecorator termDecorator = new AbstractJpqlTermDecorator(alias);

	public BaseCriteriaJpaDAO(Class<D> domainClass) {
		super(domainClass);
	}
	
	public int delete(Criteria criteria) {
		return delete(new DefaultWhereClause(termDecorator, criteria));
	}
	
	public Collection<D> find(Criteria criteria) {
		return find(criteria, null);
	}
	
	public List<D> find(Criteria criteria, String[] orderOptions)
	{
		return find(criteria, orderOptions, 0);
	}
	
	public List<D> find(Criteria criteria, String[] orderOptions, int maxElements) {
		return find(new DefaultWhereClause(termDecorator, criteria), new DefaultOrderByClause(orderOptions), maxElements);	
	}
	
	public <T extends D> List<T> find(Criteria criteria, String[] orderOptions, int maxElements, Class<T> domainClass) {
		return find(new DefaultWhereClause(termDecorator, criteria), new DefaultOrderByClause(orderOptions), maxElements, domainClass);
	}
	
	public int count(Criteria criteria) {
		return count(new DefaultWhereClause(termDecorator, criteria));
	}
	
	public D findOne(Criteria criteria) {
		return (D) findOne(criteria, null);
	}
	
	public D findOne(Criteria criteria, String[] orderOptions) {
		Collection<D> c = this.find(criteria,orderOptions, 1);
		return DataAccessUtils.firstElement(c);
	}
	
	public <T extends D> T findOne(Criteria criteria, String[] orderOptions, 
			Class<T> domainClass) {
		Collection<T> c = this.find(criteria,orderOptions, 1, domainClass);
		return DataAccessUtils.firstElement(c);
	}

	protected static class DefaultWhereClause extends AbstractJpqlCriteriaWhereClause {	
		public DefaultWhereClause(TermDecorator termDecorator, Criteria criteria) {
			super(termDecorator, criteria);
		}
	}
}
