/**
 * 
 */
package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author ivan
 *
 */
@Embeddable
public class VideoInfoID  implements Serializable{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "contentUrl", nullable = false)
    private String contentUrl;
	
	private ContentID contentId;

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public ContentID getContentId() {
		return contentId;
	}

	public void setContentId(ContentID contentId) {
		this.contentId = contentId;
	}
	

}
