package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author ivan
 *
 */
@Embeddable
public class MetadataID implements Serializable{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "title", nullable = false)
    private String title;
	
	private ContentID contentId;

	public ContentID getContentId() {
		return contentId;
	}

	public void setContentId(ContentID contentId) {
		this.contentId = contentId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	

}
