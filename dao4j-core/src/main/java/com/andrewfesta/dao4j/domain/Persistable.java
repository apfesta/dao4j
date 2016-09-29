/*
 * Created on Sep 26, 2005
 */
package com.andrewfesta.dao4j.domain;

import java.io.Serializable;

/**
 * @author Andy
 */
public interface Persistable<ID extends Serializable> extends Serializable {
	
	public ID getId();
	
	public void setId(ID id);
		
}
