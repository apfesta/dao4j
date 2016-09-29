/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

import com.andrewfesta.dao4j.criteria.Criteria;

/**
 * @author Andy
 *
 */
public class AndCriteria extends BinaryCriteria {
	
	public static final String AND = "AND";

	AndCriteria(Criteria leftCriteria, 
			Criteria rightCriteria, boolean isolate) {
		super(leftCriteria, AND, rightCriteria, isolate);
	}

	AndCriteria(Criteria leftCriteria, 
			Criteria rightCriteria) {
		super(leftCriteria, AND, rightCriteria);
	}
	

}
