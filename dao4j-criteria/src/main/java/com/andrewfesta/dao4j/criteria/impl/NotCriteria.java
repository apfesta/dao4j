/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

import com.andrewfesta.dao4j.criteria.Criteria;

/**
 * @author Andy
 *
 */
public class NotCriteria extends UniaryCriteria {
	
	public static final String NOT = "NOT";

	public NotCriteria(Criteria criteria, boolean isolate) {
		super(criteria, NOT, true, isolate);
	}

	public NotCriteria(Criteria criteria) {
		this(criteria, false);
	}

	public NotCriteria(String name, boolean isolate) {
		super(name, NOT, true, isolate);
	}

	public NotCriteria(String name) {
		this(name, false);
	}
	
	

}
