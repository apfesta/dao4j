/**
 * 
 */
package com.andrewfesta.dao4j.dao.mock;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.andrewfesta.dao4j.criteria.Criteria;
import com.andrewfesta.dao4j.dao.CriteriaDAO;
import com.andrewfesta.dao4j.dao.DAO;
import com.andrewfesta.dao4j.domain.PersistableEntity;

/**
 * @author Andy
 *
 */
public class MockCriteriaDAO<D extends PersistableEntity<ID>,ID extends Serializable> 
	extends MockDAO<D, ID>
	implements DAO<D, ID>, CriteriaDAO<D, ID> {

	private int countReturnValue;
	private List<D> findReturnValue;
	private int bulkDeleteReturnValue;
	
	public int count(Criteria criteria) {
		return countReturnValue;
	}

	public List<D> find(Criteria criteria, String[] orderOptions,
			int maxElements) {
		return findReturnValue;
	}

	public Collection<D> find(Criteria criteria) {
		return find(criteria, null);
	}
		
	public List<D> find(Criteria criteria, String[] orderOptions)
	{
		return find(criteria, orderOptions, 0);
	}
	
	public D findOne(Criteria criteria) {
		return (D) findOne(criteria, null);
	}
	
	public D findOne(Criteria criteria, String[] orderOptions) {
		Collection<D> c = this.find(criteria,orderOptions, 1);
		if (c!=null && c.size()>0) {
			return c.iterator().next();
		}
		return null;
	}

	@Override
	public <E extends D> List<E> find(Criteria criteria, String[] orderOptions,
			int maxElements, Class<E> domainClass) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}


	@Override
	public <E extends D> E findOne(Criteria criteria, String[] orderOptions,
			Class<E> domainClass) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}


	@Override
	public int delete(Criteria criteria) {
		return bulkDeleteReturnValue;
	}

}
