package com.example.cs304.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.cs304.entity.Event;
import com.example.cs304.mapper.GoodsMapper;
import com.example.cs304.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cs304.entity.Goods;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> findByCategory(String category) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Goods::getCategory, category);
        return goodsMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public List<Goods> findBySellerId(String sellerId) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Goods::getSellerId, sellerId);
        return goodsMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public Goods findById(Long id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public List<Goods> searchByKeyword(String keyword) {
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(keyword), Goods::getName, keyword)
                .or().like(StringUtils.isNotBlank(keyword), Goods::getCategory, keyword)
                .or().like(StringUtils.isNotBlank(keyword), Goods::getDescription, keyword);
        return goodsMapper.selectList(queryWrapper);
    }

    @Override
    public Goods insert(Goods goods) {
        int cnt = goodsMapper.insert(goods);
        return (cnt == 1) ? goods : null;
    }

    @Override
    public Goods updateGoods(Goods goods) {
        int cnt = goodsMapper.updateById(goods);
        return (cnt == 1) ? goods : null;
    }

    @Override
    public Goods deleteById(Goods goods) {
        int cnt = goodsMapper.deleteById(goods);
        return (cnt == 1) ? goods : null;
    }
}
