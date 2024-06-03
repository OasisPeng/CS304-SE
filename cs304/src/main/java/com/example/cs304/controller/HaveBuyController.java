package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.HaveBuy;
import com.example.cs304.service.impl.HaveBuyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/haveBuy")
public class HaveBuyController {
    @Autowired
    HaveBuyServiceImpl service;
    @PostMapping
    public Result insert(@RequestBody HaveBuy haveBuy) {
        HaveBuy haveBuy1 = service.insert(haveBuy);
        if (haveBuy1 == null) {
            return Result.fail("不能重复添加");
        } else {
            return Result.suc(haveBuy1);
        }
    }
    @DeleteMapping
    public Result delete(@RequestBody HaveBuy haveBuy) {
        HaveBuy haveBuy1 = service.delete(haveBuy);
        return Result.suc(haveBuy1);
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
