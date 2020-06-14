package com.jinoo.youtube.batch.common;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class UserRestTemplate extends RestTemplate {

	public final static int READ_TIMEOUT = 5000; //읽기시
	public final static int CONNECT_TIMEOUT = 3000; // 연결시
	public final static int MAX_CONN_TOTAL = 100; // 최대 오픈가능 커넥션 
	public final static int MAX_CONN_PER_ROUTE = 5; //IP,포트 1쌍에 수행 할 연결 

	
	public UserRestTemplate() {
		super();
		setRequestFactory(getClientHttpRequestFactory());
	}
	
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
	      = new HttpComponentsClientHttpRequestFactory();
	    	clientHttpRequestFactory.setConnectTimeout(READ_TIMEOUT);
	    	clientHttpRequestFactory.setReadTimeout(CONNECT_TIMEOUT);
		return clientHttpRequestFactory;
	}
	
	
	
}
