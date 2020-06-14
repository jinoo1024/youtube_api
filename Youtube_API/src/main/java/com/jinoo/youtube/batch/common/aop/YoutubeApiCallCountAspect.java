package com.jinoo.youtube.batch.common.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.jinoo.youtube.batch.common.CommonConstant;
import com.jinoo.youtube.batch.common.util.StringUtil;
import com.jinoo.youtube.batch.service.api.YoutubeApiCallHistoryService;

import ch.qos.logback.classic.Logger;

@Component
@Aspect
public class YoutubeApiCallCountAspect {

	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	@Autowired
	YoutubeApiCallHistoryService youtubeApiCallHistoryService;
	
	//@Around("execution(* com.jinoo.youtube.batch.service.api..*.*(..))")
	@Pointcut("@annotation(com.jinoo.youtube.batch.common.annotation.YoutubeApiCallCount)")
	public void youTubeApiCallCountPointcut() {}
	
	@AfterReturning(pointcut = "youTubeApiCallCountPointcut()", returning = "returnValue")
	public void insertYoutubeApiCallCountAfterReturnning(JoinPoint joinPoint, Object returnValue) throws RuntimeException {
	    
		String apiClass = joinPoint.getTarget().getClass().getName().toString();
		String signature = joinPoint.getSignature().toString();
		
		logger.info("YoutubeApiCallCountAspect.insertYoutubeApiCallCountAfterReturnning");
		logger.info("signature : " + signature);
		logger.info("apiClass : " + apiClass);
		
		Map<String, Object> queryParam = new HashMap<>();
		queryParam.put("apiClass", apiClass);
		queryParam.put("signature", signature);
		queryParam.put("regId",CommonConstant.YOUTUBE_USER_ID);
		queryParam.put("modId",CommonConstant.YOUTUBE_USER_ID);

		youtubeApiCallHistoryService.insertYoutubeApiCallHistory(queryParam);
	
	}
	
	@AfterThrowing(pointcut = "youTubeApiCallCountPointcut()", throwing = "exception")
	public void insertYoutubeApiCallCountAfterThrowing(JoinPoint joinPoint, HttpClientErrorException exception) {
	    
		String apiClass = joinPoint.getTarget().getClass().getName().toString();
		String signature = joinPoint.getSignature().toString();
		String ExceptionType = StringUtil.substringByStd(exception.getClass().getName(), "$");
		int httpStatus = exception.getRawStatusCode();
		
		logger.info("YoutubeApiCallCountAspect.insertYoutubeApiCallCountAfterThrowing.HttpClientErrorException");
		logger.info("signature : " + signature);
		logger.info("apiClass : " + apiClass);
		logger.info("exceptionType : " + ExceptionType);
		logger.info("httpStatus : " + httpStatus);

		Map<String, Object> queryParam = new HashMap<>();
		queryParam.put("apiClass", apiClass);
		queryParam.put("signature", signature);
		queryParam.put("exceptionType", ExceptionType);
		queryParam.put("httpStatus", httpStatus);
		queryParam.put("regId",CommonConstant.YOUTUBE_USER_ID);
		queryParam.put("modId",CommonConstant.YOUTUBE_USER_ID);

		youtubeApiCallHistoryService.insertYoutubeApiCallHistory(queryParam);
			
	}	
}
