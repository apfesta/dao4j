/**
 * 
 */
package com.andrewfesta.dao4j.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Andy
 *
 */
public interface Entity<ID extends Serializable> extends Persistable<ID> {
	
	public boolean isNew();
	public void setNew(boolean newFlag);
				
	public Object getUserModified();
	public void setUserModified(Object user);
	
	public Object getUserCreated();
	public void setUserCreated(Object user);
	
	public Date getDateModified();
	public void setDateModified(Date dateCreated);
	
	public Date getDateCreated();
	public void setDateCreated(Date dateCreated);

}
