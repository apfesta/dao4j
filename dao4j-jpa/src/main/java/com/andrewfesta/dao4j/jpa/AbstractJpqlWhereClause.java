/**
 * 
 */
package com.andrewfesta.dao4j.jpa;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.andrewfesta.dao4j.dao.WhereClause;

/**
 * @author apfesta
 *
 */
public abstract class AbstractJpqlWhereClause implements WhereClause {
	
	protected static final String WHERE_KEYWORD = " where ";
	protected static final String AND_KEYWORD = " and ";
	protected final String jpql;
	protected final Map<String, Object> valueMap;
	
	
	public AbstractJpqlWhereClause(String jpql) {
		super();
		this.valueMap = new LinkedHashMap<String, Object>();
		this.jpql = jpql;
	}

	@Override
	public String getSql() {return jpql;}
	@Override
	public List<Object> getValueList() {return new ArrayList<Object>(valueMap.values());}

	@Override
	public Map<String, Object> getValueMap() {return valueMap;}
}