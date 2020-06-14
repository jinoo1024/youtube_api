package com.jinoo.youtube.batch.mapper.api;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jinoo.youtube.batch.vo.sql.YoutubeVideoH;

@Mapper
public interface YoutubeApiCommentThreadsMapper {

	ArrayList<YoutubeVideoH> getYoutubeVideoH(Map<String, Object> param);
	
	int insertYoutubeVideoCommentH(Map<String, Object> param);
}
