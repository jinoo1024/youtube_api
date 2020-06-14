package com.jinoo.youtube.batch.mapper.api;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YoutubeApiSearchMapper {

	int insertYoutubeVideoH(Map<String, Object> param);

}
