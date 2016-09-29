package com.andrewfesta.dao4j.jpa.criteria;

import com.andrewfesta.dao4j.criteria.TermDecorator;

public class AbstractJpqlTermDecorator implements TermDecorator {
	
	private String alias;

	public AbstractJpqlTermDecorator(String alias) {
		super();
		this.alias = alias;
	}

	public String getExpression(String term) {
		return alias+"."+term;
	}
}
