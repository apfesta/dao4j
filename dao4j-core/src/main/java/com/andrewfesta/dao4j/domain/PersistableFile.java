/**
 * 
 */
package com.andrewfesta.dao4j.domain;

import java.io.InputStream;

/**
 * @author Andy
 *
 */
public interface PersistableFile {
	
	/**
	 * Must return a new inputstream each time.
	 * 
	 * @return
	 */
	public InputStream getInputStream();
		
	public String getFilename();
	
	public String getContentType();


}
