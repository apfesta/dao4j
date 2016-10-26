/**
 * 
 */
package com.andrewfesta.dao4j.paging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author apfesta
 *
 */
public class PageBuilderTest {
	
	List<MockElement> elementsAsc = new ArrayList<MockElement>();
	List<MockElement> elementsDesc = new ArrayList<MockElement>();
	
	@Before
	public void setup() {
		for (int i = 1; i<=10; i++) {
			elementsAsc.add(new MockElement(i));
		}
		elementsDesc.addAll(elementsAsc);
		Collections.reverse(elementsDesc);
	}

	@Test
	public void testDescending() {
		Pagination<Integer> firstQuery = new Pagination<Integer>();
		firstQuery.setAfter(0);
		
		List<MockElement> mockResults = elementsDesc.subList(0, 4);
		
		Page<MockElement, Integer> results = new PageBuilder<MockElement, Integer>()
			.setItems(mockResults)
			.setPageSize(4)
			.setDescending()
			.setPrevPagination(firstQuery)
			.setGetLastTokenCallback(new TokenCallback())
			.build(); 
		
		assertEquals(10, results.getItems().get(0).id);
		assertEquals(4, results.getItems().size());
		assertEquals(7, results.getItems().get(3).id);
		assertEquals((Integer)0, results.getCurrentPage().getAfter());
		assertEquals((Integer)7, results.getNextPage().getBefore());
		assertEquals((Integer)10, results.getNextPage().getAfter());
		
		//Last page
		mockResults = elementsDesc.subList(8, 10);
		
		results = new PageBuilder<MockElement, Integer>()
			.setItems(mockResults)
			.setPageSize(4)
			.setDescending()
			.setPrevPagination(firstQuery)
			.setGetLastTokenCallback(new TokenCallback())
			.build(); 
		
		assertEquals(2, results.getItems().get(0).id);
		assertEquals(2, results.getItems().size());
		assertEquals(1, results.getItems().get(1).id);
		assertEquals((Integer)0, results.getCurrentPage().getAfter());
		assertNull(results.getNextPage().getBefore());
		assertEquals((Integer)2, results.getNextPage().getAfter());
	}
	
	@Test
	public void testAscending() {
		Pagination<Integer> firstQuery = new Pagination<Integer>();
		firstQuery.setAfter(0);
		
		List<MockElement> mockResults = elementsAsc.subList(0, 4);
		
		Page<MockElement, Integer> results = new PageBuilder<MockElement, Integer>()
			.setItems(mockResults)
			.setPageSize(4)
			.setAscending()
			.setPrevPagination(firstQuery)
			.setGetLastTokenCallback(new TokenCallback())
			.build(); 
		
		assertEquals(1, results.getItems().get(0).id);
		assertEquals(4, results.getItems().size());
		assertEquals(4, results.getItems().get(3).id);
		assertEquals((Integer)0, results.getCurrentPage().getAfter());
		assertEquals((Integer)1, results.getNextPage().getBefore());
		assertEquals((Integer)4, results.getNextPage().getAfter());
		
		//Last page
		mockResults = elementsAsc.subList(8, 10);
		
		results = new PageBuilder<MockElement, Integer>()
			.setItems(mockResults)
			.setPageSize(4)
			.setAscending()
			.setPrevPagination(firstQuery)
			.setGetLastTokenCallback(new TokenCallback())
			.build(); 
		
		assertEquals(9, results.getItems().get(0).id);
		assertEquals(2, results.getItems().size());
		assertEquals(10, results.getItems().get(1).id);
		assertEquals((Integer)0, results.getCurrentPage().getAfter());
		assertEquals((Integer)9, results.getNextPage().getBefore());
		assertEquals((Integer)10, results.getNextPage().getAfter());
	}
	
	@Test
	public void testEmpty() {
		Pagination<Integer> firstQuery = new Pagination<Integer>();
		firstQuery.setAfter(0);
				
		List<MockElement> mockResults = Collections.emptyList();
		
		Page<MockElement, Integer> results = new PageBuilder<MockElement, Integer>()
			.setItems(mockResults)
			.setAscending()
			.setPrevPagination(firstQuery)
			.setGetLastTokenCallback(new TokenCallback())
			.build(); 
		
		assertTrue(results.getItems().isEmpty());
		assertEquals((Integer)0, results.getNextPage().getAfter());
	}
	
	
	
	static class MockElement {
		int id;

		public MockElement(int id) {
			super();
			this.id = id;
		}
		
	}
	
	static class TokenCallback implements PageBuilder.GetTokenCallback<MockElement, Integer> {

		@Override
		public Integer getToken(MockElement item) {
			return item.id;
		}
		
	}
	
}
