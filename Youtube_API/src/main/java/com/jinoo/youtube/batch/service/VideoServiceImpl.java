package com.jinoo.youtube.batch.service;

import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements RunService{

	@Override
	public void run() {
		System.out.println("Vedio service");		
	}

	@Override
	public String getServiceName() {
		return "video";
	}

}
