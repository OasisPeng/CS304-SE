package com.example.cs304.service;

import com.example.cs304.entity.Favourite;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public interface FavouriteService {
    @Caching(evict = {
            @CacheEvict(cacheNames = "favourite", key = "'user' + #id"),
            @CacheEvict(cacheNames = "favourite", key = "'user' + #id")
    })
    public Favourite insert(Favourite favourite);
    @Caching(evict = {
            @CacheEvict(cacheNames = "favourite", key = "'user' + #id"),
            @CacheEvict(cacheNames = "favourite", key = "'user' + #id")
    })
    public Favourite delete(Favourite favourite);
    @Cacheable(cacheNames = "favourite", key = "'user' + #id")
    public List<Favourite> selectByUserId(int id);
    @Cacheable(cacheNames = "favourite", key = "'good' + #id")
    public List<Favourite> selectByGoodId(int id);
}
