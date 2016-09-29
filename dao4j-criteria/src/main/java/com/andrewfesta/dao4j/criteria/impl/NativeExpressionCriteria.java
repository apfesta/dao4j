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
public class NativeExpressionCriteria implements Criteria {
	
	private String expression;
	private Map<String, ?> namedValues;
	private boolean isolate=false;
	
	public NativeExpressionCriteria(String expression) {
		this(expression, null, false);
	}
	
	public NativeExpressionCriteria(String expression, boolean isolate) {
		this(expression, null, isolate);
	}

	public NativeExpressionCriteria(String expression, String paramName, Object value) {
		this(expression, Collections.singletonMap(paramName, value), false );
	}
	
	public NativeExpressionCriteria(String expression, String paramName, Object value, boolean isolate) {
		this(expression, Collections.singletonMap(paramName, value), isolate );
	}
	
	public NativeExpressionCriteria(String expression, Map<String, ?> values) {
		this(expression, values, false );
	}
	
	public NativeExpressionCriteria(String expression, Map<String, ?> values, boolean isolate) {
		super();
		this.expression = expression;
		this.namedValues = values;
		this.isolate = isolate;
	}

	/* (non-Javadoc)
	 * @see com.andrewfesta.criteria.Criteria#getBindValues()
	 */
	public Object[] getBindValues() {
		return null;
	}

	@Override
	public Map<String, ?> getNamedValues() {
		return namedValues;
	}

	/* (non-Javadoc)
	 * @see com.andrewfesta.criteria.Criteria#getExpression(com.andrewfesta.criteria.TermDecorator)
	 */
	public String getExpression(TermDecorator termDecorator) {
		StringBuffer buffer = new StringBuffer();
		if (isolate) {
			buffer.append("(");
		}
		buffer.append(expression);
		if (isolate) {
			buffer.append("(");
		}
		return buffer.toString();
	}

}
