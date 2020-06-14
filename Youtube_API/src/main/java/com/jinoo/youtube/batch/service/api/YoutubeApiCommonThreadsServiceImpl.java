package com.jinoo.youtube.batch.service.api;

import java.net.URI;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinoo.youtube.batch.common.CommonConstant;
import com.jinoo.youtube.batch.common.UserRestTemplate;
import com.jinoo.youtube.batch.common.annotation.YoutubeApiCallCount;
import com.jinoo.youtube.batch.common.util.ConvertUtil;
import com.jinoo.youtube.batch.vo.api.CommonThreadsListSnippetResponseVO;

import ch.qos.logback.classic.Logger;

@Service
public class YoutubeApiCommonThreadsServiceImpl implements YoutubeApiCommonThreadsService{

	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	@Override
	@YoutubeApiCallCount
	public ResponseEntity<CommonThreadsListSnippetResponseVO> list(Map<String, String> param) {
		logger.info("YoutubeApiCommonThreadsServiceImpl.run()");
		
		URI uri = UriComponentsBuilder.fromHttpUrl(CommonConstant.YOUTUBE_API_URI_COMMENT_THREADS) 
				.queryParams(ConvertUtil.convert(new ObjectMapper(), param))
				.build()
				.encode().toUri();
		
		UserRestTemplate userRestTemplate = new UserRestTemplate();
		
		HttpHeaders headers  = new HttpHeaders(); 
        HttpEntity<String> entity = new HttpEntity<>(headers); 
		
        ResponseEntity<CommonThreadsListSnippetResponseVO> responseEntity = null;
        
        logger.info("uri : " + uri);
        try {
        	responseEntity = userRestTemplate.exchange(uri, HttpMethod.GET, entity, CommonThreadsListSnippetResponseVO.class);
        	
        	int httpStatus = responseEntity.getStatusCodeValue();
        	
        	logger.info("httpStatus : [" + httpStatus + "]" + responseEntity.getStatusCode());
        		
        } catch (HttpClientErrorException hcee) {
        
        	int httpStatus = hcee.getRawStatusCode();
        	
        	logger.info("YoutubeApiCommonThreadsServiceImpl.getStatusCode : " + hcee.getStatusCode());
        	logger.info("YoutubeApiCommonThreadsServiceImpl.getRawStatusCode : " + hcee.getRawStatusCode());
        	
        	if(httpStatus == HttpStatus.NOT_FOUND.value()) {
        	
        	}else if(httpStatus == HttpStatus.BAD_REQUEST.value()) {
        		throw hcee;
            }else if(httpStatus == HttpStatus.FORBIDDEN.value()) {
            
            }else {
            	throw hcee;
            }
        }
        
        return responseEntity;
	}
}
