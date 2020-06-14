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
public class CommonThreadsListSnippetItemVO {
	
	@JsonProperty("kind")
	String kind;	
	
	@JsonProperty("etag")
	String etag;
	
	@JsonProperty("id")
	String id;
	
	@JsonProperty("snippet")
	CommonThreadsListSnippetSnippetVO snippet;
}

	