/**
 * 
 */
package com.example.services;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.dao.IContentDAO;
import com.example.model.MovieContent;

/**
 * @author Administrator
 *
 */
public class ContentService implements IContentService {

	private Logger logger = LoggerFactory.getLogger(ContentService.class);
	private IContentDAO contentDAO;
	
	/* (non-Javadoc)
	 * @see com.example.services.IContentService#addContent(com.example.model.MovieContent)
	 */
	@Transactional(readOnly = false)
	public synchronized void addContent(MovieContent content) {
		
		contentDAO.saveContent(content);
		
	}

	/**
	 * @return the contentDAO
	 */
	public IContentDAO getContentDAO() {
		return contentDAO;
	}

	/**
	 * @param contentDAO the contentDAO to set
	 */
	public void setContentDAO(IContentDAO contentDAO) {
		this.contentDAO = contentDAO;
	}

}
