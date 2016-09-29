/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

/**
 * @author Andy
 *
 */
public class IsNullCriteria extends UniaryCriteria {
	
	public static final String IS_NULL = "IS NULL";


	public IsNullCriteria(String name, boolean isolate) {
		super(name, IS_NULL, false, isolate);
	}

	public IsNullCriteria(String name) {
		this(name, false);
	}


}
