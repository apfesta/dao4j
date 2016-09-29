/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

import java.util.Collections;
import java.util.Map;

import com.andrewfesta.dao4j.criteria.Criteria;
import com.andrewfesta.dao4j.criteria.TermDecorator;

/**
 * @author Andy
 *
 */
public class Placeholder implements Criteria {
	
	String name;
	Object value;
	
	public Placeholder(String name, Object value) {
		super();
		this.name = name.replaceAll("\\.", "_");
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.andrewfesta.criteria.Criteria#getBindValues()
	 */
	public Object[] getBindValues() {
		if (value instanceof Object[]) {
			return (Object[]) value;
		}
		return new Object[] {value};
	}

	@Override
	public Map<String, ?> getNamedValues() {
		return Collections.singletonMap(name, value);
	}

	/* (non-Javadoc)
	 * @see com.andrewfesta.criteria.Criteria#getExpression(com.andrewfesta.criteria.TermDecorator)
	 */
	public String getExpression(TermDecorator termDecorator) {
		if (value instanceof Object[]) {
			StringBuffer buffer = new StringBuffer();
			Object[] values = (Object[]) value;
			if (values.length>1) {
				buffer.append("(");
			}
			buffer.append(":");
			buffer.append(name);
			if (values.length>1) {
				buffer.append(")");
			}
			return buffer.toString();
		}
		return ":"+name;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		if (value instanceof Object[]) {
			Object[] values = (Object[]) value;
			for (int i=0; i<values.length; i++) {
				buffer.append(values[i]);
				if (i+1<values.length) {
					buffer.append(",");
				}
			}
		} else {
			buffer.append(value);
		}
		buffer.append("]");
		
		return buffer.toString();
	}
	
	

}
