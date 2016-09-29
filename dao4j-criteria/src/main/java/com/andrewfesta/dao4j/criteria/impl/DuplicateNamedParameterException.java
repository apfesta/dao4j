package com.andrewfesta.dao4j.criteria.impl;

public class DuplicateNamedParameterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8785729031181053531L;
	
	private String name;

	public DuplicateNamedParameterException(String name) {
		super("There is a duplicate parameter in this criteria expression: "+name);
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

}
