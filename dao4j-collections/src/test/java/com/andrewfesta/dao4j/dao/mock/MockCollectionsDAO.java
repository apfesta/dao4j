/**
 * 
 */
package com.andrewfesta.dao4j.dao.mock;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.andrewfesta.dao4j.dao.CollectionsDAO;
import com.andrewfesta.dao4j.dao.DAO;
import com.andrewfesta.dao4j.domain.PersistableEntity;

/**
 * @author Andy
 *
 */
public class MockCollectionsDAO<D extends PersistableEntity<ID>,ID extends Serializable> 
	extends MockDAO<D, ID>
	implements DAO<D, ID>, CollectionsDAO<D, ID> {

	private int countReturnValue;
	private List<D> findReturnValue;
	private int bulkDeleteReturnValue;
		
	public int count(Map<String, Object> criteria) {
		return countReturnValue;
	}

	public List<D> find(Map<String, Object> criteria,
			String[] orderOptions, int maxElements) {
		return findReturnValue;
	}

	public void setCountReturnValue(int countReturnValue) {
		this.countReturnValue = countReturnValue;
	}

	public void setFindReturnValue(List<D> findReturnValue) {
		this.findReturnValue = findReturnValue;
	}

	public Collection<D> find(Map<String, Object> criteria) {
		return find(criteria, null);
	}
	
	public List<D> find(Map<String, Object> criteria, String[] orderOptions)
	{
		return find(criteria, orderOptions, 0);
	}
	
	/* (non-Javadoc)
	 * @see net.mixednutz.foundation.dao.DAO#findOne(java.util.Map, java.lang.String[])
	 */
	public D findOne(Map<String, Object> criteria) {
		return (D) findOne(criteria, null);
	}
	
	// @see net.mixednutz.core.dao.DAO#findOne(java.util.Map, java.lang.String[])
	/**
	 * @param criteria
	 * @param orderOptions
	 * @return
	 */
	public D findOne(Map<String, Object> criteria, String[] orderOptions) {
		Collection<D> c = this.find(criteria,orderOptions, 1);
		if (c!=null && c.size()>0) {
			return c.iterator().next();
		}
		return null;
	}
	
	@Override
	public <E extends D> List<E> find(Map<String, Object> criteria,
			String[] orderOptions, Class<E> domainClass) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}


	@Override
	public <E extends D> E findOne(Map<String, Object> criteria,
			String[] orderOptions, Class<E> domainClass) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public int delete(Map<String, Object> criteria) {
		return bulkDeleteReturnValue;
	}

	public void setBulkDeleteReturnValue(int bulkDeleteReturnValue) {
		this.bulkDeleteReturnValue = bulkDeleteReturnValue;
	}

}
