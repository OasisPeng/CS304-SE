package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.Follow;
import com.example.cs304.service.impl.FollowServiceImpl;
import jakarta.annotation.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    FollowServiceImpl service;
    @PostMapping
    public Result Insert(@RequestBody Follow follow) {
        boolean res = service.insert(follow);
        if (res) {
            return Result.suc(follow);
        } else {
            return Result.fail("关注失败,不能重复关注");
        }
    }
    @DeleteMapping
    public Result Delete(@RequestBody Follow follow) {
        int res = service.delete(follow);
        if (res == 1) {
            return Result.suc(follow);
        } else {
            return Result.fail("取消关注失败，请重试");
        }
    }
    @GetMapping("/follower/{id}")
    public Result GetFromFollower(@RequestBody int id) {
        List<Follow> res = service.selectByFollower(id);
        return Result.suc(res);
    }
    @GetMapping("/following/{id}")
    public Result GetFromFollowing(@RequestBody int id) {
        List<Follow> res = service.selectByFollowing(id);
        return Result.suc(res);
    }
}
