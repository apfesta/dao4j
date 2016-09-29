/**
 * 
 */
package com.andrewfesta.dao4j.dao;

/**
 * @author apfesta
 *
 */
public interface OrderByClause {
	
	/**
	 * Creates the SQL representation of this Where Clause
	 * 
	 * @return
	 */
	public String getSql();

}
