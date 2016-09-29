/**
 * 
 */
package com.andrewfesta.dao4j.jpa;

import com.andrewfesta.dao4j.dao.OrderByClause;


/**
 * @author apfesta
 *
 */
public abstract class AbstractJpqlOrderByClause implements OrderByClause {
	protected static final String ORDER_BY_KEYWORD = " order by ";
	protected String[] orderOptions;
	protected String alias;

	public AbstractJpqlOrderByClause(String alias, String[] orderOptions) {
		super();
		this.orderOptions = orderOptions;
		this.alias = alias;
	}

	@Override
	public String getSql() {
		StringBuffer queryString = new StringBuffer();
		
		if (orderOptions!=null && orderOptions.length>0) {
			queryString.append(ORDER_BY_KEYWORD);
			for (int i=0; i<orderOptions.length; i++) {
				queryString.append(alias).append(".");
				queryString.append(orderOptions[i]);
				if (i+1<orderOptions.length) {
					queryString.append(", ");
				}
			}
		}
		
		return queryString.toString();
	}
	
}