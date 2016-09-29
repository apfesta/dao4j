/**
 * 
 */
package com.andrewfesta.dao4j.domain;

import java.io.Serializable;

/**
 * @author apfesta
 *
 */
public interface PersistableEnum<ID extends Serializable> {
	
	public ID getId();
	
}
