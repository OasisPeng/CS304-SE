package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.pinglun;
import com.example.cs304.service.impl.PinglunServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pinglun")
public class PinglunController {
    @Autowired
    PinglunServiceImpl service;
    @PostMapping
    public Result insert(@RequestBody pinglun pinglun) {
        pinglun res = service.insert(pinglun);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
    @DeleteMapping
    public Result delete(@RequestBody pinglun pinglun) {
        pinglun res = service.deleteById(pinglun);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
    @PutMapping
    public Result updateById(@RequestBody pinglun pinglun) {
        pinglun res = service.updateById(pinglun);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
    @GetMapping("/course/{id}")
    public Result getByCourseId(@PathVariable Integer id) {
        List<pinglun> res = service.selectByCourseId(id);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
    @GetMapping("/user/{id}")
    public Result getByUserId(@PathVariable Integer id) {
        List<pinglun> res = service.selectByUserId(id);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
}
