/**
 * 
 */
package com.andrewfesta.dao4j.domain;

import java.io.Serializable;


/**
 * @author Andy
 *
 */
@SuppressWarnings("serial")
public class PersistableObject<ID extends Serializable> implements Persistable<ID> {

	private ID id;
	
	private int hashCode = Integer.MIN_VALUE;
		
	public PersistableObject() {
		super();
	}
	
	public PersistableObject(ID id) {
		super();
		this.id = id;
	}

	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	
	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			
		}
		return this.hashCode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj==null || !(obj instanceof PersistableObject)) {
			return false;
		}
				
		if (this.getClass().isAssignableFrom(obj.getClass()) 
						|| obj.getClass().isAssignableFrom(this.getClass())) {
			
			PersistableObject<?> obj2 = (PersistableObject<?>) obj;
			
			if (this.getId()==null && obj2.getId()==null) {
				return true;
			} else if (this.getId()==null ^ obj2.getId()==null) {
				return false;
			}
			return (this.getId().equals(obj2.getId()));
		}
		return false;
	}

	
}
