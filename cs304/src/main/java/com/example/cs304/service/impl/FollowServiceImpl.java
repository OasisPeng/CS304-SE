package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.Follow;
import com.example.cs304.mapper.FollowMapper;
import com.example.cs304.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    FollowMapper dao;
    @Override
    public boolean insert(Follow follow) {
        LambdaQueryWrapper<Follow> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Follow::getFollower, follow.getFollower()).eq(Follow::getFollowing, follow.getFollowing());
        List<Follow> list = dao.selectList(lqw);
        if (!list.isEmpty()) {
            return false;
        }
        dao.insert(follow);
        return true;
    }

    @Override
    public Integer delete(Follow follow) {
        LambdaQueryWrapper<Follow> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Follow::getFollower, follow.getFollower()).eq(Follow::getFollowing, follow.getFollowing());
        return dao.delete(lqw);
    }

    @Override
    public List<Follow> selectByFollowing(Integer id) {
        LambdaQueryWrapper<Follow> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Follow::getFollowing, id);
        return dao.selectList(lqw);
    }

    @Override
    public List<Follow> selectByFollower(Integer id) {
        LambdaQueryWrapper<Follow> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Follow::getFollower, id);
        return dao.selectList(lqw);    }
}
