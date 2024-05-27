package com.example.cs304.service;

import com.example.cs304.entity.Follow;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FollowService {
    @Caching(evict = {
            @CacheEvict(cacheNames = "follow", key = "'following' + #id"),
            @CacheEvict(cacheNames = "follow", key = "'follower' + #id")
    })
    public boolean insert(Follow follow);
    @Caching(evict = {
            @CacheEvict(cacheNames = "follow", key = "'following' + #id"),
            @CacheEvict(cacheNames = "follow", key = "'follower' + #id")
    })
    public Integer delete(Follow follow);
    @Cacheable(cacheNames = "follow", key = "'following' + #id")

    public List<Follow> selectByFollowing(Integer id);
    @Cacheable(cacheNames = "follow", key = "'follower' + #id")

    public List<Follow> selectByFollower(Integer id);
}
