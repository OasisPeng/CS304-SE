package com.example.cs304.service;

import com.example.cs304.entity.HaveBuy;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public interface HaveBuyService {
    @Caching(evict = {
            @CacheEvict(cacheNames = "havebuy", key = "'user' + #id"),
            @CacheEvict(cacheNames = "havebuy", key = "'user' + #id")
    })
    public HaveBuy insert(HaveBuy haveBuy);
    @Caching(evict = {
            @CacheEvict(cacheNames = "havebuy", key = "'user' + #id"),
            @CacheEvict(cacheNames = "havebuy", key = "'user' + #id")
    })
    public HaveBuy delete(HaveBuy haveBuy);
    @Cacheable(cacheNames = "havebuy", key = "'user' + #id")
    public List<HaveBuy> selectByUserId(int id);
    @Cacheable(cacheNames = "havebuy", key = "'good' + #id")
    public List<HaveBuy> selectByGoodId(int id);

}
