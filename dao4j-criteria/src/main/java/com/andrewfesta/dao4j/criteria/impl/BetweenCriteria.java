/**
 * 
 */
package com.andrewfesta.dao4j.criteria.impl;

/**
 * @author Andy
 *
 */
public class BetweenCriteria extends TernaryCriteria {
	
	public static final String BETWEEN = "BETWEEN";
	public static final String AND = "AND";


	BetweenCriteria(String name, Object value1, Object value2,
			boolean isolate, String paramName1, String paramName2) {
		super(name, BETWEEN, paramName1, value1, AND, paramName2, value2, isolate);
	}
	
	BetweenCriteria(String name, Object value1, Object value2,
			boolean isolate) {
		super(name, BETWEEN, value1, AND, value2, isolate);
	}

	BetweenCriteria(String name, Object value1, Object value2) {
		super(name, BETWEEN, value1, AND, value2);
	}

}
