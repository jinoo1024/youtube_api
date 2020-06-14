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
public class CommonThreadsListSnippetTopLevelCommentSnippetVO {
	
	@JsonProperty("videoId")
	String videoId;	
	
	@JsonProperty("textDisplay")
	String textDisplay;
	
	@JsonProperty("textOriginal")
	String textOriginal;
	
	@JsonProperty("authorDisplayName")
	String authorDisplayName;

	@JsonProperty("authorProfileImageUrl")
	String authorProfileImageUrl;

	@JsonProperty("authorChannelUrl")
	String authorChannelUrl;
	
	@JsonProperty("authorChannelId")
	CommonThreadsListSnippetAuthorChannelIdVO authorChannelId;
	
	@JsonProperty("canRate")
	boolean canRate;
	
	@JsonProperty("viewerRating")
	String viewerRating;
	
	@JsonProperty("likeCount")
	int likeCount;
	
	@JsonProperty("publishedAt")
	String publishedAt;
	
	@JsonProperty("updatedAt")
	String updatedAt;
}

	