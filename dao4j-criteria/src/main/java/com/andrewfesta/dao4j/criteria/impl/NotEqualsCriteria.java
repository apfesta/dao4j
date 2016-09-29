/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;


/**
 * @author Andy
 *
 */
public class NotEqualsCriteria extends BinaryCriteria {
	
	public static final String EQUALS = "<>";


	NotEqualsCriteria(String name, Object value,
			boolean isolate) {
		super(name, EQUALS, value, isolate);
	}

	NotEqualsCriteria(String name, Object value) {
		super(name, EQUALS, value);
	}
	
	NotEqualsCriteria(EqualsCriteria equalsCritieria) {
		this(equalsCritieria.getName(), equalsCritieria.getValue());
	}
	
	NotEqualsCriteria(EqualsCriteria equalsCritieria, boolean isolate) {
		this(equalsCritieria.getName(), equalsCritieria.getValue(), isolate);
	}
		

}
