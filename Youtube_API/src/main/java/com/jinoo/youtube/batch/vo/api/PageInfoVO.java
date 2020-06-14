package com.jinoo.youtube.batch.vo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageInfoVO {
	
	@JsonProperty("totalResults")
	int totalResults;
	
	@JsonProperty("resultsPerPage")
	int resultsPerPage;
}





