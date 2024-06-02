package com.example.cs304.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//全局跨域
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Value("${cors.domain}")
    private String allowedOrigins;
    @Override
    public void addCorsMappings(CorsRegistry registry){
        String[] origins = allowedOrigins.split(",");
        for (int i = 0; i < origins.length; i++) {
            origins[i] = "http://" + origins[i] + ":8080";
        }
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins(origins)
                .allowedMethods("GET","POST","PUT","DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
