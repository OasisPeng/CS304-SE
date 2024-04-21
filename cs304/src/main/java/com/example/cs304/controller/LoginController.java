package com.example.cs304.controller;

import com.example.cs304.common.JwtUtil;
import com.example.cs304.common.Result;
import com.example.cs304.common.Util;
import com.example.cs304.entity.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Value("${jwt.secret-key}")
    String secretKey;
    @PostMapping("/login")
    public Result login(@RequestBody Student student) {
        if (!Util.loginVerify(student.getUsername(), student.getPassword())) {
            return Result.fail();
        } else {
            String token = JwtUtil.generateToken(secretKey, student.getUsername());
            student.setToken(token);
            return Result.suc(student);
        }
    }
}
