package com.example.cs304.common;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUtil {
    @Value("${jwt.secret-key}")
    String secretKey;

    private Cache<String, String> jwtCache;
    @PostConstruct
    public void init() {
        jwtCache = Caffeine.newBuilder()
                // 缓存的最大容量
                .maximumSize(1000)
                // 设置缓存的过期时间，这里设置为 JWT 的过期时间的一半，以确保在 JWT 过期之前缓存失效
                .expireAfterWrite(301, TimeUnit.MINUTES)
                .build();
    }


    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     * @param ttlMillis jwt过期时间(毫秒)
     * @param claims    设置的信息
     * @return
     */
    public String createJWT(long ttlMillis, Map<String, Object> claims) {
        // 指定签名的时候使用的签名算法，也就是header那部分
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // 设置jwt的body
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                // 设置过期时间
                .setExpiration(exp);
        String token = builder.compact();
        jwtCache.put(token, "");
        // TODO:实现单设备登录
        return token;
    }

    /**
     * Token解密，解析 JWT Token 中的数据
     * @param token     加密后的token
     * @return
     */
    public String parseJWT(String token) {
        if (jwtCache.getIfPresent(token) == null) { // token失效
            return null;
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    // 设置需要解析的jwt
                    .parseClaimsJws(token).getBody();
            return (String) claims.get("username");
        } catch (ExpiredJwtException e) {
            log.error("Token expired");
        } catch (SignatureException e) {
            log.error("Invalid token signature");
        } catch (Exception e) {
            log.error("Error processing token: " + e.getMessage());
        }
        return null;
    }

    /**
     * 从请求头中提取token
     * @param request 拦截到的请求
     * @return
     */
    public String extractJWT(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    /**
     * 从缓存中删除jwt
     * @param token
     */
    public void invalidJWT(String token) {
        jwtCache.invalidate(token);
    }
}
