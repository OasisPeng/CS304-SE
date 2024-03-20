package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.dafen;
import com.example.cs304.mapper.DafenMapper;
import com.example.cs304.service.DafenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DafenServiceImpl implements DafenService {
    @Autowired
    DafenMapper mapper;
    @Override
    public dafen insert(dafen dafen) {
        mapper.insert(dafen);
        return dafen;
    }

    @Override
    public dafen deleteById(dafen dafen) {
        int i = mapper.deleteById(dafen);
        return (i == 1) ? dafen:null;
    }

    @Override
    public dafen updateById(dafen dafen) {
        int i = mapper.updateById(dafen);
        return (i == 1) ? dafen:null;
    }

    @Override
    public List<dafen> selectByCourseId(int courseId) {
        LambdaQueryWrapper<dafen> lqw = new LambdaQueryWrapper<>();
        lqw.eq(dafen::getCourseId, courseId);
        return mapper.selectList(lqw);
    }

    @Override
    public List<dafen> selectByUserId(int userId) {
        LambdaQueryWrapper<dafen> lqw = new LambdaQueryWrapper<>();
        lqw.eq(dafen::getUserId, userId);
        return mapper.selectList(lqw);
    }


}
