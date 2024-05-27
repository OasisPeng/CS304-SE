package com.example.cs304.filter;

import com.example.cs304.common.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil util;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Credentials", "false");
            response.setContentType("application/json;charset=utf-8");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
            response.setHeader("Access-Control-Allow-Methods", "PUT, GET, DELETE, POST, OPTIONS");
            return;
        }
        response.setHeader("Access-Control-Allow-Credentials", "false");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        response.setHeader("Access-Control-Allow-Methods", "PUT, GET, DELETE, POST, OPTIONS");

        String token = util.extractJWT(request);
        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String username = util.parseJWT(token);
            // TODO: 判断解析得到的username是否和真实username相同
            // 如果token有效
            if (username != null) {
                //将解析得到的用户认证信息（username）封装后存在上下文中，一个SecurityContext对应一个请求线程
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, null);
                // 完成身份认证，在后续过滤器.authenticated() 中判断是否通过身份验证
                SecurityContextHolder.getContext().setAuthentication(authentication);
                if (request.getServletPath().contains("/login/logout")) {
                    util.invalidJWT(token);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
