package com.ibm.airlineScheduler;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import io.github.kaiso.relmongo.config.EnableRelMongo;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ibm.airlineScheduler.*"})
@EnableRelMongo
@EnableAsync
public class AirlineSchedulerApplication {

	public static void main(String[] args) {
		System.out.println("Main from airlineScheduler run  changed with KAFKA SSL 38");
		SpringApplication.run(AirlineSchedulerApplication.class, args);
	}
	
	@Bean(name = "asyncExecutor")
	  public Executor taskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(2);
	    executor.setMaxPoolSize(2);
	    executor.setQueueCapacity(500);
	    executor.setThreadNamePrefix("GithubLookup-");
	    executor.initialize();
	    return executor;
	  }


}
