package com.example.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.OneToOne;
import javax.persistence.FetchType;

@Entity
@Table(name = "Content")
public class MovieContent implements java.io.Serializable {
	
	@EmbeddedId
	private ContentID contentId;
	
	@OneToMany( mappedBy = "movieContent", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<MovieLink> movieLink = new HashSet<MovieLink>(
			0);
	
	@OneToMany( mappedBy = "movieContent", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Entitlement> entitlement = new HashSet<Entitlement>(
			0);
     
    @Column(name = "contry", nullable = false)
    private String contry;
     
    @Column(name = "dateCreate", nullable = false)
    private String dateCreate;
     
    @Column(name = "dateModified", nullable = false)
    private String dateModified;
    
    @Column(name = "episodesCount", nullable = false)
    private String episodesCount;
    
    @Column(name = "language", nullable = false)
    private String language;
    
    @Column(name = "number", nullable = false)
    private String number;
    
    @Column(name = "season", nullable = false)
    private String season;
 
    @Column(name = "contentSignal", nullable = false)
    private String contentSignal;
    
    @Column(name = "type", nullable = false)
    private String contetType;
    
    @OneToOne(mappedBy = "movieContent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Metadata metadata;

    @OneToOne(mappedBy = "movieContent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MovieVideoInfo movieVideoInfo;

	public MovieVideoInfo getMovieVideoInfo() {
		return this.movieVideoInfo;
	}

	public void setMovieVideoInfo(MovieVideoInfo movieVideoInfo) {
		this.movieVideoInfo = movieVideoInfo;
	}

    
	public Set<MovieLink> getMovieLink() {
	    return movieLink;
	}
	
	public void setMovieLink(Set<MovieLink> movieLink) {
	    this.movieLink = movieLink;
	}
    
	/**
	 * @return the contry
	 */
	public String getContry() {
		return contry;
	}

	/**
	 * @param contry the contry to set
	 */
	public void setContry(String contry) {
		this.contry = contry;
	}

	/**
	 * @return the dateCreate
	 */
	public String getDateCreate() {
		return dateCreate;
	}

	/**
	 * @param dateCreate the dateCreate to set
	 */
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}

	/**
	 * @return the dateModified
	 */
	public String getDateModified() {
		return dateModified;
	}

	/**
	 * @param dateModified the dateModified to set
	 */
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	/**
	 * @return the episodesCount
	 */
	public String getEpisodesCount() {
		return episodesCount;
	}

	/**
	 * @param episodesCount the episodesCount to set
	 */
	public void setEpisodesCount(String episodesCount) {
		this.episodesCount = episodesCount;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the season
	 */
	public String getSeason() {
		return season;
	}

	/**
	 * @param season the season to set
	 */
	public void setSeason(String season) {
		this.season = season;
	}

	/**
	 * @return the contentSignal
	 */
	public String getContentSignal() {
		return contentSignal;
	}

	/**
	 * @param contentSignal the contentSignal to set
	 */
	public void setContentSignal(String contentSignal) {
		this.contentSignal = contentSignal;
	}

	/**
	 * @return the contetType
	 */
	public String getContetType() {
		return contetType;
	}

	/**
	 * @param contetType the contetType to set
	 */
	public void setContetType(String contetType) {
		this.contetType = contetType;
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

	/**
	 * @return the metadata
	 */
	public Metadata getMetadata() {
		return metadata;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	/**
	 * @return the entitlement
	 */
	public Set<Entitlement> getEntitlement() {
		return entitlement;
	}

	/**
	 * @param entitlement the entitlement to set
	 */
	public void setEntitlement(Set<Entitlement> entitlement) {
		this.entitlement = entitlement;
	}
	
}
