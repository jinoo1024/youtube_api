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
public class CommonThreadsListSnippetSnippetVO {
	
	@JsonProperty("videoId")
	String videoId;	
	
	@JsonProperty("topLevelComment")
	CommonThreadsListSnippetTopLevelCommentVO topLevelComment;
	
	@JsonProperty("canReply")
	boolean canReply;
	
	@JsonProperty("totalReplyCount")
	int totalReplyCount;
	
	@JsonProperty("isPublic")
	boolean isPublic;
}

	