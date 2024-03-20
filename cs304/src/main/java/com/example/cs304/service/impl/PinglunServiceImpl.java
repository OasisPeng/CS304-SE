package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.pinglun;
import com.example.cs304.mapper.PinglunMapper;
import com.example.cs304.service.PinglunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinglunServiceImpl implements PinglunService {
    @Autowired
    PinglunMapper mapper;
    @Override
    public pinglun insert(pinglun pinglun) {
        mapper.insert(pinglun);
        return pinglun;
    }

    @Override
    public pinglun deleteById(pinglun pinglun) {
        int i = mapper.deleteById(pinglun);
        return (i == 1) ? pinglun:null;
    }

    @Override
    public pinglun updateById(pinglun pinglun) {
        int i = mapper.updateById(pinglun);
        return (i == 1) ? pinglun:null;
    }

    @Override
    public List<pinglun> selectByCourseId(int courseId) {
        LambdaQueryWrapper<pinglun> lqw = new LambdaQueryWrapper<>();
        lqw.eq(pinglun::getCourseId, courseId);
        return mapper.selectList(lqw);
    }

    @Override
    public List<pinglun> selectByUserId(int userId) {
        LambdaQueryWrapper<pinglun> lqw = new LambdaQueryWrapper<>();
        lqw.eq(pinglun::getUserId, userId);
        return mapper.selectList(lqw);
    }

}
