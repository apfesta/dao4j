/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.andrewfesta.dao4j.criteria.Criteria;
import com.andrewfesta.dao4j.criteria.TermDecorator;

/**
 * @author Andy
 *
 */
public abstract class BinaryCriteria extends AbstractCriteria {
	
	protected Criteria leftCriteria;
	protected Criteria rightCriteria;
	protected String operator;
	protected boolean isolate;
	
	

	public BinaryCriteria(Criteria leftCriteria, String operator,
			Criteria rightCriteria, boolean isolate) {
		super();
		this.leftCriteria = leftCriteria;
		this.operator = operator;
		this.rightCriteria = rightCriteria;
		this.isolate = isolate;
		this.validateUniqueParamNames();
	}
		
	public BinaryCriteria(Criteria leftCriteria, String operator,
			Criteria rightCriteria) {
		this(leftCriteria, operator, rightCriteria, false);
	}
	
	public BinaryCriteria(String name, String operator, Object value, boolean isolate, String paramName) {
		this(new Term(name), operator, new Placeholder(paramName, value), isolate);
	}
	
	public BinaryCriteria(String name, String operator, Object value, boolean isolate) {
		this(name, operator, value, isolate, name);
	}
	
	public BinaryCriteria(String name, String operator, Object value) {
		this(name, operator, value, false);
	}
	
	String getName() {
		if (leftCriteria instanceof Term) {
			return ((Term)leftCriteria).name;
		}
		return null;
	}
	Object getValue() {
		if (rightCriteria instanceof Placeholder) {
			return ((Placeholder)rightCriteria).value;
		}
		return null;
	}
	

	/* (non-Javadoc)
	 * @see com.andrewfesta.criteria.Criteria#getBindValues()
	 */
	@SuppressWarnings("deprecation")
	public Object[] getBindValues() {
		Object[] leftValues = leftCriteria.getBindValues();
		Object[] rightValues = rightCriteria.getBindValues();

		Collection<Object> values = new ArrayList<Object>();
		if (leftValues!=null) {
			values.addAll(Arrays.asList(leftValues));
		}
		if (rightValues!=null) {
			values.addAll(Arrays.asList(rightValues));
		}
		return values.toArray();
	}

	@Override
	public Map<String, ?> getNamedValues() {
		Map<String, ?> leftValues = leftCriteria.getNamedValues();
		Map<String, ?> rightValues = rightCriteria.getNamedValues();

		Map<String, Object> values = new LinkedHashMap<String, Object>();
		if (leftValues!=null) {
			values.putAll(leftValues);
		}
		if (rightValues!=null) {
			values.putAll(rightValues);
		}
		return values;
	}

	/* (non-Javadoc)
	 * @see com.andrewfesta.criteria.Criteria#getExpression()
	 */
	public String getExpression(TermDecorator termDecorator) {
		StringBuffer buffer = new StringBuffer();
		if (isolate) {
			buffer.append("(");
		}
		buffer.append(leftCriteria.getExpression(termDecorator));
		buffer.append(" "+operator+" ");
		buffer.append(rightCriteria.getExpression(termDecorator));
		if (isolate) {
			buffer.append(")");
		}
		return buffer.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BinaryCriteria) {
			return super.equals(obj)
					&& Arrays.deepEquals(this.getBindValues(),
							((BinaryCriteria) obj).getBindValues());
		} else {
			return false;
		}
	}
	
	protected void validateUniqueParamNames() {
		Map<String, ?> leftValues = leftCriteria.getNamedValues();
		Map<String, ?> rightValues = rightCriteria.getNamedValues();
		
		if (rightValues!=null && leftValues!=null) {
			for (String name: rightValues.keySet()) {
				if (leftValues.containsKey(name)) {
					throw new DuplicateNamedParameterException(name);
				}
			}
		}
	}

}
