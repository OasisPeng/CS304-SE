package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.dianzan;
import com.example.cs304.entity.dianzan;
import com.example.cs304.mapper.DianzanMapper;
import com.example.cs304.service.DianzanService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DianzanServiceImpl implements DianzanService {
    @Autowired
    DianzanMapper mapper;
    @Override
    public dianzan insert(dianzan Dianzan) {
        LambdaQueryWrapper<dianzan> lqw = new LambdaQueryWrapper<>();
        lqw.eq(dianzan::getCourseId, Dianzan.getCourseId()).eq(dianzan::getUserId, Dianzan.getUserId());
        List<dianzan> r = mapper.selectList(lqw);
        if (r.isEmpty()) {
        mapper.insert(Dianzan);
        return Dianzan;} else {
            return null;
        }
    }

    @Override
    public dianzan deleteById(dianzan Dianzan) {
        LambdaQueryWrapper<dianzan> lqw = new LambdaQueryWrapper<>();
        System.out.println(Dianzan);
        lqw.eq(dianzan::getCourseId, Dianzan.getCourseId()).eq(dianzan::getUserId, Dianzan.getUserId());
        int i = mapper.delete(lqw);
        return (i >= 1) ? Dianzan:null;
    }

    @Override
    public dianzan updateById(dianzan dianzan) {
        int i = mapper.updateById(dianzan);
        return (i == 1) ? dianzan:null;
    }

    @Override
    public List<dianzan> selectByCourseId(int courseId) {
        LambdaQueryWrapper<dianzan> lqw = new LambdaQueryWrapper<>();
        lqw.eq(dianzan::getCourseId, courseId);
        return mapper.selectList(lqw);
    }

    @Override
    public List<dianzan> selectByUserId(int userId) {
        LambdaQueryWrapper<dianzan> lqw = new LambdaQueryWrapper<>();
        lqw.eq(dianzan::getUserId, userId);
        return mapper.selectList(lqw);
    }

}
