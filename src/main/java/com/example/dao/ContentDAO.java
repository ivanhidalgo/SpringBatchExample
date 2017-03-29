package com.example.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SessionFactory;

import com.example.model.MovieContent;

class ContentDAO implements IContentDAO {

	private Logger logger = LoggerFactory.getLogger(ContentDAO.class);
	
	private SessionFactory sessionFactory;
	
    /**
     * Gets Hibernate Session Factory
     *
     * @return SessionFactory - Hibernate Session Factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /**
     * Sets Hibernate Session Factory
     *
     * @param SessionFactory - Hibernate Session Factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	public synchronized void saveContent(MovieContent content) {
		
		logger.info("Content Identifier: " + content.getContentId().getContentId());
		getSessionFactory().getCurrentSession().merge(content);
		getSessionFactory().getCurrentSession().evict(content);

	}

}
