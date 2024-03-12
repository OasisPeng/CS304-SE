package com.example.cs304.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    @Primary
    @Bean
    public CacheManager customCacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        List<CaffeineCache> cacheList = new ArrayList<>();
        cacheList.add(buildCache("event"));
        cacheList.add(buildCache("course"));
        simpleCacheManager.setCaches(cacheList);
        return simpleCacheManager;
    }

    public CaffeineCache buildCache(String name) {
//        System.out.println("缓存启动");
        return new CaffeineCache(name, Caffeine.newBuilder()
                .maximumSize(100)
                .initialCapacity(100)
                .expireAfterAccess(20, TimeUnit.MINUTES)
                .recordStats()
                .build(),
                true);
    }

}
