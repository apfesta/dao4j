/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

/**
 * @author Andy
 *
 */
public class IsNotNullCriteria extends UniaryCriteria {
	
	public static final String IS_NOT_NULL = "IS NOT NULL";


	public IsNotNullCriteria(String name, boolean isolate) {
		super(name, IS_NOT_NULL, false, isolate);
	}

	public IsNotNullCriteria(String name) {
		this(name, false);
	}
	
	public IsNotNullCriteria(IsNullCriteria isNullCriteria) {
		this(isNullCriteria.getName());
	}

	public IsNotNullCriteria(IsNullCriteria isNullCriteria, boolean isolate) {
		this(isNullCriteria.getName(), isolate);
	}

}
