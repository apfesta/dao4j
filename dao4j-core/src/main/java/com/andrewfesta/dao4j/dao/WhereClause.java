/**
 * 
 */
package com.andrewfesta.dao4j.dao;

import java.util.List;
import java.util.Map;

/**
 * @author apfesta
 *
 */
public interface WhereClause {
	
	/**
	 * Creates the SQL representation of this Where Clause
	 * 
	 * @return
	 */
	public String getSql();
	
	/**
	 * Gets the values that will be used for the prepared statement.
	 * 
	 * @deprecated
	 * @return
	 */
	public List<Object> getValueList();

	/**
	 * Gets the named parameters that will be used for the prepared statement.
	 * 
	 * @return
	 */
	public Map<String, Object> getValueMap();
}
