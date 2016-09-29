/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

import java.util.Collection;
import java.util.Map;

import com.andrewfesta.dao4j.criteria.Criteria;
import com.andrewfesta.dao4j.criteria.CriteriaFactory;

/**
 * @author Andy
 *
 */
public class DefaultCriteriaFactory implements CriteriaFactory {

	/* (non-Javadoc)
	 * @see com.andrewfesta.criteria.CriteriaFactory#mapToCriteria(java.util.Map)
	 */
	public Criteria mapToCriteria(Map<String, Object> criteriaMap) {
		Criteria criteria = null;
		for (Map.Entry<String, Object> entry : criteriaMap.entrySet()) {
			if (criteria==null) {
				criteria = equals(entry.getKey(), entry.getValue());
			} else {
				criteria = and(criteria, 
						equals(entry.getKey(), entry.getValue()));
			}
		}
		return criteria;
	}

	public Criteria equals(String name, Object value) {
		return new EqualsCriteria(name, value);
	}
	
	public Criteria equals(String name, Object value, boolean isolate) {
		return new EqualsCriteria(name, value, isolate);
	}
	
	public Criteria equals(Criteria function, Object value, String paramName) {
		return new EqualsCriteria(function, value, paramName);
	}
	
	public Criteria equals(Criteria function, Object value, boolean isolate, String paramName) {
		return new EqualsCriteria(function, value, isolate, paramName);
	}

	public Criteria and(Criteria leftCriteria, Criteria rightCriteria) {
		return new AndCriteria(leftCriteria, rightCriteria);
	}

	public Criteria and(Criteria leftCriteria, Criteria rightCriteria,
			boolean isolate) {
		return new AndCriteria(leftCriteria, rightCriteria, isolate);
	}
		
	public Criteria and(Criteria... criteriaArray) {
		Criteria newCrit = null;
		for (Criteria c : criteriaArray) {
			if (newCrit==null) {
				newCrit = c;
			} else {
				newCrit = and(newCrit, c);
			}
		}
		return newCrit;
	}

	public Criteria or(Criteria leftCriteria, Criteria rightCriteria) {
		return new OrCriteria(leftCriteria, rightCriteria);
	}

	public Criteria or(Criteria leftCriteria, Criteria rightCriteria,
			boolean isolate) {
		return new OrCriteria(leftCriteria, rightCriteria, isolate);
	}
	
	public Criteria or(Criteria... criteriaArray) {
		Criteria newCrit = null;
		for (Criteria c : criteriaArray) {
			if (newCrit==null) {
				newCrit = c;
			} else {
				newCrit = or(newCrit, c);
			}
		}
		return newCrit;
	}

	public Criteria greaterThan(String name, Object value) {
		return new GreaterThanCriteria(name, value);
	}
	
	public Criteria greaterThan(String name, Object value, boolean isolate) {
		return new GreaterThanCriteria(name, value, isolate);
	}
	
	public Criteria greaterThan(String name, Object value, boolean isolate, String paramName) {
		return new GreaterThanCriteria(name, value, isolate, paramName);
	}
	
	public Criteria greaterThanEquals(String name, Object value) {
		return new GreaterThanEqualsCriteria(name, value);
	}
	
	public Criteria greaterThanEquals(String name, Object value, boolean isolate) {
		return new GreaterThanEqualsCriteria(name, value, isolate);
	}
	
	public Criteria greaterThanEquals(String name, Object value, boolean isolate, String paramName) {
		return new GreaterThanEqualsCriteria(name, value, isolate, paramName);
	}
	
	public Criteria lessThan(String name, Object value) {
		return new LessThanCriteria(name, value);
	}
	
	public Criteria lessThan(String name, Object value, boolean isolate) {
		return new LessThanCriteria(name, value, isolate);
	}
	
	public Criteria lessThan(String name, Object value, boolean isolate, String paramName) {
		return new LessThanCriteria(name, value, isolate, paramName);
	}
	
	public Criteria lessThanEquals(String name, Object value) {
		return new LessThanEqualsCriteria(name, value);
	}
	
	public Criteria lessThanEquals(String name, Object value, boolean isolate) {
		return new LessThanEqualsCriteria(name, value, isolate);
	}
	
	public Criteria lessThanEquals(String name, Object value, boolean isolate, String paramName) {
		return new LessThanEqualsCriteria(name, value, isolate, paramName);
	}
	
