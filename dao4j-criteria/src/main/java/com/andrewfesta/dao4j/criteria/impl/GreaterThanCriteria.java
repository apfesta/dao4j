/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

/**
 * @author Andy
 *
 */
public class GreaterThanCriteria extends BinaryCriteria {
	
	public static final String GREATER_THAN = ">";

	GreaterThanCriteria(String name, Object value,
			boolean isolate, String paramName) {
		super(name, GREATER_THAN, value, isolate, paramName);
	}
	
	GreaterThanCriteria(String name, Object value,
			boolean isolate) {
		super(name, GREATER_THAN, value, isolate);
	}

	GreaterThanCriteria(String name, Object value) {
		super(name, GREATER_THAN, value);
	}

}
