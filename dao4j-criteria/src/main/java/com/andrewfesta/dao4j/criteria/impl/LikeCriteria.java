/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;


/**
 * @author Andy
 *
 */
public class LikeCriteria extends BinaryCriteria {
	
	public static final String LIKE = "LIKE";

	public LikeCriteria(String name, String value,
			boolean isolate, String paramName) {
		super(name, LIKE, value, isolate, paramName);
	}
	
	public LikeCriteria(String name, String value,
			boolean isolate) {
		super(name, LIKE, value, isolate);
	}

	public LikeCriteria(String name, String value) {
		super(name, LIKE, value);
	}

}
