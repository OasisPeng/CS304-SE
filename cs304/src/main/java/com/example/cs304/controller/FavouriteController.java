package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.Favourite;
import com.example.cs304.service.FavouriteService;
import com.example.cs304.service.impl.FavouriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favourite")
public class FavouriteController {
    @Autowired
    FavouriteServiceImpl service;
    @PostMapping
    public Result insert(@RequestBody Favourite favourite) {
        Favourite favourite1 = service.insert(favourite);
        if (favourite1 == null) {
            return Result.fail("不能重复添加");
        } else {
            return Result.suc(favourite1);
        }
    }
    @DeleteMapping
    public Result delete(@RequestBody Favourite favourite) {
        Favourite favourite1 = service.delete(favourite);
        return Result.suc(favourite1);
    }
    @GetMapping("/user/{id}")
    public Result getByUserId(@PathVariable Integer id) {
        return Result.suc(service.selectByUserId(id));
    }
    @GetMapping("/good/{id}")
    public Result getByGoodId(@PathVariable Integer id) {
        return Result.suc(service.selectByGoodId(id));
    }
}
