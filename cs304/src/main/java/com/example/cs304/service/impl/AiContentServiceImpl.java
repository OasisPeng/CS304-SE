package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.AiContent;
import com.example.cs304.mapper.AiContentMapper;
import com.example.cs304.service.AiContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AiContentServiceImpl implements AiContentService {
    @Autowired
    AiContentMapper aiContentMapper;
    @Override
    public AiContent SelectById(int id) {
        return aiContentMapper.selectById(id);
    }

    @Override
    public List<AiContent> SelectByUserName(int user) {
        LambdaQueryWrapper<AiContent> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AiContent::getUser, user);
        return aiContentMapper.selectList(lqw);
    }

    @Override
    public Integer DeleteById(int id) {
        return aiContentMapper.deleteById(id);

    }
}
