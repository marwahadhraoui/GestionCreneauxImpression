package com.plateformeDev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestionCreneauxImpressionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionCreneauxImpressionApplication.class, args);
	}

}
