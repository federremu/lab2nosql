package com.paquete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class NosqlProjectoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NosqlProjectoApplication.class, args);
	}

}
