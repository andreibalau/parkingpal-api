package com.core.parkingpalapi.configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		utilMapping(registry, "/availability");

	}

	private void utilMapping(CorsRegistry registry, String path) {
		registry.addMapping(path).allowedOrigins("*").allowedMethods("POST").allowedHeaders("*");
	}
}
