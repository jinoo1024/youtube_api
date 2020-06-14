package com.jinoo.youtube.batch.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinoo.youtube.batch.common.CommonConstant;
import com.jinoo.youtube.batch.mapper.api.YoutubeApiCommentThreadsMapper;
import com.jinoo.youtube.batch.service.api.YoutubeApiCommonThreadsService;
import com.jinoo.youtube.batch.vo.api.CommonThreadsListSnippetItemVO;
import com.jinoo.youtube.batch.vo.api.CommonThreadsListSnippetResponseVO;
import com.jinoo.youtube.batch.vo.sql.YoutubeVideoH;

import ch.qos.logback.classic.Logger;

@Transactional
@Service
public class CommentsServiceImpl implements RunService {

	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	@Autowired
	YoutubeApiCommonThreadsService youtubeApiCommonThreadsService;
	
	@Autowired
	YoutubeApiCommentThreadsMapper youtubeApiCommentThreadsMapper;
	
	private Map<String, String> requestParam;
	
	public CommentsServiceImpl() {
		requestParam = new HashMap<>();
		makeRequestParam();
	}
	
	@Override
	public void run() {
		logger.info("********** start Search service **********");	
		
		List<YoutubeVideoH> videoList = youtubeApiCommentThreadsMapper.getYoutubeVideoH(makeVideoListQueryParam());
		
		if(videoList != null) {
			int num = 1;
			String pageToken = null;
			
			logger.info("videoList.size : " + videoList.size());
			for(YoutubeVideoH videoVO : videoList) {
				logger.info("");
				logger.info("["+num+"]");
				logger.info("video_id : " + videoVO.getVideoId());
				logger.info("title : " + videoVO.getTitle());
				logger.info("description : " + videoVO.getDescription());
				logger.info("published_at : " + videoVO.getPublishedAt());
				logger.info("channel_id : " + videoVO.getChannelId());
				logger.info("channel_title : " + videoVO.getChannelTitle());
				logger.info("reg_id : " + videoVO.getRegId());
				logger.info("reg_date : " + videoVO.getRegDate());
				logger.info("mod_id : " + videoVO.getModId());
				logger.info("mod_date : " + videoVO.getModDate());
				logger.info("search_keyword : " + videoVO.getSearchKeyword());
				num++;

				pageToken = "";
				requestParam.put("videoId", videoVO.getVideoId());
				requestParam.put("pageToken", pageToken);

				while(pageToken != null) {
					ResponseEntity<CommonThreadsListSnippetResponseVO> responseEntity = youtubeApiCommonThreadsService.list(requestParam);
					if(responseEntity != null) {
						
						logger.info("kind : " + responseEntity.getBody().getKind());
						logger.info("etag : " + responseEntity.getBody().getEtag());
						logger.info("nextPageToken : " + responseEntity.getBody().getNextPageToken());
						logger.info("prevPageToken : " + responseEntity.getBody().getPrevPageToken());
						logger.info("pageInfo.totalResults : " + responseEntity.getBody().getPageInfo().getTotalResults());
						logger.info("pageInfo.resultsPerPage : " + responseEntity.getBody().getPageInfo().getResultsPerPage());
						
						for(CommonThreadsListSnippetItemVO commonThreadsListSnippetItemVO : responseEntity.getBody().getItems()) {
							youtubeApiCommentThreadsMapper.insertYoutubeVideoCommentH(makeQueryParam(commonThreadsListSnippetItemVO));
						}
						
						pageToken = responseEntity.getBody().getNextPageToken();
						requestParam.put("pageToken", pageToken);
					}else {
						pageToken = null;
					}
				}
			}	
		}else {
			logger.info("no video list");
		}
		
		logger.info("********** end Search service **********");	
		
	}

	@Override
	public String getServiceName() {
		return "comments";
	}
	
	private Map<String, Object> makeVideoListQueryParam() {
		
		Map<String, Object> queryParam = new HashMap<>();
		
		return queryParam;
		
	}
	
	private void makeRequestParam() {
		requestParam.put("part", "snippet");
		requestParam.put("key", CommonConstant.YOUTUBE_API_KEY);
		requestParam.put("maxResults", (new Integer(100)).toString());
		requestParam.put("order", "time");
	}
	
	private Map<String, Object> makeQueryParam(CommonThreadsListSnippetItemVO item) {
		
		Map<String, Object> queryParam = new HashMap<>();
		
		String videoId = null;
		String commentId = null;
		String textDisplay = null;
		String textOriginal = null;
		String authorDisplayName = null;
		String authorProfileImageUrl = null;
		String authorChannelUrl = null;
		String authorChannelId = null;
		boolean canRate = true;
		String viewerRating = null;
		int likeCount = 0;
		String publishedAt = null;
		String updatedAt = null;
		
		videoId = item.getSnippet().getVideoId();
		try{	commentId = item.getId();	} catch(NullPointerException e) {}
		try{	textDisplay = item.getSnippet().getTopLevelComment().getSnippet().getTextDisplay();	} catch(NullPointerException e) {}
		try{	textOriginal = item.getSnippet().getTopLevelComment().getSnippet().getTextOriginal();	} catch(NullPointerException e) {}
		try{	authorDisplayName = item.getSnippet().getTopLevelComment().getSnippet().getAuthorDisplayName();	} catch(NullPointerException e) {}
		try{	authorProfileImageUrl = item.getSnippet().getTopLevelComment().getSnippet().getAuthorProfileImageUrl();	} catch(NullPointerException e) {}
		try{	authorChannelUrl = item.getSnippet().getTopLevelComment().getSnippet().getAuthorChannelUrl();	} catch(NullPointerException e) {}
		try{	authorChannelId = item.getSnippet().getTopLevelComment().getSnippet().getAuthorChannelId().getValue();	} catch(NullPointerException e) {}
		try{	canRate = item.getSnippet().getTopLevelComment().getSnippet().isCanRate();	} catch(NullPointerException e) {}
		try{	viewerRating = item.getSnippet().getTopLevelComment().getSnippet().getViewerRating();	} catch(NullPointerException e) {}
		try{	likeCount = item.getSnippet().getTopLevelComment().getSnippet().getLikeCount();	} catch(NullPointerException e) {}
		try{	publishedAt = item.getSnippet().getTopLevelComment().getSnippet().getPublishedAt();	} catch(NullPointerException e) {}
		try{	updatedAt = item.getSnippet().getTopLevelComment().getSnippet().getUpdatedAt();	} catch(NullPointerException e) {}
		
		queryParam.put("videoId", videoId);
		queryParam.put("commentId", commentId);
		queryParam.put("textDisplay", textDisplay);
		queryParam.put("textOriginal", textOriginal);
		queryParam.put("authorDisplayName", authorDisplayName);
		queryParam.put("authorProfileImageUrl", authorProfileImageUrl);
		queryParam.put("authorChannelUrl", authorChannelUrl);
		queryParam.put("authorChannelId", authorChannelId);
		queryParam.put("canRate", canRate);
		queryParam.put("viewerRating", viewerRating);
		queryParam.put("likeCount", likeCount);
		queryParam.put("publishedAt", publishedAt);
		queryParam.put("updatedAt", updatedAt);
		queryParam.put("regId",CommonConstant.YOUTUBE_USER_ID);
		queryParam.put("modId",CommonConstant.YOUTUBE_USER_ID);
		
		return queryParam;
		
	}


	
}
