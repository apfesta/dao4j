/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

import com.andrewfesta.dao4j.criteria.Criteria;


/**
 * @author Andy
 *
 */
public class EqualsCriteria extends BinaryCriteria {
	
	public static final String EQUALS = "=";

	EqualsCriteria(String name, Object value,
			boolean isolate, String paramName) {
		super(name, EQUALS, value, isolate, paramName);
	}
	
	EqualsCriteria(String name, Object value,
			boolean isolate) {
		super(name, EQUALS, value, isolate);
	}

	EqualsCriteria(String name, Object value) {
		super(name, EQUALS, value);
	}
	
	EqualsCriteria(Criteria function, Object value,
			boolean isolate, String paramName) {
		super(function, EQUALS, new Placeholder(paramName, value), isolate);
	}
	
	EqualsCriteria(Criteria function, Object value, String paramName) {
		super(function, EQUALS, new Placeholder(paramName, value));
	}
		

}
