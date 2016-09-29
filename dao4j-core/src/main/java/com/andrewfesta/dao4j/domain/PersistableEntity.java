/*
 * Created on Sep 26, 2005
 */
package com.andrewfesta.dao4j.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Andy
 */
@SuppressWarnings("serial")
public class PersistableEntity<ID extends Serializable> extends PersistableObject<ID>
	implements Entity<ID> {
	
	private boolean newFlag=false;
	private Object userModified;
	private Object userCreated;
	private Date dateModified;
	private Date dateCreated;
	
	

	/**
	 * 
	 */
	public PersistableEntity() {
		super();
	}
		
	/**
	 * @param _id
	 */
	public PersistableEntity(ID _id, boolean newFlag) {
		super(_id);
		this.newFlag = newFlag;
	}
	
	
	/**
	 * @return Returns the newFlag.
	 */
	public boolean isNew() {
		return newFlag;
	}

	/**
	 * @param newFlag The newFlag to set.
	 */
	public void setNew(boolean newFlag) {
		this.newFlag = newFlag;
	}
	
	public Object getUserModified() {
		return userModified;
	}

	public void setUserModified(Object userModified) {
		this.userModified = userModified;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Object getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Object userCreated) {
		this.userCreated = userCreated;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}
