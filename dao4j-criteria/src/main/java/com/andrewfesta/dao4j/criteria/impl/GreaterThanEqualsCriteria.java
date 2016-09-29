/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

/**
 * @author Andy
 *
 */
public class GreaterThanEqualsCriteria extends BinaryCriteria {
	
	public static final String GREATER_THAN_EQUALS = ">=";

	GreaterThanEqualsCriteria(String name, Object value,
			boolean isolate, String paramName) {
		super(name, GREATER_THAN_EQUALS, value, isolate, paramName);
	}
	
	GreaterThanEqualsCriteria(String name, Object value,
			boolean isolate) {
		super(name, GREATER_THAN_EQUALS, value, isolate);
	}

	GreaterThanEqualsCriteria(String name, Object value) {
		super(name, GREATER_THAN_EQUALS, value);
	}

}
