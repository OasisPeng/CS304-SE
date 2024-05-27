package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.HaveBuy;
import com.example.cs304.entity.HaveBuy;
import com.example.cs304.mapper.HaveBuyMapper;
import com.example.cs304.service.HaveBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HaveBuyServiceImpl implements HaveBuyService {
    @Autowired
    HaveBuyMapper mapper;
    @Override
    public HaveBuy insert(HaveBuy haveBuy) {
        LambdaQueryWrapper<HaveBuy> lqw = new LambdaQueryWrapper<>();
        lqw.eq(HaveBuy::getUserId, haveBuy.getUserId()).eq(HaveBuy::getGoodId, haveBuy.getGoodId());
        List<HaveBuy> l = mapper.selectList(lqw);
        if (l.isEmpty()) {
            mapper.insert(haveBuy);
            return haveBuy;
        } else {
            return null;
        }
    }

    @Override
    public HaveBuy delete(HaveBuy haveBuy) {
        LambdaQueryWrapper<HaveBuy> lqw = new LambdaQueryWrapper<>();
        lqw.eq(HaveBuy::getUserId, haveBuy.getUserId()).eq(HaveBuy::getGoodId, haveBuy.getGoodId());
        mapper.delete(lqw);
        return haveBuy;
    }

    @Override
    public List<HaveBuy> selectByUserId(int id) {
        LambdaQueryWrapper<HaveBuy> lqw = new LambdaQueryWrapper<>();
        lqw.eq(HaveBuy::getUserId, id);
        return mapper.selectList(lqw);
    }

    @Override
    public List<HaveBuy> selectByGoodId(int id) {
        LambdaQueryWrapper<HaveBuy> lqw = new LambdaQueryWrapper<>();
        lqw.eq(HaveBuy::getGoodId, id);
        return mapper.selectList(lqw);
    }
}
