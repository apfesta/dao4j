/*
 * Created on Sep 26, 2005
 */
package com.andrewfesta.dao4j.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.andrewfesta.dao4j.domain.Entity;



/**
 * @author Andy
 */
public interface DAO<D extends Entity<ID>,ID extends Serializable> {
		
	public D get(ID id);
	
	/**
	 * Persists the entity now, thus returning the ID.  Use this if you need the
	 * ID right now.  Use persist if you don't care.
	 * 
	 * @param p
	 * @return
	 */
	public ID save(D p);
	
	/**
	 * Persists the entity at the conclusion of the transaction.  Use this if
	 * you don't care the the object won't persist right now.  IDs won't be
	 * generated here.
	 * 
	 * @param d
	 */
	public void persist(D d);
	
	/**
	 * Persists the entity and returns the persistant version attached to the
	 * session
	 * 
	 * @param d
	 * @return
	 */
	public D merge(D d);
		
	public void delete(D p);
	
	public void save(Collection<D> items);
	
	public void persist(Collection<D> items);
		
	public Date getDate();
	
}
