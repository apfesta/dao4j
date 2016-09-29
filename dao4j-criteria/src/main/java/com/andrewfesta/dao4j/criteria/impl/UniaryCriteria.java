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
public class UniaryCriteria extends AbstractCriteria {
	
	protected Criteria criteria;
	protected String operator;
	protected boolean isolate;
	private boolean operatorLeftAligned;
		

	public UniaryCriteria(Criteria criteria, String operator,
			boolean operatorLeftAligned,  boolean isolate) {
		super();
		this.criteria = criteria;
		this.operator = operator;
		this.isolate = isolate;
		this.operatorLeftAligned = operatorLeftAligned;
	}
	
	public UniaryCriteria(Criteria criteria, String operator,
			boolean operatorLeftAligned) {
		this(criteria, operator, operatorLeftAligned, false);
	}
	
	public UniaryCriteria(String name, String operator,
			boolean operatorLeftAligned,  boolean isolate) {
		this(new Term(name), operator, operatorLeftAligned, isolate);
	}
	
	public UniaryCriteria(String name, String operator,
			boolean operatorLeftAligned) {
		this(name, operator, operatorLeftAligned, false);
	}
	
	
	String getName() {
		if (criteria instanceof Term) {
			return ((Term)criteria).name;
		}
		return null;
	}
	

	@SuppressWarnings("deprecation")
	public Object[] getBindValues() {
		Object[] bindValues = criteria.getBindValues();
		
		Collection<Object> values = new ArrayList<Object>();
		if (bindValues!=null) {
			values.addAll(Arrays.asList(bindValues));
		}
		
		return values.toArray();
	}

	@Override
	public Map<String, ?> getNamedValues() {
		Map<String, ?> bindValues = criteria.getNamedValues();
		
		Map<String, Object> values = new LinkedHashMap<String, Object>();
		if (bindValues!=null) {
			values.putAll(bindValues);
		}
		
		return values;
	}

	public String getExpression(TermDecorator termDecorator) {
		StringBuffer buffer = new StringBuffer();
		if (isolate) {
			buffer.append("(");
		}
		if (operatorLeftAligned) {
			buffer.append(operator+" ");
		}
		buffer.append(criteria.getExpression(termDecorator));
		if (!operatorLeftAligned) {
			buffer.append(" "+operator);
		}
		if (isolate) {
			buffer.append(")");
		}
		return buffer.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UniaryCriteria) {
			return super.equals(obj)
			&& Arrays.deepEquals(this.getBindValues(),
					((UniaryCriteria) obj).getBindValues());
		} else {
			return false;
		}
	}

}
