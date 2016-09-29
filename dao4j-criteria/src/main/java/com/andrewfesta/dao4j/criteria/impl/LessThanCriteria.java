/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

/**
 * @author Andy
 *
 */
public class LessThanCriteria extends BinaryCriteria {
	
	public static final String LESS_THAN = "<";

	LessThanCriteria(String name, Object value,
			boolean isolate, String paramName) {
		super(name, LESS_THAN, value, isolate, paramName);
	}
	
	LessThanCriteria(String name, Object value,
			boolean isolate) {
		super(name, LESS_THAN, value, isolate);
	}

	LessThanCriteria(String name, Object value) {
		super(name, LESS_THAN, value);
	}

}
