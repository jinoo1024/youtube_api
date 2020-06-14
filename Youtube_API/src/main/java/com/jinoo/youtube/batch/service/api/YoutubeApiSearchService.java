package com.jinoo.youtube.batch.service.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.jinoo.youtube.batch.vo.api.SearchListResponseVO;

public interface YoutubeApiSearchService {

	ResponseEntity<SearchListResponseVO> list(Map<String, String> param);
	
}
