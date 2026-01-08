package com.ismaildrcn.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@EntityScan(basePackages = { "com.ismaildrcn.model.entity" })
@SpringBootApplication
public class YumziApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(YumziApplicationStarter.class, args);
	}

}
