package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LinkID implements Serializable{

	@Column(name = "Type", nullable = false)
    private String movieType;
	
	private ContentID contentId;

	/**
	 * @return the movieType
	 */
	public String getMovieType() {
		return movieType;
	}

	/**
	 * @param movieType the movieType to set
	 */
	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	/**
	 * @return the contentId
	 */
	public ContentID getContentId() {
		return contentId;
	}

	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(ContentID contentId) {
		this.contentId = contentId;
	}
	
	
	
	
}
