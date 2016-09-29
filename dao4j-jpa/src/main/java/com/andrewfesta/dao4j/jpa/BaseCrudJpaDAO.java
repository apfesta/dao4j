package com.andrewfesta.dao4j.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;

import com.andrewfesta.dao4j.dao.DAO;
import com.andrewfesta.dao4j.domain.Entity;

/**
 * Implements the DAO's CRUD operations by using the 
 * Java Persistance (JPA) framework
 * 
 * @author apfesta
 *
 * @param <D>
 * @param <ID>
 */
public class BaseCrudJpaDAO<D extends Entity<ID>,ID extends Serializable> 
	implements DAO<D,ID> {

	protected EntityManager em;
	
	protected Class<D> domainClass;
		
	public BaseCrudJpaDAO(Class<D> domainClass) {
		super();
		this.domainClass = domainClass;
	}
	
	/* (non-Javadoc)
	 * @see net.mixednutz.foundation.model.dao.DAO#load(java.io.Serializable)
	 */
	public D get(ID id) {
		return em.find(domainClass, id);
	}
	
	public void detach(D d) {
		em.detach(d);
	}
	
	public void refresh(D d) {
		em.refresh(d);
	}
	
	public void persist(D d) {
		em.persist(d);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.andrewfesta.dao.DAO#merge(com.andrewfesta.domain.Entity)
	 */
	public D merge(D d) {
		return em.merge(d);
	}


	/* (non-Javadoc)
	 * @see net.mixednutz.foundation.model.dao.DAO#save(net.mixednutz.foundation.model.Persistable)
	 */
	public ID save(D p) {
		if (p.isNew()) {
			//Add a new object to the Persistence Context
			em.persist(p);
		} else if (!em.contains(p)) {
			//Merge if object is not managed by the Persistence Context
			p = em.merge(p);
		} 
		//Synchronize any changes to the persistence context to the database
		em.flush();
		return p.getId();
	}
	
	

	public void save(Collection<D> items) {
		//TODO Make more effecient - use persist(collection) instead?
		for (D item: items) {
			save(item);
		}
	}


	public void persist(Collection<D> items) {
		for (D item: items) {
			persist(item);
		}
	}


	/* (non-Javadoc)
	 * @see net.mixednutz.foundation.model.dao.DAO#delete(net.mixednutz.foundation.model.Persistable)
	 */
	public void delete(D p) {
		if (em.contains(p)) {
			em.remove(p);
		} else {
			/*
			 * Merging ensures that a "detached" entity will be re-attached before
			 * calling the remove method.
			 */
			em.remove(em.merge(p));
		}
	}	
	
	/**
	 * Delete method that provides a callback to the attached object before it
	 * gets removed.
	 * 
	 * @param p
	 * @param callback
	 */
	public void delete(D p, AttachedEntityCallback<D> callback) {
		if (em.contains(p)) {
			em.remove(p);
		} else {
			/*
			 * Merging ensures that a "detached" entity will be re-attached before
			 * calling the remove method.
			 */
			D attached = em.merge(p);
			/*
			 * Provide a call back so that we can remove this object from its
			 * parent collections before removal.
			 */
			callback.doWithAttachedEntity(attached);
			em.remove(attached);
		}
	}

	@Override
	public Date getDate() {
		return new Date();
	}
	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}
	
	/**
	 * A callback to manipulate an attached entity before its 
	 * detached.
	 *
	 * @param <D>
	 */
	public interface AttachedEntityCallback<D> {
		/**
		 * Execute before the entity is detached.
		 * @param mergedEntity
		 */
		void doWithAttachedEntity(D mergedEntity);
	}
}
