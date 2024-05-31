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
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://"+allowedOrigins+":8080")
                .allowedMethods("GET","POST","PUT","DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
