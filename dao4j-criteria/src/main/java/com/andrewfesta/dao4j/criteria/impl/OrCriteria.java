/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

import com.andrewfesta.dao4j.criteria.Criteria;

/**
 * @author Andy
 *
 */
public class OrCriteria extends BinaryCriteria {
	
	public static final String OR = "OR";

	OrCriteria(Criteria leftCriteria, 
			Criteria rightCriteria, boolean isolate) {
		super(leftCriteria, OR, rightCriteria, isolate);
	}

	OrCriteria(Criteria leftCriteria, 
			Criteria rightCriteria) {
		super(leftCriteria, OR, rightCriteria);
	}
	

}
