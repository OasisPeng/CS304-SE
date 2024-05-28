package com.example.cs304.service;

import com.example.cs304.entity.pinglun;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PinglunService {
    @Caching(evict = {
            @CacheEvict(cacheNames = "pinglun", key = "'course' + #pinglun.courseId", condition = "#result != null"),
            @CacheEvict(cacheNames = "pinglun", key = "'user' + #pinglun.userId", condition = "#result != null")
    })
    public pinglun insert(pinglun pinglun);
    @Caching(evict = {
            @CacheEvict(cacheNames = "pinglun", key = "'course' + #pinglun.courseId", condition = "#result != null"),
            @CacheEvict(cacheNames = "pinglun", key = "'user' + #pinglun.userId", condition = "#result != null")
    })
    public pinglun deleteById(pinglun pinglun);
    @Caching(evict = {
            @CacheEvict(cacheNames = "pinglun", key = "'course' + #pinglun.courseId", condition = "#result != null"),
            @CacheEvict(cacheNames = "pinglun", key = "'user' + #pinglun.userId", condition = "#result != null")
    })
    public pinglun updateById(pinglun pinglun);
    @Cacheable(cacheNames = "pinglun", key = "'course' + #courseId")
    public List<pinglun> selectByCourseId(int courseId);
    @Cacheable(cacheNames = "pinglun", key = "'user' + #userId")
    public List<pinglun> selectByUserId(int userId);
}
