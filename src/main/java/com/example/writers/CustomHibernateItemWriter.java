package com.example.writers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.example.model.MovieContent;
import com.example.services.IContentService;

public class CustomHibernateItemWriter implements ItemWriter<MovieContent>{

	private Logger logger = LoggerFactory.getLogger(CustomHibernateItemWriter.class);
	private IContentService contentService;
	
	public void write(List<? extends MovieContent> contents) throws Exception {
		
		logger.info("Writing Movies Content on the Mysql data base.");
		
		for(MovieContent content : contents) {
			contentService.addContent(content);
        }
		
		logger.info("Writing finished!!");
	}

	/**
	 * @return the contentService
	 */
	public IContentService getContentService() {
		return contentService;
	}

	/**
	 * @param contentService the contentService to set
	 */
	public void setContentService(IContentService contentService) {
		this.contentService = contentService;
	}
}
