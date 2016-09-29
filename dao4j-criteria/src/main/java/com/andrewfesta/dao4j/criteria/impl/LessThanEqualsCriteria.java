/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

/**
 * @author Andy
 *
 */
public class LessThanEqualsCriteria extends BinaryCriteria {
	
	public static final String LESS_THAN_EQUALS = "<=";

	LessThanEqualsCriteria(String name, Object value,
			boolean isolate, String paramName) {
		super(name, LESS_THAN_EQUALS, value, isolate, paramName);
	}
	
	LessThanEqualsCriteria(String name, Object value,
			boolean isolate) {
		super(name, LESS_THAN_EQUALS, value, isolate);
	}

	LessThanEqualsCriteria(String name, Object value) {
		super(name, LESS_THAN_EQUALS, value);
	}

}
