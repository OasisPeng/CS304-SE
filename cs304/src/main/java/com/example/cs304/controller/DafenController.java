package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.dafen;
import com.example.cs304.service.impl.DafenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dafen")
public class DafenController {
    @Autowired
    DafenServiceImpl service;
    @PostMapping
    public Result insert(@RequestBody dafen dafen) {
        dafen res = service.insert(dafen);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
    @DeleteMapping
    public Result delete(@RequestBody dafen dafen) {
        dafen res = service.deleteById(dafen);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
    @PutMapping
    public Result updateById(@RequestBody dafen dafen) {
        dafen res = service.updateById(dafen);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
    @GetMapping("/course/{id}")
    public Result getByCourseId(@PathVariable Integer id) {
        List<dafen> res = service.selectByCourseId(id);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
    @GetMapping("/user/{id}")
    public Result getByUserId(@PathVariable Integer id) {
        List<dafen> res = service.selectByUserId(id);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
}
