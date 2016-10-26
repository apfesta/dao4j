package com.andrewfesta.dao4j.paging;

import java.util.List;

public class PageBuilder<D,Token> {
	
	List<D> items;
	Pagination<Token> prevPage;
	InitItemsCallback<D> listCallback;
	GetTokenCallback<D,Token> tokenCallback;
	boolean descending = false;
	Integer pageSize;
	
	public PageBuilder<D,Token> setItems(List<D> items) {
		this.items = items;
		return this;
	}
	
	public PageBuilder<D,Token> setPrevPagination(Pagination<Token> prevPage) {
		this.prevPage = prevPage;
		return this;
	}
	
	public PageBuilder<D,Token> setInitItemsCallback(InitItemsCallback<D> callback) {
		this.listCallback = callback;
		return this;
	}
	
	public PageBuilder<D,Token> setGetLastTokenCallback(GetTokenCallback<D,Token> callback) {
		this.tokenCallback = callback;
		return this;
	}
	
	public PageBuilder<D,Token> setDescending() {
		return setDescending(true);
	}
	
	public PageBuilder<D,Token> setDescending(boolean descending) {
		this.descending = descending;
		return this;
	}
	
	public PageBuilder<D,Token> setAscending() {
		return setDescending(false);
	}
	
	public PageBuilder<D,Token> setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}
		
	public Page<D,Token> build() {
		Page<D,Token> page = new Page<D,Token>();
		if (listCallback!=null) {
			listCallback.initList(items);
		}
		page.setItems(items);
		page.setCurrentPage(prevPage);
		Pagination<Token> nextPage = new Pagination<Token>();
		if (items.isEmpty()) {
			nextPage.setAfter(prevPage.getAfter());
			nextPage.setBefore(prevPage.getBefore());
		} else {
			if (descending) {
				if (tokenCallback!=null) {
					if (pageSize==null) {
						throw new IllegalArgumentException("Missing pageSize argument");
					}
					nextPage.setAfter(tokenCallback.getToken(items.get(0)));
					if (items.size()>=pageSize) {
						nextPage.setBefore(tokenCallback.getToken(items.get(items.size()-1)));
					} else {
						nextPage.setBefore(null);
					}
				}
			} else {
				if (tokenCallback!=null) {
					nextPage.setBefore(tokenCallback.getToken(items.get(0)));
					nextPage.setAfter(tokenCallback.getToken(items.get(items.size()-1)));
				}
			}
		}
		
		page.setNextPage(nextPage);
		return page;
	}
	
	/**
	 * The token is the entity attribute used to filter results either before
	 * or after the value of that token. 
	 * 
	 * <p>The token should be something that
	 * implements Comparable like a Number, Date or String.
	 * 
	 * @author apfesta
	 *
	 * @param <D>
	 * @param <Token>
	 */
	public interface GetTokenCallback<D,Token> {
		public Token getToken(D item);
	}
	
	/**
	 * Allows a callback to initialize transient elements of list items.
	 * 
	 * @author apfesta
	 *
	 * @param <D>
	 */
	public interface InitItemsCallback<D> {
		public void initList(List<D> items);
	}
	
}
