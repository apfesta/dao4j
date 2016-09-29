/**
 * 
 */
package com.andrewfesta.dao4j.criteria;

import java.util.Collection;
import java.util.Map;

/**
 * @author Andy
 *
 */
public interface CriteriaFactory {
	
	public Criteria mapToCriteria(Map<String,Object> criteriaMap);
	
	public Criteria equals(String name, Object value);
	
	public Criteria equals(String name, Object value, boolean isolate);
	
	public Criteria equals(Criteria function, Object value, String paramName);
	
	public Criteria equals(Criteria function, Object value, boolean isolate, String paramName);
	
	public Criteria and(Criteria leftCriteria, Criteria rightCriteria);
	
	public Criteria and(Criteria... criteria);
		
	public Criteria and(Criteria leftCriteria, Criteria rightCriteria, boolean isolate);

	public Criteria or(Criteria leftCriteria, Criteria rightCriteria);
	
	public Criteria or (Criteria... criteria);
	
	public Criteria or(Criteria leftCriteria, Criteria rightCriteria, boolean isolate);

	public Criteria greaterThan(String name, Object value);
	
	public Criteria greaterThan(String name, Object value, boolean isolate);
	
	public Criteria greaterThan(String name, Object value, boolean isolate, String paramName);
	
	public Criteria greaterThanEquals(String name, Object value);
	
	public Criteria greaterThanEquals(String name, Object value, boolean isolate);
	
	public Criteria greaterThanEquals(String name, Object value, boolean isolate, String paramName);
	
	public Criteria lessThan(String name, Object value);
	
	public Criteria lessThan(String name, Object value, boolean isolate);
	
	public Criteria lessThan(String name, Object value, boolean isolate, String paramName);
	
	public Criteria lessThanEquals(String name, Object value);
	
	public Criteria lessThanEquals(String name, Object value, boolean isolate);
	
	public Criteria lessThanEquals(String name, Object value, boolean isolate, String paramName);
	
	public Criteria like(String name, String value);
	
	public Criteria like(String name, String value, boolean isolate);
	
	public Criteria like(String name, String value, boolean isolate, String paramName);
	
	public Criteria between(String name, Object lower, Object upper);
	
	public Criteria between(String name, Object lower, Object upper, boolean isolate);
	
	public Criteria between(String name, Object lower, Object upper, boolean isolate, String paramNameLower, String paramNameUpper);
	
	public Criteria in(String name, Object[] values);
	
	public Criteria in(String name, Object[] values, boolean isolate);
	
	public Criteria in(String name, Object[] values, boolean isolate, String paramName);
	
	public Criteria in(String name, Collection<?> values);
	
	public Criteria in(String name, Collection<?> values, boolean isolate);
	
	public Criteria in(String name, Collection<?> values, boolean isolate, String paramName);
	
	public Criteria isNull(String name);
	
	public Criteria isNull(String name, boolean isolate);
	
	public Criteria isNotNull(String name);
	
	public Criteria isNotNull(String name, boolean isolate);
		
	public Criteria not(Criteria criteria);
	
	public Criteria not(Criteria criteria, boolean isolate);

	public Criteria nativeExpression(String expression);
	
	public Criteria nativeExpression(String expression, boolean isolate);
	
	public Criteria nativeExpression(String expression, String paramName, Object value);
	
	public Criteria nativeExpression(String expression, String paramName, Object value, boolean isolate);
	
	public Criteria function(String functionName, Criteria[] params);
	
	public Criteria function(String functionName, Criteria[] params, boolean nativeFunction);
	
	public Criteria year(String name);
	
	public Criteria month(String name);
	
	public Criteria day(String name);
	
	public Criteria substring(String name, int start, int length);
	
}
