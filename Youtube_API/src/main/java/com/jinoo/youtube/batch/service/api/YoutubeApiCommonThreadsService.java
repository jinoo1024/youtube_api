package com.jinoo.youtube.batch.service.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.jinoo.youtube.batch.vo.api.CommonThreadsListSnippetResponseVO;

public interface YoutubeApiCommonThreadsService {

	ResponseEntity<CommonThreadsListSnippetResponseVO> list(Map<String, String> param);
	
}
