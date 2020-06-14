package com.jinoo.youtube.batch.service.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jinoo.youtube.batch.mapper.api.YoutubeApiCallHistoryMapper;

@Service
public class YoutubeApiCallHistoryServiceImpl implements YoutubeApiCallHistoryService {

	@Autowired
	YoutubeApiCallHistoryMapper youtubeApiCallHistoryMapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int insertYoutubeApiCallHistory(Map<String, Object> param) {
		
		return youtubeApiCallHistoryMapper.insertYoutubeApiCallHistory(param);
	
	}
}
