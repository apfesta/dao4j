/**
 * 
 */
package com.andrewfesta.dao4j.jpa.collections;

import java.util.Iterator;
import java.util.Map;

import com.andrewfesta.dao4j.jpa.AbstractJpqlWhereClause;

/**
 * @author apfesta
 *
 */
public abstract class AbstractJpqlCollectionsWhereClause extends AbstractJpqlWhereClause {
		
	/**
	 * Constructor for building Map based where clauses
	 * @param alias
	 * @param criteria
	 */
	public AbstractJpqlCollectionsWhereClause(String alias, Map<String,Object> criteria){
		super(printAsJpql(alias, criteria));
		populateValueMap(alias, criteria);
	}
	
	static String printAsJpql(String alias, Map<String,Object> criteria) {
		StringBuffer queryString = new StringBuffer();
		
		if (criteria!=null && !criteria.isEmpty())
		{
			queryString.append(WHERE_KEYWORD);
			Iterator<String> it = criteria.keySet().iterator();
			while (it.hasNext())
			{
				String key = it.next();
				
				String paramName = key.replaceAll("\\.", "_");
				queryString.append(alias).append(".");
				queryString.append(key);
				queryString.append("=");
				queryString.append(":");
				queryString.append(paramName);
				
				if (it.hasNext())
				{
					queryString.append(AND_KEYWORD);
				}
			}
		}
		return queryString.toString();
	}
	
	protected void populateValueMap(String alias, Map<String,Object> criteria) {
		if (criteria!=null && !criteria.isEmpty())
		{
			Iterator<String> it = criteria.keySet().iterator();
			while (it.hasNext())
			{
				String key = it.next();
				Object value = criteria.get(key);
				
				String paramName = key.replaceAll("\\.", "_");
				
				valueMap.put(paramName, value);
			}
		}
	}
	
}