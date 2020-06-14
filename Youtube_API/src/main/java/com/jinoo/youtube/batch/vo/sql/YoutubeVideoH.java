package com.jinoo.youtube.batch.vo.sql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class YoutubeVideoH {

	private String videoId;
	
	private String title;
	
	private String description;
	
	private String publishedAt;
	
	private String channelId;
	
	private String channelTitle;
	
	private String regId;
	
	private String regDate;
	
	private String modId;
	
	private String modDate;
	
	private String searchKeyword;
	
}
