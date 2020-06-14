package com.jinoo.youtube.batch.mapper.api;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YoutubeApiCallHistoryMapper {

	int insertYoutubeApiCallHistory(Map<String, Object> param);

}
