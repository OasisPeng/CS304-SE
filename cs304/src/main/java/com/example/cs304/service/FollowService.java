package com.example.cs304.service;

import com.example.cs304.entity.Follow;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface FollowService {
    public boolean insert(Follow follow);
    public Integer delete(Follow follow);
    public List<Follow> selectByFollowing(Integer id);
    public List<Follow> selectByFollower(Integer id);
}
