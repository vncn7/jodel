/*package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
            .allowedOrigins("http://localhost:3000") // React.js frontend URL
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Allowed HTTP methods
            .allowedHeaders("*") // Allow any headers
            .allowCredentials(true); // Allow credentials like cookies or Authorization headers
    }
}
*/
