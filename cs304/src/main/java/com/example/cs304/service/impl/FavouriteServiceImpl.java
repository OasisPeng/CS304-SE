package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.Favourite;
import com.example.cs304.mapper.FavouriteMapper;
import com.example.cs304.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    FavouriteMapper mapper;
    @Override
    public Favourite insert(Favourite favourite) {
        LambdaQueryWrapper<Favourite> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Favourite::getUserId, favourite.getUserId()).eq(Favourite::getGoodId, favourite.getGoodId());
        List<Favourite> q = mapper.selectList(lqw);
        if (q.isEmpty()) {
            mapper.insert(favourite);
            return favourite;
        } else {
            return null;
        }
    }

    @Override
    public Favourite delete(Favourite favourite) {
        LambdaQueryWrapper<Favourite> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Favourite::getUserId, favourite.getUserId()).eq(Favourite::getGoodId, favourite.getGoodId());
        mapper.delete(lqw);
        return favourite;
    }

    @Override
    public List<Favourite> selectByUserId(int id) {
        LambdaQueryWrapper<Favourite> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Favourite::getUserId, id);
        return mapper.selectList(lqw);
    }

    @Override
    public List<Favourite> selectByGoodId(int id) {
        LambdaQueryWrapper<Favourite> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Favourite::getGoodId, id);
        return mapper.selectList(lqw);
    }
}
