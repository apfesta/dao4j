/**
 * 
 */
package com.andrewfesta.dao4j.jpa.criteria;

import com.andrewfesta.dao4j.criteria.Criteria;
import com.andrewfesta.dao4j.criteria.TermDecorator;
import com.andrewfesta.dao4j.jpa.AbstractJpqlWhereClause;

/**
 * @author apfesta
 *
 */
public abstract class AbstractJpqlCriteriaWhereClause extends AbstractJpqlWhereClause {
	
	/**
	 * Constructor for building Criteria based where clauses
	 * @param termDecorator
	 * @param criteria
	 */
	public AbstractJpqlCriteriaWhereClause(TermDecorator termDecorator, Criteria criteria) {
		super(printAsJpql(termDecorator, criteria));
	}
	
	static String printAsJpql(TermDecorator termDecorator, Criteria criteria) {
		StringBuffer queryString = new StringBuffer();
		if (criteria!=null) {
			queryString.append(WHERE_KEYWORD);
			queryString.append(criteria.getExpression(termDecorator));
		}
		return queryString.toString();
	}
	
}