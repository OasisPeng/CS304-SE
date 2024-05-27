package com.example.cs304.service;

import com.example.cs304.entity.Message;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MessageService {
    @Caching(cacheable = {
            @Cacheable(cacheNames = "messages", key = "#id1 + ' ' + #id2"),
            @Cacheable(cacheNames = "messages", key = "#id2 + ' ' + #id1")

    })
    public List<Message> SelectByTwoUser(Integer id1, Integer id2);
    @Caching(evict = {
            @CacheEvict(cacheNames = "messages", key = "#id1 + ' ' + #id2"),
            @CacheEvict(cacheNames = "messages", key = "#id2 + ' ' + #id1")

    })
    public Message InsertMessage(Message message);
    @Caching(evict = {
            @CacheEvict(cacheNames = "messages", key = "#id1 + ' ' + #id2"),
            @CacheEvict(cacheNames = "messages", key = "#id2 + ' ' + #id1")

    })
    public Boolean InsertMessages(List<Message> messages);
}
