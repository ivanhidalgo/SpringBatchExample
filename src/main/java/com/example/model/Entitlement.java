package com.example.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Entitlement")
public class Entitlement implements java.io.Serializable {

	/**
	 *  serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private EntitlementID entitlementId;
	
	@MapsId("contentId")
    @JoinColumns({
        @JoinColumn(name="contentId", referencedColumnName="contentId")
    })
    @ManyToOne
	private MovieContent movieContent;
	
	@Column(name = "authLevel", nullable = false)
	private String authLevel;

	/**
	 * @return the entitlementId
	 */
	public EntitlementID getEntitlementId() {
		return entitlementId;
	}

	/**
	 * @param entitlementId the entitlementId to set
	 */
	public void setEntitlementId(EntitlementID entitlementId) {
		this.entitlementId = entitlementId;
	}

	/**
	 * @return the authLevel
	 */
	public String getAuthLevel() {
		return authLevel;
	}

	/**
	 * @param authLevel the authLevel to set
	 */
	public void setAuthLevel(String authLevel) {
		this.authLevel = authLevel;
	}

	/**
	 * @return the movieContent
	 */
	public MovieContent getMovieContent() {
		return movieContent;
	}

	/**
	 * @param movieContent the movieContent to set
	 */
	public void setMovieContent(MovieContent movieContent) {
		this.movieContent = movieContent;
	}
	
	

}
