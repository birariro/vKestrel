package com.birariro.dailydevblogassemble;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@EnableBatchProcessing
@EnableScheduling
public class DailyDevBlogAssembleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyDevBlogAssembleApplication.class, args);
	}

}
