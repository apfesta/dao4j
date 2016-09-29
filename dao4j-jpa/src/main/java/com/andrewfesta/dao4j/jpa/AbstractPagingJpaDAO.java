package com.andrewfesta.dao4j.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.andrewfesta.dao4j.domain.Entity;
import com.andrewfesta.dao4j.paging.Page;
import com.andrewfesta.dao4j.paging.PageBuilder;
import com.andrewfesta.dao4j.paging.Pagination;

/**
 * Provides methods for returning Pages of data.  This class is expected to be
 * subclassed.  Subclasses are responsible for creating JPQL and passing them 
 * methods the correct values.
 * 
 * @author apfesta
 *
 * @param <D>
 * @param <ID>
 * @param <Token>
 */
public abstract class AbstractPagingJpaDAO<D extends Entity<ID>,ID extends Serializable, Token> 
	extends BaseQueryJpaDAO<D,ID> {
	
	protected Class<Token> tokenClass;
	
	public AbstractPagingJpaDAO(Class<D> domainClass, Class<Token> tokenClass) {
		super(domainClass);
		this.tokenClass = tokenClass;
	}
	
	/**
	 * If there's no pagination provided, where do you want to start?
	 * 
	 * @return
	 */
	protected abstract Pagination<Token> getDefaultToken();
	
	/**
	 * Given this domain object, what is the value the we're paging on?
	 * 
	 * @param item
	 * @return
	 */
	protected abstract <T extends D> Token getToken(T item);
	
	protected abstract <T extends D> void initList(List<T> items);
	
	/**
	 * Queries for D (Domain Object)
	 * @param queryString Query that already does the pagination
	 * @param tokenParamName Name of the :param that you want to the pagination token passed into
	 * @param valueMap All of the other parameters except the token param.  That will be determined
	 * @param maxElements Page size
	 * @param paging Previous Pagination object
	 * @return
	 */
	protected Page<D,Token> query(
			final String queryString, final String tokenParamName,
			final Map<String, Object> valueMap, final int maxElements, 
			boolean descending, Pagination<Token> paging) {
		return query(queryString, tokenParamName, valueMap, maxElements, 
				descending, getDomainClass(), paging);
	}
	
	/**
	 * Queries for a subclass of D (Domain Object)
	 * @param queryString Query that already does the pagination
	 * @param tokenParamName Name of the :param that you want to the pagination token passed into
	 * @param valueMap All of the other parameters except the token param.  That will be determined
	 * @param maxElements Page size
	 * @param descending true if the results are expected to be descending
	 * @param clazz Allows for subclasses of Domain Object
	 * @param paging Previous Pagination object
	 * @return
	 */
	protected <T extends D> Page<T,Token> query(
			final String queryString, final String tokenParamName,
			final Map<String, Object> valueMap, final int maxElements, 
			boolean descending,
			Class<T> clazz, Pagination<Token> paging) {
		
		return this.query(queryString, tokenParamName, valueMap, maxElements, 
				descending, clazz, paging, 
				new PageBuilder.InitItemsCallback<T>() {
					@Override
					public void initList(List<T> items) {
						AbstractPagingJpaDAO.this.initList(items);
					}
				},
				new PageBuilder.GetTokenCallback<T,Token>(){
					@Override
					public Token getToken(T item) {
						return AbstractPagingJpaDAO.this.getToken(item);
					}});
	}
	
	/**
	 * Queries for some generic type.  This method is used when we're doing
	 * aggregations and we expect an Object[] back and we have to customize
	 * the result.
	 * 
	 * @param queryString Query that already does the pagination
	 * @param tokenParamName Name of the :param that you want to the pagination token passed into
	 * @param valueMap All of the other parameters except the token param.  That will be determined
	 * @param maxElements Page size
	 * @param clazz Allows for subclasses of Domain Object
	 * @param paging Previous Pagination object
	 * @param tokenCallback
	 * @param descending true if the query string is sorted descending
	 * @return
	 */
	protected <T> Page<T,Token> query(
			final String queryString, final String tokenParamName,
			final Map<String, Object> valueMap, final int maxElements, boolean descending, 
			Class<T> clazz, Pagination<Token> paging, 
			PageBuilder.InitItemsCallback<T> itemsCallback,
			PageBuilder.GetTokenCallback<T,Token> tokenCallback) {
		
		if (paging==null) {
			paging = getDefaultToken();
		}
		
		if (Object[].class.isAssignableFrom(clazz)) {
			Query q = getEntityManager().createQuery(queryString);
			q.setParameter(tokenParamName, paging.getAfter());
			if (paging.getAfter()!=null) {
				q.setParameter(tokenParamName, paging.getAfter());
			} else if (paging.getBefore()!=null) {
				q.setParameter(tokenParamName, paging.getBefore());
			} else {
				throw new IllegalArgumentException("Must provide pagination parameters.");
			}
			
			for (Entry<String, Object> entry: valueMap.entrySet()) {
				q.setParameter(entry.getKey(), entry.getValue());
			}
					
			q.setMaxResults(maxElements);
			
			@SuppressWarnings("unchecked")
			Page<T, Token> page = new PageBuilder<T, Token>()
					.setInitItemsCallback(itemsCallback)
					.setItems(q.getResultList())
					.setDescending(descending)
					.setPageSize(maxElements)
					.setPrevPagination(paging)
					.setGetLastTokenCallback(tokenCallback)
					.build();
						
			return page;
		} else {
			TypedQuery<T> q = getEntityManager().createQuery(queryString, clazz);
			q.setParameter(tokenParamName, paging.getAfter());
			if (paging.getAfter()!=null) {
				q.setParameter(tokenParamName, paging.getAfter());
			} else if (paging.getBefore()!=null) {
				q.setParameter(tokenParamName, paging.getBefore());
			} else {
				throw new IllegalArgumentException("Must provide pagination parameters.");
			}
			
			for (Entry<String, Object> entry: valueMap.entrySet()) {
				q.setParameter(entry.getKey(), entry.getValue());
			}
					
			q.setMaxResults(maxElements);
			
			Page<T, Token> page = new PageBuilder<T, Token>()
					.setItems(q.getResultList())
					.setPrevPagination(paging)
					.setGetLastTokenCallback(tokenCallback)
					.build();
						
			return page;
		}		
	}

	

}
