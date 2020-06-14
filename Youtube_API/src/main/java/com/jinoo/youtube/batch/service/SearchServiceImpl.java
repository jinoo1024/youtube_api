package com.jinoo.youtube.batch.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinoo.youtube.batch.common.CommonConstant;
import com.jinoo.youtube.batch.mapper.api.YoutubeApiSearchMapper;
import com.jinoo.youtube.batch.service.api.YoutubeApiSearchService;
import com.jinoo.youtube.batch.vo.api.SearchListItemVO;
import com.jinoo.youtube.batch.vo.api.SearchListResponseVO;

import ch.qos.logback.classic.Logger;

@Service
@Transactional
public class SearchServiceImpl implements RunService {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	@Autowired
	YoutubeApiSearchService youtubeApiSearchService;
	
	@Autowired
	YoutubeApiSearchMapper youtubeApiSearchMapper;
	
	private Map<String, String> requestParam;
	
	public SearchServiceImpl() {
		requestParam = new HashMap<>();
		makeRequestParam();
	}
	
	@Override
	public void run() {
		logger.info("********** start Search service **********");	
		
		String pageToken = "";
		
		while(pageToken != null) {
			ResponseEntity<SearchListResponseVO> responseEntity = youtubeApiSearchService.list(requestParam);
			if(responseEntity != null) {
				
				logger.info("kind : " + responseEntity.getBody().getKind());
				logger.info("etag : " + responseEntity.getBody().getEtag());
				logger.info("nextPageToken : " + responseEntity.getBody().getNextPageToken());
				logger.info("prevPageToken : " + responseEntity.getBody().getPrevPageToken());
				logger.info("pageInfo.totalResults : " + responseEntity.getBody().getPageInfo().getTotalResults());
				logger.info("pageInfo.resultsPerPage : " + responseEntity.getBody().getPageInfo().getResultsPerPage());
				
				for(SearchListItemVO searchListItemVO : responseEntity.getBody().getItems()) {
					youtubeApiSearchMapper.insertYoutubeVideoH(makeQueryParam(searchListItemVO));
				}
				
				pageToken = responseEntity.getBody().getNextPageToken();
				requestParam.put("pageToken", pageToken);
			}else {
				pageToken = null;
			}
		}
		
		logger.info("********** end Search service **********");	
		
	}

	@Override
	public String getServiceName() {
		return "search";
	}
	
	private void makeRequestParam() {
		requestParam.put("part", "snippet");
		requestParam.put("key", CommonConstant.YOUTUBE_API_KEY);
		requestParam.put("maxResults", (new Integer(50)).toString());
		requestParam.put("q", "컬리");
		requestParam.put("order", "date");
		requestParam.put("type", "video");
	}
	
	private Map<String, Object> makeQueryParam(SearchListItemVO item) {
		
		Map<String, Object> queryParam = new HashMap<>();
		queryParam.put("videoId", item.getId().getVideoId());
		queryParam.put("title", item.getSnippet().getTitle());
		queryParam.put("description", item.getSnippet().getDescription());
		queryParam.put("publishedAt", item.getSnippet().getPublishedAt());
		queryParam.put("channelId", item.getSnippet().getChannelId());
		queryParam.put("channelTitle", item.getSnippet().getChannelTitle());
		queryParam.put("searchKeyword", requestParam.get("q"));
		queryParam.put("regId",CommonConstant.YOUTUBE_USER_ID);
		queryParam.put("modId",CommonConstant.YOUTUBE_USER_ID);
		
		return queryParam;
		
	}	
}
