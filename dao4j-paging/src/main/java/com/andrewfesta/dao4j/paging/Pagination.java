package com.andrewfesta.dao4j.paging;

public class Pagination<Token> {
	
	private Token after;
	private Token before;
	
	public Token getAfter() {
		return after;
	}
	public void setAfter(Token after) {
		this.after = after;
	}
	public Token getBefore() {
		return before;
	}
	public void setBefore(Token before) {
		this.before = before;
	}
	@Override
	public int hashCode() {
		if (after!=null && before!=null) {
			return after.hashCode()+before.hashCode();
		}
		if (after!=null) {
			return after.hashCode();
		}
		if (before!=null) {
			return before.hashCode();
		}
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pagination) {
			if (after!=null && before!=null) {
				return after.equals(((Pagination<?>) obj).after) && before.equals(((Pagination<?>) obj).before);
			}
			if (after!=null) {
				return after.equals(((Pagination<?>) obj).after);
			}
			if (before!=null) {
				before.equals(((Pagination<?>) obj).before);
			}
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		if (after!=null && before!=null) {
			return "Between:"+after.toString()+" and:"+before.hashCode();
		}
		if (after!=null) {
			return "After:"+after.toString();
		}
		if (before!=null) {
			return "Before:"+after.toString();
		}
		return super.toString();
	}
		
}