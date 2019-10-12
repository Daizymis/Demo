package com.hm.attempt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * @author min_huang
 *
 */
@SpringBootApplication()
@EnableScheduling
@EnableCaching
@ImportResource(locations = { "classpath:applicationContext.xml" })
public class Application {

	public static void main(String[] args) {
		// SpringApplication.run(Application.class, args);
		new SpringApplicationBuilder(Application.class).properties("spring.config.name:application").build().run(args);
	}
}
