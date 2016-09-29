/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

import java.util.Collection;


/**
 * @author Andy
 *
 */
public class InCriteria extends EqualsCriteria {
	
	public static final String IN = "in";
	

	<T> InCriteria(String name, T[] values,
			boolean isolate, String paramName) {
		super(name, getValue(values), isolate, paramName);
		this.operator = getOperator(values);
	}
	
	<T> InCriteria(String name, T[] values,
			boolean isolate) {
		super(name, getValue(values), isolate);
		this.operator = getOperator(values);
	}

	<T> InCriteria(String name, T[] values) {
		super(name, values);
		this.operator = getOperator(values);
	}
	
	<T> InCriteria(String name, Collection<T> values,
			boolean isolate, String paramName) {
		super(name, getValue(values), isolate, paramName);
		this.operator = getOperator(values);
	}
	
	<T> InCriteria(String name, Collection<T> values,
			boolean isolate) {
		super(name, getValue(values), isolate);
		this.operator = getOperator(values);
	}

	<T> InCriteria(String name, Collection<T> values) {
		super(name, values);
		this.operator = getOperator(values);
	}
	
	/**
	 * If there's only one value, we can use a standard equals criteria
	 * 
	 * @param values
	 * @return
	 */
	private static <T> String getOperator(T[] values) {
		if (values.length==1) {
			return EQUALS;
		} 
		return IN;
	}
	
	/**
	 * If there's only one value, we can use a standard equals criteria
	 * 
	 * @param values
	 * @return
	 */
	private static <T> String getOperator(Collection<T> values) {
		if (values.size()==1) {
			return EQUALS;
		} 
		return IN;
	}
	
	/**
	 * If there's only one value, we can use a standard equals criteria
	 * 
	 * @param values
	 * @return
	 */
	private static <T> Object getValue(T[] values) {
		if (values.length==1) {
			return values[0];
		} 
		return values;
	}
	
	private static <T> Object getValue(Collection<T> values) {
		if (values.size()==1) {
			return values.iterator().next();
		} 
		return values;
	}
	

}
