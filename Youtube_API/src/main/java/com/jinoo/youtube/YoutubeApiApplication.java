package com.jinoo.youtube;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.jinoo.youtube.batch.service.RunServiceFactory;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class YoutubeApiApplication {

	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(YoutubeApiApplication.class, args);
	}
	
	@Component
	class YoutubeApiCommandLineRunner implements CommandLineRunner {
		
		@Override
		public void run(String... args) throws Exception {
		
			logger.info("========= start YoutubeApiApplication =========");
			
			RunServiceFactory.getRunService("search").run();;
			RunServiceFactory.getRunService("comments").run();;
			
			logger.info("========= end YoutubeApiApplication =========");

		}
	}
}
