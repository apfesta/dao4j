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
public abstract class TernaryCriteria extends AbstractCriteria {
	
	private Criteria leftCriteria;
	private Criteria middleCriteria;
	private Criteria rightCriteria;
	private String operator1;
	private String operator2;
	private boolean isolate;
	
	

	public TernaryCriteria(Criteria leftCriteria, String operator1,
			Criteria middleCriteria, String operator2, Criteria rightCriteria, boolean isolate) {
		super();
		this.leftCriteria = leftCriteria;
		this.operator1 = operator1;
		this.middleCriteria = middleCriteria;
		this.operator2 = operator2;
		this.rightCriteria = rightCriteria;
		this.isolate = isolate;
		this.validateUniqueParamNames();
	}
		
	public TernaryCriteria(Criteria leftCriteria, String operator1,
			Criteria middleCriteria, String operator2, Criteria rightCriteria) {
		this(leftCriteria, operator1, middleCriteria, operator2, rightCriteria, false);
	}
	
	public TernaryCriteria(String name, String operator1, String paramName1, Object value1, String operator2, String paramName2, Object value2, boolean isolate) {
		this(new Term(name), operator1, new Placeholder(paramName1, value1), operator2, new Placeholder(paramName2, value2), isolate);
	}
	
	public TernaryCriteria(String name, String operator1, Object value1, String operator2, Object value2, boolean isolate) {
		this(new Term(name), operator1, new Placeholder(name+"1", value1), operator2, new Placeholder(name+"2", value2), isolate);
	}
	
	public TernaryCriteria(String name, String operator1, Object value1, String operator2, Object value2) {
		this(name, operator1, value1, operator2, value2, false);
	}
	

	/* (non-Javadoc)
	 * @see com.andrewfesta.criteria.Criteria#getBindValues()
	 */
	@SuppressWarnings("deprecation")
	public Object[] getBindValues() {
		Object[] leftValues = leftCriteria.getBindValues();
		Object[] middleValues = middleCriteria.getBindValues();
		Object[] rightValues = rightCriteria.getBindValues();
		
		Collection<Object> values = new ArrayList<Object>();
		if (leftValues!=null) {
			values.addAll(Arrays.asList(leftValues));
		}
		if (middleValues!=null) {
			values.addAll(Arrays.asList(middleValues));
		}
		if (rightValues!=null) {
			values.addAll(Arrays.asList(rightValues));
		}
		return values.toArray();
	}

	@Override
	public Map<String, ?> getNamedValues() {
		Map<String, ?> leftValues = leftCriteria.getNamedValues();
		Map<String, ?> middleValues = middleCriteria.getNamedValues();
		Map<String, ?> rightValues = rightCriteria.getNamedValues();
		
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		if (leftValues!=null) {
			values.putAll(leftValues);
		}
		if (middleValues!=null) {
			values.putAll(middleValues);
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
		buffer.append(" "+operator1+" ");
		buffer.append(middleCriteria.getExpression(termDecorator));
		buffer.append(" "+operator2+" ");
		buffer.append(rightCriteria.getExpression(termDecorator));
		if (isolate) {
			buffer.append(")");
		}
		return buffer.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TernaryCriteria) {
			return super.equals(obj)
			&& Arrays.deepEquals(this.getBindValues(),
					((TernaryCriteria) obj).getBindValues());
		} else {
			return false;
		}
	}
	
	protected void validateUniqueParamNames() {
		Map<String, ?> leftValues = leftCriteria.getNamedValues();
		Map<String, ?> middleValues = middleCriteria.getNamedValues();
		Map<String, ?> rightValues = rightCriteria.getNamedValues();
		
		if (middleValues!=null && leftValues!=null) {
			for (String name: middleValues.keySet()) {
				if (leftValues.containsKey(name)) {
					throw new DuplicateNamedParameterException(name);
				}
			}
		}
		if (rightValues!=null) {
			for (String name: rightValues.keySet()) {
				if (leftValues!=null && leftValues.containsKey(name)) {
					throw new DuplicateNamedParameterException(name);
				}
			}
		}
	}
	
}
