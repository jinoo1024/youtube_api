package com.jinoo.youtube.batch.vo.param;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApiSearchParam {

	@NonNull
	private String part;
	
	@Builder
	public ApiSearchParam(String part) {
		this.part = part;
	}
	
	private String key;
	
	private boolean forContentOwner;
	
	private boolean forMine;
	
	private String relatedToVideoId;
	
	private String channelId;
	
	private String channelType;
	
	private String eventType;
	
	private int maxResults;
	
	private String onBehalfOfContentOwner;
	
	private String order;
	
	private String pageToken;
	
	private String publishedAfter;
	
	private String publishedBefore;
	
	private String q;
	
	private String regionCode;
	
	private String safeSearch;
	
	private String topicId;
	
	private String type;
	
	private String videoCaption;
	
	private String videoCategoryId;
	
	private String videoDefinition;
	
	private String videoDimension;
	
	private String videoDuration;
	
	private String videoEmbeddable;
	
	private String videoLicense;
	
	private String videoSyndicated;
	
	private String videoType;
	
}
