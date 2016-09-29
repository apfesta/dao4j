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
public class FunctionTerm extends AbstractCriteria {
	
	String functionName;
	Criteria[] parameters;
	boolean nativeFunction;
	
	/**
	 * 
	 * @param functionName
	 * @param parameters
	 * @param nativeFunction this function should be written in native sql vs the JPQL "FUNCTION()"
	 */
	public FunctionTerm(String functionName, Criteria[] parameters, boolean nativeFunction) {
		super();
		this.functionName = functionName;
		this.parameters = parameters;
		this.nativeFunction = nativeFunction;
		this.validateUniqueParamNames();
	}

	/* (non-Javadoc)
	 * @see com.andrewfesta.criteria.Criteria#getExpression(com.andrewfesta.criteria.TermDecorator)
	 */
	public String getExpression(TermDecorator termDecorator) {
		StringBuffer s = new StringBuffer();
		if (nativeFunction) {
			s.append(functionName);
			s.append("(");
		} else {
			s.append("FUNCTION(");
			s.append("'"+functionName+"'");
			if (parameters.length>0) {
				s.append(",");
			}
		}
		
		for (int i=0; i<parameters.length; i++) {
			s.append(parameters[i].getExpression(termDecorator));
			if (i+1<parameters.length) {
				s.append(",");
			}
		}
		s.append(")");
		return s.toString();
	}

	/* (non-Javadoc)
	 * @see com.andrewfesta.criteria.Criteria#getBindValues()
	 */
	@SuppressWarnings("deprecation")
	public Object[] getBindValues() {
		Collection<Object> bindValues = new ArrayList<Object>();
		for (Criteria parameter: parameters) {
			Object[] values = parameter.getBindValues();
			if (values!=null) {
				bindValues.addAll(Arrays.asList(values));
			}
		}
		return bindValues.toArray();
	}

	@Override
	public Map<String, ?> getNamedValues() {
		Map<String, Object> bindValues = new LinkedHashMap<String, Object>();
		for (Criteria parameter: parameters) {
			Map<String, ?> values = parameter.getNamedValues();
			if (values!=null) {
				bindValues.putAll(values);
			}
		}
		return bindValues;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FunctionTerm) {
			return super.equals(obj)
			&& Arrays.deepEquals(this.getBindValues(),
					((FunctionTerm) obj).getBindValues());
		} else {
			return false;
		}
	}
	
	protected void validateUniqueParamNames() {
		
	}

}
