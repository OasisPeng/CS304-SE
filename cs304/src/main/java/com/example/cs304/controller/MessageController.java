package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.Message;
import com.example.cs304.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageServiceImpl service;
    @GetMapping("/getByTwoUser/{id1}/{id2}")
    public Result getByTwoUser(@PathVariable Integer id1, @PathVariable Integer id2) {
        List<Message> list = service.SelectByTwoUser(id1, id2);
        System.out.println(list);
        return Result.suc(list);
    }
    @GetMapping("/getByOneUserTo/{id}")
    public Result getByOneUserTo(@PathVariable Integer id) {
        List<Message> list = service.SelectByOneUserTo(id);
        System.out.println(list);
        return Result.suc(list);
    }
    @GetMapping("/getByOneUserFrom/{id}")
    public Result getByOneUserFrom(@PathVariable Integer id) {
        List<Message> list2 = service.SelectByOneUserFrom(id);
        System.out.println(list2);
        return Result.suc(list2);
    }
    @PostMapping("/insertList")
    public Result InsertList(@RequestBody List<Message> list) {
        return Result.suc(service.InsertMessages(list));
    }

    @PostMapping("/insertOne")
    public Result InsertOne(@RequestBody Message message) {
        return Result.suc(service.InsertMessage(message));
    }

    @PostMapping("/sendMessage")
    public Result sendMessage(@RequestBody Message message) {
        message.setTime(new Timestamp(System.currentTimeMillis()));
        System.out.println(message);
        service.InsertMessage(message);
        return Result.suc(200);
    }
}
