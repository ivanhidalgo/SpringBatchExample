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
@Table(name = "VideoInfo")
public class MovieVideoInfo implements java.io.Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private VideoInfoID videoInfoID;

	@MapsId("contentId")
    @JoinColumns({
        @JoinColumn(name="contentId", referencedColumnName="contentId")
    })
    @OneToOne
    private MovieContent movieContent;

    @Column(name = "licenceExperyDateTime", nullable = false)
    private String licenceExperyDateTime;

    @Column(name = "licenceQParamesEscapedForXml", nullable = false)
    private String LicenceParamesEscapedForXml;

    @Column(name = "pet", nullable = false)
    private String pet;

    @Column(name = "playbackId", nullable = false)
    private String playBackId;

    @Column(name = "playbackType", nullable = false)
    private String playBackType;

    @Column(name = "rightsObject", nullable = false)
    private String rightsObject;

	/**
	 * @return the licenceExperyDateTime
	 */
	public String getLicenceExperyDateTime() {
		return licenceExperyDateTime;
	}

	/**
	 * @param licenceExperyDateTime the licenceExperyDateTime to set
	 */
	public void setLicenceExperyDateTime(String licenceExperyDateTime) {
		this.licenceExperyDateTime = licenceExperyDateTime;
	}

	/**
	 * @return the licenceParamesEscapedForXml
	 */
	public String getLicenceParamesEscapedForXml() {
		return LicenceParamesEscapedForXml;
	}

	/**
	 * @param licenceParamesEscapedForXml the licenceParamesEscapedForXml to set
	 */
	public void setLicenceParamesEscapedForXml(String licenceParamesEscapedForXml) {
		LicenceParamesEscapedForXml = licenceParamesEscapedForXml;
	}

	/**
	 * @return the pet
	 */
	public String getPet() {
		return pet;
	}

	/**
	 * @param pet the pet to set
	 */
	public void setPet(String pet) {
		this.pet = pet;
	}

	/**
	 * @return the playBackId
	 */
	public String getPlayBackId() {
		return playBackId;
	}

	/**
	 * @param playBackId the playBackId to set
	 */
	public void setPlayBackId(String playBackId) {
		this.playBackId = playBackId;
	}

	/**
	 * @return the playBackType
	 */
	public String getPlayBackType() {
		return playBackType;
	}

	/**
	 * @param playBackType the playBackType to set
	 */
	public void setPlayBackType(String playBackType) {
		this.playBackType = playBackType;
	}

	/**
	 * @return the rightsObject
	 */
	public String getRightsObject() {

		return rightsObject;

	}

	/**
	 * @param rightsObject the rightsObject to set
	 */
	public void setRightsObject(String rightsObject) {
		this.rightsObject = rightsObject;
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
	 * @return the videoInfoID
	 */
	public VideoInfoID getVideoInfoID() {
		return videoInfoID;
	}

	/**
	 * @param videoInfoID the videoInfoID to set
	 */
	public void setVideoInfoID(VideoInfoID videoInfoID) {
		this.videoInfoID = videoInfoID;
	}
}