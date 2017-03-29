package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Link")
public class MovieLink implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@MapsId("contentId")
    @JoinColumns({
        @JoinColumn(name="contentId", referencedColumnName="contentId")
    })
    @ManyToOne
	private MovieContent movieContent;
     
    @Column(name = "Resolution", nullable = false)
    private String movieResolution;
    
    @EmbeddedId
    private LinkID linkId; 
     
    @Column(name = "Url", nullable = false)
    private String movieUrl;
    
	public MovieContent getMovieContent() {
		return this.movieContent;
	}

	public void setMovieContent(MovieContent movieContent) {
		this.movieContent = movieContent;
	}

	/**
	 * @return the movieResolution
	 */
	public String getMovieResolution() {
		return movieResolution;
	}

	/**
	 * @param movieResolution the movieResolution to set
	 */
	public void setMovieResolution(String movieResolution) {
		this.movieResolution = movieResolution;
	}

	/**
	 * @return the movieUrl
	 */
	public String getMovieUrl() {
		return movieUrl;
	}

	/**
	 * @param movieUrl the movieUrl to set
	 */
	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}

	/**
	 * @return the linkId
	 */
	public LinkID getLinkId() {
		return linkId;
	}

	/**
	 * @param linkId the linkId to set
	 */
	public void setLinkId(LinkID linkId) {
		this.linkId = linkId;
	}
	
	
}
