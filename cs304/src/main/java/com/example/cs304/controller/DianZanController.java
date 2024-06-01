package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.dianzan;
import com.example.cs304.service.impl.DianzanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dianzan")
public class DianZanController {
    @Autowired
    DianzanServiceImpl service;
    @PostMapping
    public Result insert(@RequestBody dianzan dianzan) {
        dianzan res = service.insert(dianzan);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
    @DeleteMapping
    public Result delete(@RequestBody dianzan Dianzan) {
        dianzan res = service.deleteById(Dianzan);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
    @PutMapping
    public Result updateById(@RequestBody dianzan dianzan) {
        dianzan res = service.updateById(dianzan);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
    @GetMapping("/course/{id}")
    public Result getByCourseId(@PathVariable Integer id) {
        List<dianzan> res = service.selectByCourseId(id);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
    @GetMapping("/user/{id}")
    public Result getByUserId(@PathVariable Integer id) {
        List<dianzan> res = service.selectByUserId(id);
        if (res == null) {
            return Result.fail();
        } else {
            return Result.suc(res);
        }
    }
}
