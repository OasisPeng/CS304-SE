package com.example.cs304.service;

import com.example.cs304.entity.dianzan;
import com.example.cs304.entity.pinglun;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DianzanService {
    @Caching(evict = {
            @CacheEvict(cacheNames = "dianzan", key = "#dianzan.courseId", condition = "#result != null"),
            @CacheEvict(cacheNames = "dianzan", key = "#dianzan.userId", condition = "#result != null")
    })
    public dianzan insert(dianzan dianzan);
    @Caching(evict = {
            @CacheEvict(cacheNames = "dianzan", key = "#dianzan.courseId", condition = "#result != null"),
            @CacheEvict(cacheNames = "dianzan", key = "#dianzan.userId", condition = "#result != null")
    })
    public dianzan deleteById(dianzan dianzan);
    @Caching(evict = {
            @CacheEvict(cacheNames = "dianzan", key = "#dianzan.courseId", condition = "#result != null"),
            @CacheEvict(cacheNames = "dianzan", key = "#dianzan.userId", condition = "#result != null")
    })
    public dianzan updateById(dianzan dianzan);
    @Cacheable(cacheNames = "pinglun", key = "#courseId")
    public List<dianzan> selectByCourseId(int courseId);
    @Cacheable(cacheNames = "pinglun", key = "#userId")
    public List<dianzan> selectByUserId(int userId);
}
