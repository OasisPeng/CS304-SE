package com.example.cs304.controller;

import com.example.cs304.annotation.MonitorPerformance;
import com.example.cs304.common.JwtUtil;
import com.example.cs304.common.Result;
import com.example.cs304.common.Util;
import com.example.cs304.entity.Student;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    JwtUtil util;
    @PostMapping("/login")
    @MonitorPerformance
    public Result login(@RequestBody Student student) {
        System.out.println("已登录");
        if (!Util.loginVerify(student.getUsername(), student.getPassword())) {
            return Result.fail();
        } else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", student.getUsername());
            String token = util.createJWT(36000000, claims);
            HashMap<String, String> res = new HashMap<>();
            res.put("token", token);
            res.put("username", student.getUsername());
            res.put("password", student.getPassword());
            return Result.suc(res);
        }
    }
    @GetMapping("/logout/{username}")
    public Result logout(@PathVariable String username) {
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(username)){
            return Result.fail();
        } else {
            SecurityContextHolder.clearContext();
            return Result.suc(null);
        }
    }
}
