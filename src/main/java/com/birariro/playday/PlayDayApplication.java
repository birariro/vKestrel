package com.birariro.playday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PlayDayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayDayApplication.class, args);
	}

}
