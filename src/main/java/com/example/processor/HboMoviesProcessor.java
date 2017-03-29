package com.example.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.example.model.ADI;
import com.example.model.ContentID;
import com.example.model.Entitlement;
import com.example.model.EntitlementID;
import com.example.model.LinkID;
import com.example.model.Metadata;
import com.example.model.MetadataID;
import com.example.model.MovieContent;
import com.example.model.MovieLink;
import com.example.model.MovieVideoInfo;
import com.example.model.VideoInfoID;

public class HboMoviesProcessor implements ItemProcessor<ADI, MovieContent> {

	private Logger logger = LoggerFactory.getLogger(HboMoviesProcessor.class);
	
	public MovieContent process(ADI adi) throws Exception {
		
		logger.info("Transforming ADI xml document to Content objects.");
		
		ContentID contentId = new ContentID();
		contentId.setContentId(adi.getAsset().getMetadata().getAMS().getAssetID());
		
		MovieContent content = new MovieContent();
		content.setContentId(contentId);
		content.setContry("Colombia");
		content.setDateCreate("16032017");
		content.setDateModified("122587");
		content.setEpisodesCount("3");
		content.setLanguage("english");
		content.setNumber("second");
		content.setSeason("the first season");
		content.setContentSignal("signal");
		content.setContetType("Type");

		LinkID linkId = new LinkID(); 
		linkId.setContentId(contentId);
		linkId.setMovieType("Type1");
		
		MovieLink movieLink = new MovieLink();
		movieLink.setMovieResolution("Resolution");
		movieLink.setLinkId(linkId);
		movieLink.setMovieUrl("Url");	
		movieLink.setMovieContent(content);
		content.getMovieLink().add(movieLink);
		
		LinkID linkId2 = new LinkID(); 
		linkId2.setContentId(contentId);
		linkId2.setMovieType("Type2");
		
		MovieLink movieLink2 = new MovieLink();		
		movieLink2.setMovieResolution("Resolution");
		movieLink2.setLinkId(linkId2);
		movieLink2.setMovieUrl("Url");
		movieLink2.setMovieContent(content);
		content.getMovieLink().add(movieLink2);
		
		VideoInfoID videoInfoId = new VideoInfoID();
		videoInfoId.setContentId(contentId);
		videoInfoId.setContentUrl("001");

		MovieVideoInfo movieVideoInfo = new MovieVideoInfo();
		movieVideoInfo.setVideoInfoID(videoInfoId);
		movieVideoInfo.setLicenceExperyDateTime("23-02-2017");
		movieVideoInfo.setLicenceParamesEscapedForXml("Xml Licence");
		movieVideoInfo.setPet("pet");
		movieVideoInfo.setPlayBackId("Play Id");
		movieVideoInfo.setPlayBackType("Play Back Type");
		movieVideoInfo.setRightsObject("Copy");
		movieVideoInfo.setMovieContent(content);
		content.setMovieVideoInfo(movieVideoInfo);
		
		MetadataID metadataId = new MetadataID();
		metadataId.setContentId(contentId);
		metadataId.setTitle("Back to the Future");
		
		Metadata metadata = new Metadata();
		metadata.setMetadataId(metadataId);
		metadata.setCast("cast");
		metadata.setDirector("Cristofer Nollan");
		metadata.setDuration("08:03");
		metadata.setGenres("Fiction");
		metadata.setOriginarTitle("Back to the Future");
		metadata.setRating("Higth");
		metadata.setSynopsis("Back to the future (1985)");
		metadata.setYear("1985");
		metadata.setMovieContent(content);
		content.setMetadata(metadata);
		
		EntitlementID entitlementId = new EntitlementID();
		entitlementId.setContentId(contentId);
		entitlementId.setUrn("urn1");
		
		Entitlement entitlement = new Entitlement();
		entitlement.setEntitlementId(entitlementId);
		entitlement.setAuthLevel("strong level");
		entitlement.setMovieContent(content);
		content.getEntitlement().add(entitlement);
		
		
		
		logger.info("Order Transformed");
		
		return content;
	}
}
