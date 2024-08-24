package com.abcrestaurant.restaurantweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve images from the C:/images/ directory
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/C:/images/");

        // Serve images from the src/main/resources/static/images/ directory
        registry.addResourceHandler("/static/images/**")
                .addResourceLocations("classpath:/static/images/");
    }
}


