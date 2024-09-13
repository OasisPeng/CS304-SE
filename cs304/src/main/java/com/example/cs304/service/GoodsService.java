package com.example.cs304.service;

import com.example.cs304.entity.Goods;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GoodsService {

//    @Cacheable(cacheNames= "goods", key = "'category:' + #category")
    List<Goods> findByCategory(String category);

//    @Cacheable(cacheNames = "goods", key = "'seller:' + #sellerId")
    List<Goods> findBySellerId(String sellerId);

//    @Cacheable(cacheNames = "goods", key = "'buyer:' + #buyerId")
    List<Goods> findByBuyerId(String buyerId);

//    @Cacheable(cacheNames = "goods", key = "#id")
    Goods findById(Long id);

    List<Goods> searchByKeyword(String keyword);

//    @Caching(evict = {
//            @CacheEvict(cacheNames = "goods", key = "'category:' + #goods.category", condition = "#result != null"),
//            @CacheEvict(cacheNames = "goods", key = "'seller:' + #goods.sellerId", condition = "#result != null"),
//    })
    Goods insert(Goods goods);

//    @Caching(evict = {
//            @CacheEvict(cacheNames = "goods", key = "#goods.id", condition = "#result != null"),
//            @CacheEvict(cacheNames = "goods", key = "'category:' + #goods.category", condition = "#result != null"),
//            @CacheEvict(cacheNames = "goods", key = "'buyer:' + #goods.buyerId", condition = "#result != null")
//    })
    Goods updateGoods(Goods goods);

//    @Caching(evict = {
//            @CacheEvict(cacheNames = "goods", key = "#goods.id", condition = "#result != null"),
//            @CacheEvict(cacheNames = "goods", key = "'seller:' + #goods.sellerId", condition = "#result != null"),
//            @CacheEvict(cacheNames = "goods", key = "'category:' + #goods.category", condition = "#result != null"),
//            @CacheEvict(cacheNames = "goods", key = "'buyer:' + #goods.buyerId", condition = "#result != null")
//    })
    Goods deleteById(Goods goods);
}

