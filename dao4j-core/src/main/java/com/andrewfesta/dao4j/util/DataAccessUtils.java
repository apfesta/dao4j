/**
 * 
 */
package com.andrewfesta.dao4j.util;

import java.util.Collection;

/**
 * @author Andy
 *
 */
public class DataAccessUtils {
	
	public static <D> D firstElement(Collection<D> collection) {
		if (collection!=null && collection.size()>0) {
			return collection.iterator().next();
		}
		return null;
	}

}
