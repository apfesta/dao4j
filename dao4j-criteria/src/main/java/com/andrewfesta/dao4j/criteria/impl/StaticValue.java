/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

import java.util.Map;

import com.andrewfesta.dao4j.criteria.Criteria;
import com.andrewfesta.dao4j.criteria.TermDecorator;

/**
 * A hard coded string or numeric value
 * 
 * @author Andy
 *
 */
public class StaticValue implements Criteria {
	
	Object value;
	
	public StaticValue(Object value) {
		super();
		this.value = value;
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
		if (value instanceof Number) {
			return value.toString();
		} else {
			return "'"+value.toString()+"'";
		}
	}

	@Override
	public String toString() {
		if (value instanceof Number) {
			return value.toString();
		} else {
			return "'"+value.toString()+"'";
		}
	}


}
