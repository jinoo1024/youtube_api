package com.jinoo.youtube.batch.vo.param;

import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class ApiCommentThreadParam {

	@NonNull
	private String part;
	
	@Nullable
	private String allThreadsRelatedToChannelId;
	
	private String channelId;
	
	private String id;
	
	private String videoId;
	
	private int maxResults;
	
	private String moderationStatus;
	
	private String order;
	
	private String pageToken;
	
	private String searchTerms;
	
	private String textFormat;
	
	private String key;
	
	@Builder
	public ApiCommentThreadParam(String part) {
		this.part = part;
	}
	
}
