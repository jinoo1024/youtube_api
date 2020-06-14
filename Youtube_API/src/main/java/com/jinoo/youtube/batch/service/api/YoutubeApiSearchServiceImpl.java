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
import com.jinoo.youtube.batch.vo.api.SearchListResponseVO;

import ch.qos.logback.classic.Logger;

@Service
public class YoutubeApiSearchServiceImpl implements YoutubeApiSearchService{

	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	@Override
	@YoutubeApiCallCount
	public ResponseEntity<SearchListResponseVO> list(Map<String, String> param) {
		logger.info("YoutubeApiSearchServiceImpl.run()");
		
		URI uri = UriComponentsBuilder.fromHttpUrl(CommonConstant.YOUTUBE_API_URI_SEARCH) 
				//.queryParam("part", "replies")
				//.queryParam("key", "AIzaSyA6VYAVhClTwj-cvvqPvnG9QsqnLI_Vgro")
				.queryParams(ConvertUtil.convert(new ObjectMapper(), param))
				.build()
				.encode().toUri();
		
		UserRestTemplate userRestTemplate = new UserRestTemplate();
		
		HttpHeaders headers  = new HttpHeaders(); 
        HttpEntity<String> entity = new HttpEntity<>(headers); 
		
        ResponseEntity<SearchListResponseVO> responseEntity = null;
        
        logger.info("uri : " + uri);
        try {
        	//ResponseEntity<String> responseEntity = userRestTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        	responseEntity = userRestTemplate.exchange(uri, HttpMethod.GET, entity, SearchListResponseVO.class);
        	
        	int httpStatus = responseEntity.getStatusCodeValue();
        	
        	logger.info("httpStatus : [" + httpStatus + "]" + responseEntity.getStatusCode());
        	
        	//logger.info("getNextPageToken : " + responseEntity.getBody().getNextPageToken());
        	//logger.info("getPrevPageToken : " + responseEntity.getBody().getPrevPageToken());
    		
        	//logger.info("responseEntity.getBody : " + responseEntity.getBody().getPageInfo().toString());
        	//logger.info("responseEntity.getStatusCode : " + responseEntity.getStatusCode());
        	//logger.info("responseEntity.getHeaders : " + responseEntity.getHeaders());
        	//logger.info("responseEntity.getStatusCodeValue : " + responseEntity.getStatusCodeValue());
        	//logger.info("responseEntity.getClass : " + responseEntity.getClass());
    		
        	
    		//logger.info("responseEntity : " + responseEntity.getBody());
    			
        } catch (HttpClientErrorException hcee) {
        
        	int httpStatus = hcee.getRawStatusCode();
        	
        	logger.info("YoutubeApiSearchServiceImpl.getStatusCode : " + hcee.getStatusCode());
        	logger.info("YoutubeApiSearchServiceImpl.getRawStatusCode : " + hcee.getRawStatusCode());
        	
        	if(httpStatus == HttpStatus.NOT_FOUND.value()) {
        	
        	}else if(httpStatus == HttpStatus.BAD_REQUEST.value()) {
        		throw hcee;
            }else if(httpStatus == HttpStatus.FORBIDDEN.value()) {
            
            }else {
            	throw hcee;
            }
        	
        	//logger.info("getResponseBodyAsString : " + hcee.getResponseBodyAsString());
        	//logger.info("getMessage : " + hcee.getMessage());
        	//e.printStackTrace();
        	
        }
        
        return responseEntity;
	}
	
/*
	public <T> URI makeUri(T param) {
		@SuppressWarnings("unchecked")
		Class<T> aa = (Class<T>) param.getClass();
		
		for(Field b : aa.getDeclaredFields()) {
			logger.info(b.getName() + " : " + b.getType());
			PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(aa, b.getName());
			Method getter = pd.getReadMethod();
			
			try {
				logger.info("getter : " + getter.invoke(param));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		return null;
	}
*/
}
