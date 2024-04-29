package com.example.cs304.config;

import com.example.cs304.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(conf -> conf
                        //放行不需要鉴权/身份验证的路径(登录,swagger)
                        .requestMatchers(
                                "/login/login",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html"
                        )
                        .permitAll()
                        .anyRequest().authenticated()  //除 /login 外，其他请求均需要先通过身份认证
                )
                .csrf(AbstractHttpConfigurer::disable)// 禁用CSRF
                .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // 添加JWT认证过滤器，从Token中判断用户是否合法/*获取用户权限
        return http.build();
    }
}
