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
public class SnippetVO {
	
	@JsonProperty("videoId")
	String videoId;
	
	@JsonProperty("textDisplay")
	String textDisplay;
	
	@JsonProperty("textOriginal")
	String textOriginal;
	
	@JsonProperty("parentId")
	String parentId;
	
	@JsonProperty("authorDisplayName")
	String authorDisplayName;
	
	@JsonProperty("authorProfileImageUrl")
	String authorProfileImageUrl;
	
	@JsonProperty("authorChannelUrl")
	String authorChannelUrl;
	
	@JsonProperty("authorChannelId")
	AuthorChannelIdVO authorChannelId;
	
	@JsonProperty("canRate")
	String canRate;
	
	@JsonProperty("viewerRating")
	String viewerRating;
	
	@JsonProperty("likeCount")
	int likeCount;
	
	@JsonProperty("ppublishedAt")
	String ppublishedAt;
	
	@JsonProperty("updatedAt")
	String updatedAt;		
}





