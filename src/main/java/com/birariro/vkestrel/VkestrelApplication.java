package com.birariro.vkestrel;

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
public class VkestrelApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkestrelApplication.class, args);
	}

}
