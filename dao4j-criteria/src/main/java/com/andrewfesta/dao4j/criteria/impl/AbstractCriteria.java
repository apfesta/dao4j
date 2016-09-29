/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

import com.andrewfesta.dao4j.criteria.Criteria;
import com.andrewfesta.dao4j.criteria.TermDecorator;

/**
 * @author Andy
 *
 */
public abstract class AbstractCriteria implements Criteria {
	
	private static final TermDecorator toStringDecorator = new TermDecorator() {
		public String getExpression(String term) {
			return term;
		}};
		

	@Override
	public String toString() {
		return getExpression(toStringDecorator);
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AbstractCriteria) {
			return toString().equals(obj.toString());
		} else {
			return false;
		}
	}
	

}
