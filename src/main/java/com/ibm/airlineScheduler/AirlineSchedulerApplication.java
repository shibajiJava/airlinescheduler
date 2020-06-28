package com.ibm.airlineScheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.github.kaiso.relmongo.config.EnableRelMongo;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ibm.airlineScheduler.*"})
@EnableRelMongo
public class AirlineSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineSchedulerApplication.class, args);
	}

}
