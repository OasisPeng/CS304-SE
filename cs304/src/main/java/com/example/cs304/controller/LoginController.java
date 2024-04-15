package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.common.Util;
import com.example.cs304.entity.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/login")
public class LoginController {
    @PostMapping("/login")
    public Result login(@RequestBody Student student) throws IOException, URISyntaxException {
        String[] cookies =  Util.casLogin(student.getUsername(), student.getPassword());
        if (cookies[0].equals("")) {
            return Result.fail();
        } else {
            return Result.suc(cookies);
        }
    }
}
