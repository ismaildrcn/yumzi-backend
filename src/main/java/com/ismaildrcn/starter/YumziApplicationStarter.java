package com.ismaildrcn.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.ismaildrcn")
@EntityScan(basePackages = { "com.ismaildrcn.model.entity" })
@EnableJpaRepositories(basePackages = {
		"com.ismaildrcn.repository" }, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.ismaildrcn\\.repository\\.redis\\..*"))
@SpringBootApplication
public class YumziApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(YumziApplicationStarter.class, args);
	}

}
