package com.example.cs304.service;

import com.example.cs304.entity.dafen;
import com.example.cs304.entity.pinglun;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DafenService {
//    @Caching(evict = {
//            @CacheEvict(cacheNames = "dafen", key = "'course' + #dafen.courseId", condition = "#result != null"),
//            @CacheEvict(cacheNames = "dafen", key = "'user' + #dafen.userId", condition = "#result != null")
//    })
    public dafen insert(dafen dafen);
//    @Caching(evict = {
//            @CacheEvict(cacheNames = "dafen", key = "'course' + #dafen.courseId",condition = "#result != null"),
//            @CacheEvict(cacheNames = "dafen", key = "'user' + #dafen.userId",condition = "#result != null")
//    })
    public dafen deleteById(dafen dafen);
//    @Caching(evict = {
//            @CacheEvict(cacheNames = "dafen", key = "'course' + #dafen.courseId",condition = "#result != null"),
//            @CacheEvict(cacheNames = "dafen", key = "'user' + #dafen.userId",condition = "#result != null")
//    })
    public dafen updateById(dafen dafen);
//    @Cacheable(cacheNames = "pinglun", key = "'course' + #courseId")
    public List<dafen> selectByCourseId(int courseId);
//    @Cacheable(cacheNames = "pinglun", key = "'user' + #userId")
    public List<dafen> selectByUserId(int userId);

}
