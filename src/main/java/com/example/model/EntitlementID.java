package com.example.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author ivan
 *
 */
@Embeddable
public class EntitlementID implements Serializable{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	private String urn;
	
	private ContentID contentId;

	public ContentID getContentId() {
		return contentId;
	}

	public void setContentId(ContentID contentId) {
		this.contentId = contentId;
	}

	/**
	 * @return the urn
	 */
	public String getUrn() {
		return urn;
	}

	/**
	 * @param urn the urn to set
	 */
	public void setUrn(String urn) {
		this.urn = urn;
	}

	
	

}