	public Criteria like(String name, String value) {
		return new LikeCriteria(name, value);
	}
	
	public Criteria like(String name, String value, boolean isolate) {
		return new LikeCriteria(name, value, isolate);
	}
	
	public Criteria like(String name, String value, boolean isolate, String paramName) {
		return new LikeCriteria(name, value, isolate, paramName);
	}
	
	
	
	public Criteria between(String name, Object lower, Object upper,
			boolean isolate, String paramNameLower, String paramNameUpper) {
		return new BetweenCriteria(name, lower, upper, isolate, paramNameLower, paramNameUpper);
	}

	public Criteria between(String name, Object lower, Object upper,
			boolean isolate) {
		return new BetweenCriteria(name, lower, upper, isolate);
	}

	public Criteria between(String name, Object lower, Object upper) {
		return new BetweenCriteria(name, lower, upper);
	}

	public Criteria nativeExpression(String expression) {
		return new NativeExpressionCriteria(expression);
	}
	
	public Criteria nativeExpression(String expression, boolean isolate) {
		return new NativeExpressionCriteria(expression, isolate);
	}
	
	public Criteria nativeExpression(String expression, String paramName, Object value) {
		return new NativeExpressionCriteria(expression, paramName, value);
	}
	
	public Criteria nativeExpression(String expression, String paramName, Object value, boolean isolate) {
		return new NativeExpressionCriteria(expression, paramName, value, isolate);
	}
	
	public Criteria in(String name, Object[] values) {
		return new InCriteria(name, values);
	}

	public Criteria in(String name, Object[] values, boolean isolate) {
		return new InCriteria(name, values, isolate);
	}
	
	public Criteria in(String name, Object[] values, boolean isolate, String paramName) {
		return new InCriteria(name, values, isolate, paramName);
	}
	
	public Criteria in(String name, Collection<?> values) {
		return new InCriteria(name, values);
	}

	public Criteria in(String name, Collection<?> values, boolean isolate) {
		return new InCriteria(name, values, isolate);
	}
	
	public Criteria in(String name, Collection<?> values, boolean isolate, String paramName) {
		return new InCriteria(name, values, isolate, paramName);
	}

	public Criteria isNull(String name) {
		return new IsNullCriteria(name);
	}

	public Criteria isNull(String name, boolean isolate) {
		return new IsNullCriteria(name, isolate);
	}

	public Criteria isNotNull(String name) {
		return new IsNotNullCriteria(name);
	}

	public Criteria isNotNull(String name, boolean isolate) {
		return new IsNotNullCriteria(name, isolate);
	}

	public Criteria not(Criteria criteria) {
		if (criteria instanceof IsNullCriteria) {
			return new IsNotNullCriteria((IsNullCriteria)criteria);
		} 
		if (criteria instanceof EqualsCriteria) {
			return new NotEqualsCriteria((EqualsCriteria)criteria);
			
		}
		return new NotCriteria(criteria);
	}

	public Criteria not(Criteria criteria, boolean isolate) {
		if (criteria instanceof IsNullCriteria) {
			return new IsNotNullCriteria((IsNullCriteria)criteria, isolate);
		}
		if (criteria instanceof EqualsCriteria) {
			return new NotEqualsCriteria((EqualsCriteria)criteria, isolate);
			
		}
		return new NotCriteria(criteria, isolate);
	}
	
	public Criteria year(String name) {
		return new FunctionTerm("year", new Criteria[]{new Term(name)}, true);
	}
	
	public Criteria month(String name) {
		return new FunctionTerm("month", new Criteria[]{new Term(name)}, true);
	}
	
	public Criteria day(String name) {
		return new FunctionTerm("day", new Criteria[]{new Term(name)}, true);
	}
	
	@Override
	public Criteria substring(String name, int start, int length) {
		return new FunctionTerm("substring", new Criteria[]{
				new Term(name), new StaticValue(start), new StaticValue(length)}, true);
	}

	@Override
	public Criteria function(String functionName, Criteria[] params) {
		return new FunctionTerm(functionName, params, false);
	}

	@Override
	public Criteria function(String functionName, Criteria[] params,
			boolean nativeFunction) {
		return new FunctionTerm(functionName, params, nativeFunction);
	}

}
