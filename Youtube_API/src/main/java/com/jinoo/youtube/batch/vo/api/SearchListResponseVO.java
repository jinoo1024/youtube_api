package com.jinoo.youtube.batch.vo.api;

import java.util.List;

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
public class SearchListResponseVO {
	
	@JsonProperty("kind")
	String kind;
	
	@JsonProperty("etag")
	String etag;
	
	@JsonProperty("nextPageToken")
	String nextPageToken;
	
	@JsonProperty("prevPageToken")
	String prevPageToken;
	
	@JsonProperty("pageInfo")
	PageInfoVO pageInfo;
	
	@JsonProperty("items")
	List<SearchListItemVO> items;
}





