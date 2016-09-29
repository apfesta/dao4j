package com.andrewfesta.dao4j.paging;

import java.util.List;

public class Page<D, Token> {

	private List<D> items;
	private Pagination<Token> nextPage;
	
	public List<D> getItems() {
		return items;
	}
	public void setItems(List<D> items) {
		this.items = items;
	}
	public Pagination<Token> getNextPage() {
		return nextPage;
	}
	public void setNextPage(Pagination<Token> nextPage) {
		this.nextPage = nextPage;
	}
}
