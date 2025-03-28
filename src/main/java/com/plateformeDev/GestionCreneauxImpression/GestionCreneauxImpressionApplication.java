package com.plateformeDev.GestionCreneauxImpression;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.plateformeDev.entities")
public class GestionCreneauxImpressionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionCreneauxImpressionApplication.class, args);
	}

}
