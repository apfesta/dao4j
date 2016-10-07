package com.andrewfesta.dao4j.criteria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.andrewfesta.dao4j.criteria.impl.DefaultCriteriaFactory;
import com.andrewfesta.dao4j.criteria.impl.DuplicateNamedParameterException;
import com.andrewfesta.dao4j.criteria.impl.StaticValue;
import com.andrewfesta.dao4j.criteria.impl.Term;


public class CriteriaFactoryTest {
	
	@Test
	public void testExpression() {
		CriteriaFactory cf = new DefaultCriteriaFactory();
		TermDecorator td = new MockTermDecorator();
		
		Criteria c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11;
		
		//Simple Equals
		c1 = cf.equals("a", "b");
		assertEquals("a = :a", c1.getExpression(td));
		assertEquals("b", c1.getNamedValues().get("a"));
		c2 = cf.equals("c", "d", true);
		assertEquals("(c = :c)", c2.getExpression(td));
		assertEquals("d", c2.getNamedValues().get("c"));
		
		//And
		c3 = cf.and(c1, c2);
		assertEquals("a = :a AND (c = :c)", c3.getExpression(td));
		assertEquals("b", c3.getNamedValues().get("a"));
		assertEquals("d", c3.getNamedValues().get("c"));
		c4 = cf.and(c1, c2, true);
		assertEquals("(a = :a AND (c = :c))", c4.getExpression(td));
		assertEquals("b", c3.getNamedValues().get("a"));
		assertEquals("d", c3.getNamedValues().get("c"));
		
		//In
		c5 = cf.in("id", new Integer[] {1,4,6,7});
		assertEquals("id in (:id)", c5.getExpression(td));
		assertEquals(1, ((Integer[])c5.getNamedValues().get("id"))[0].intValue());
		assertEquals(4, ((Integer[])c5.getNamedValues().get("id"))[1].intValue());
		assertEquals(6, ((Integer[])c5.getNamedValues().get("id"))[2].intValue());
		assertEquals(7, ((Integer[])c5.getNamedValues().get("id"))[3].intValue());
		c5 = cf.in("id", new Integer[] {9});
		assertEquals("id = :id", c5.getExpression(td));
		assertEquals(9, ((Integer)c5.getNamedValues().get("id")).intValue());
		
		//Not
		c6 = cf.not(c1);
		assertEquals("a <> :a", c6.getExpression(td));
		assertEquals("b", c6.getNamedValues().get("a"));
		c6 = cf.not(c4);
		assertEquals("NOT (a = :a AND (c = :c))", c6.getExpression(td));
		assertEquals("b", c6.getNamedValues().get("a"));
		assertEquals("d", c6.getNamedValues().get("c"));
		
		//IsNull
		c7 = cf.isNull("a");
		assertEquals("a IS NULL", c7.getExpression(td));
		c7 = cf.not(cf.isNull("a"));
		assertEquals("a IS NOT NULL", c7.getExpression(td));
		c7 = cf.isNotNull("a");
		assertEquals("a IS NOT NULL", c7.getExpression(td));
		
		//Functions
		c8 = cf.month("a");
		assertEquals("month(a)", c8.getExpression(td));
		c8 = cf.substring("a",2,1);
		assertEquals("substring(a,2,1)", c8.getExpression(td));
		c9 = cf.function("replace", new Criteria[]{new Term("a"),new StaticValue(" "),new StaticValue("")});
		assertEquals("FUNCTION('replace',a,' ','')", c9.getExpression(td));
		c9 = cf.function("substring", new Criteria[]{new Term("a"),new StaticValue(2),new StaticValue(1)},true);
		assertEquals("substring(a,2,1)", c9.getExpression(td));
				
		//Duplicate parameter names
		try {
			cf.and(c1, c6);
			fail("DuplicateNamedParameterException expected");
		} catch (DuplicateNamedParameterException e) {}
		
		//Long field names
		c10 = cf.equals("long.field.name", 1);
		assertEquals("long.field.name = :long_field_name", c10.getExpression(td));
		assertEquals(1, c10.getNamedValues().get("long_field_name"));
		
		//Between
		c11 = cf.between("a", 1, 10);
		assertEquals("a BETWEEN :a1 AND :a2", c11.getExpression(td));
		assertEquals(1, c11.getNamedValues().get("a1"));
		assertEquals(10, c11.getNamedValues().get("a2"));
		
	}
	
	@Test
	public void testEquals() {
		CriteriaFactory cf = new DefaultCriteriaFactory();
		
		Criteria c1, c2, c3, c4, c5, c6, c7, c8, c9;
		c1 = cf.equals("a", "b");
		assertEquals(c1, cf.equals("a", "b"));
		
		c2 = cf.equals("c", "d", true);
		assertEquals(c2, cf.equals("c", "d", true));
		
		c3 = cf.and(c1, c2);
		assertEquals(c3, cf.and(c1, c2));
		
		c4 = cf.and(c1, c2, true);
		assertEquals(c4, cf.and(c1, c2, true));
		
		c5 = cf.in("id", new Integer[] {9});
		assertEquals(c5, cf.in("id", new Integer[] {9}));
		
		c6 = cf.not(c1);
		assertEquals(c6, cf.not(c1));
		c6 = cf.not(c4);
		assertEquals(c6, cf.not(c4));
		
		c7 = cf.isNull("a");
		assertEquals(c7, cf.isNull("a"));
		c7 = cf.not(cf.isNull("a"));
		assertEquals(c7, cf.not(cf.isNull("a")));
		c7 = cf.isNotNull("a");
		assertEquals(c7, cf.isNotNull("a"));
		
		c8 = cf.month("a");
		assertEquals(c8, cf.month("a"));
		c8 = cf.substring("a",2,1);
		assertEquals(c8, cf.substring("a",2,1));
		
		c9 = cf.function("replace", new Criteria[]{new Term("a"),new StaticValue(" "),new StaticValue("")});
		assertEquals(c9, cf.function("replace", new Criteria[]{new Term("a"),new StaticValue(" "),new StaticValue("")}));
		c9 = cf.function("substring", new Criteria[]{new Term("a"),new StaticValue(2),new StaticValue(1)},true);
		assertEquals(c9, cf.function("substring", new Criteria[]{new Term("a"),new StaticValue(2),new StaticValue(1)},true));
		
	}
	
	class MockTermDecorator implements TermDecorator {

		public String getExpression(String term) {
			return term;
		}
		
	}

}
