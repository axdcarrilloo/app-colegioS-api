package com.casa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AppColegioSApiApplication implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(AppColegioSApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AppColegioSApiApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("AppColegioSApiApplication.class : run() -> Iniciando servicios de Colegios...!");
	}
}
