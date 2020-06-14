package com.jinoo.youtube.batch.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunServiceFactory {

	@Autowired
	private List<RunService> runServices;

	private static final Map<String, RunService> runServiceCache = new HashMap<>();
	
	@PostConstruct
	public void initRunServiceCache() {
		for(RunService runService : runServices) {
			runServiceCache.put(runService.getServiceName(), runService);
		}
	}
	
	public static RunService getRunService(String type) {
		RunService service = runServiceCache.get(type);
		if(service == null) {
			throw new RuntimeException("Unknown service type : " + type);
		}
		return service;
	}
}
