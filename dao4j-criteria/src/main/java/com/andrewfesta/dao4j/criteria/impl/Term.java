/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

import java.util.Map;

import com.andrewfesta.dao4j.criteria.Criteria;
import com.andrewfesta.dao4j.criteria.TermDecorator;

/**
 * @author Andy
 *
 */
public class Term implements Criteria {
	
	String name;
	
	public Term(String name) {
		super();
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.andrewfesta.criteria.Criteria#getBindValues()
	 */
	public Object[] getBindValues() {
		return null;
	}
	
	@Override
	public Map<String, ?> getNamedValues() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.andrewfesta.criteria.Criteria#getExpression(com.andrewfesta.criteria.TermDecorator)
	 */
	public String getExpression(TermDecorator termDecorator) {
		return termDecorator.getExpression(name);
	}

	@Override
	public String toString() {
		return name.toString();
	}
	
	

}
