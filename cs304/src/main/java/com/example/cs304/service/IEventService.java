package com.example.cs304.service;

import com.example.cs304.entity.Event;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 待办事件 服务类
 * </p>
 *
 * @author cs304
 * @since 2024-03-06
 */
@Transactional
public interface IEventService {
    @Cacheable(cacheNames = "event", key = "#calculateDate[0] + #owner + #calculateDate[1]")
    List<Event> queryByDateAndOwner(int[] calculateDate, String owner);
    @CacheEvict(cacheNames = "event", key = "#event.week + #event.owner + #event.xq", condition = "#result != null")
    //这里因为主要查询的是一个List<event>，而无论是存储还是删除更新都是一个event,所以不对应，只能在每次新增删除更新时将整个list的缓存清除
    Event save(Event event);
    @CacheEvict(cacheNames = "event", key = "#event.week + #event.owner + #event.xq", condition = "#result != null")
    Event updateById(Event event);
    @CacheEvict(cacheNames = "event", key = "#event.week + #event.owner + #event.xq", condition = "#result != null")
    Event removeById(Event event);
}
