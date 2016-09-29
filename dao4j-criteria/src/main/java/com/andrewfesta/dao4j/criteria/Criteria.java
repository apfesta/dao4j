/*
 * Created on Dec 5, 2005
 */
package com.andrewfesta.dao4j.criteria;

import java.util.Map;

/**
 * @author Andy
 */
public interface Criteria {

	public String getExpression(TermDecorator termDecorator);
	
	/**
	 * @deprecated
	 * @return
	 */
	public Object[] getBindValues();
	
	public Map<String,?> getNamedValues();
}
