package com.jinoo.youtube.batch.vo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchListSnippetVO {
	
	@JsonProperty("publishedAt")
	String publishedAt;	
	
	@JsonProperty("channelId")
	String channelId;
	 
	@JsonProperty("title")
	String title;
	
	@JsonProperty("description")
	String description;

	@JsonProperty("thumbnails")
	SearchListThumbnailsVO thumbnails;
	
	@JsonProperty("channelTitle")
	String channelTitle;

}

	