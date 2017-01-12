/**
 * 
 */
package com.andrewfesta.dao4j.dao.mock;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.andrewfesta.dao4j.dao.DAO;
import com.andrewfesta.dao4j.domain.Entity;

/**
 * @author Andy
 *
 */
public class MockDAO<D extends Entity<ID>,ID extends Serializable> 
	implements DAO<D, ID> {

	private D getReturnValue;
	private Date date = new Date();
	private ID saveReturnValue;
	
	private boolean saved;
	private boolean deleted;
	
	public void delete(D p) {
		deleted = true;
	}

	public D get(ID id) {
		return getReturnValue;
	}

	public Date getDate() {
		return date;
	}

	public ID save(D p) {
		saved = true;
		return saveReturnValue;
	}
	
	public void persist(D d) {
		saved = true;
	}
	
	public D merge(D d) {
		return d;
	}

	public void save(Collection<D> items) {
	}

	public void persist(Collection<D> items) {
	}

	public void setGetReturnValue(D getReturnValue) {
		this.getReturnValue = getReturnValue;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setSaveReturnValue(ID saveReturnValue) {
		this.saveReturnValue = saveReturnValue;
	}

	public boolean isSaved() {
		return saved;
	}

	public boolean isDeleted() {
		return deleted;
	}

}
