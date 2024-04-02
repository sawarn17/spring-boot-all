package com.appinventiv.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
//it is used to tell the spring boot application to run the task in background.
@EnableAsync
public class AsyncConfig {
	
	
	@Bean(name = "taskExecutor")
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		//it means that it set the total 100 threads are now in the waiting state
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("user-hread-");
		executor.initialize();
		return executor;
	}
}
