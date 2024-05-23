package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.Message;
import com.example.cs304.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageServiceImpl service;
    @GetMapping("/getByTwoUser/{id1}/{id2}")
    public Result getByTwoUser(@PathVariable Integer id1, @PathVariable Integer id2) {
        List<Message> list = service.SelectByTwoUser(id1, id2);
        return Result.suc(list);
    }
    @PostMapping("/insertList")
    public Result InsertList(@RequestBody List<Message> list) {
        return Result.suc(service.InsertMessages(list));
    }

    @PostMapping("/insertOne")
    public Result InsertOne(@RequestBody Message message) {
        return Result.suc(service.InsertMessage(message));
    }
}
