package com.example.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Metadata")
public class Metadata implements java.io.Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MetadataID metadataId;
	
	@MapsId("contentId")
    @JoinColumns({
        @JoinColumn(name="contentId", referencedColumnName="contentId")
    })
	@OneToOne
	private MovieContent movieContent;
     
    @Column(name = "cast", nullable = false)
    private String cast;
    
    @Column(name = "director", nullable = false)
    private String director;
    
    @Column(name = "duration", nullable = false)
    private String duration;
    
    @Column(name = "genres", nullable = false)
    private String genres;
    
    @Column(name = "originalTitle", nullable = false)
    private String originarTitle;
    
    @Column(name = "rating", nullable = false)
    private String rating;
    
    @Column(name = "synopsis", nullable = false)
    private String synopsis;
    
    @Column(name = "year", nullable = false)
    private String year;

	/**
	 * @return the metadataId
	 */
	public MetadataID getMetadataId() {
		return metadataId;
	}

	/**
	 * @param metadataId the metadataId to set
	 */
	public void setMetadataId(MetadataID metadataId) {
		this.metadataId = metadataId;
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

	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the genres
	 */
	public String getGenres() {
		return genres;
	}

	/**
	 * @param genres the genres to set
	 */
	public void setGenres(String genres) {
		this.genres = genres;
	}

	/**
	 * @return the originarTitle
	 */
	public String getOriginarTitle() {
		return originarTitle;
	}

	/**
	 * @param originarTitle the originarTitle to set
	 */
	public void setOriginarTitle(String originarTitle) {
		this.originarTitle = originarTitle;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * @param synopsis the synopsis to set
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the cast
	 */
	public String getCast() {
		return cast;
	}

	/**
	 * @param cast the cast to set
	 */
	public void setCast(String cast) {
		this.cast = cast;
	}
	
}
