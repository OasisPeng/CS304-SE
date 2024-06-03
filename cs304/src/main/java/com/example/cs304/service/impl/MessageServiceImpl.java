package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.Message;
import com.example.cs304.mapper.MessageMapper;
import com.example.cs304.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper dao;
    @Override
    public List<Message> SelectByTwoUser(Integer id1, Integer id2) {
        LambdaQueryWrapper<Message> lqw = new LambdaQueryWrapper<>();
        lqw.nested(wrapper -> {

            wrapper.eq(Message::getFrom, id1)

                    .eq(Message::getTo, id2);

        }).or(wrapper -> {

            wrapper.eq(Message::getTo, id1)

                    .eq(Message::getFrom, id2);

        }).orderByAsc(Message::getTime);
        List<Message> messages=dao.selectList(lqw);
        System.out.println("SelectByTwoUser SQL: " + lqw.getSqlSegment());
        return messages;
    }

    @Override
    public List<Message> SelectByOneUserTo(Integer id) {
        LambdaQueryWrapper<Message> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Message::getTo, id)
                .orderByAsc(Message::getTime);
        List<Message> messages = dao.selectList(lqw);
        System.out.println("SelectByOneUserTo SQL: " + lqw.getSqlSegment());
        return messages;
    }

    @Override
    public List<Message> SelectByOneUserFrom(Integer id) {
        LambdaQueryWrapper<Message> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Message::getFrom, id)
                .orderByAsc(Message::getTime);
        List<Message> messages = dao.selectList(lqw);
        System.out.println("SelectByOneUserFrom SQL: " + lqw.getSqlSegment());
        return messages;
    }


    @Override
    public Message InsertMessage(Message message) {
        if (message.getOld() == 0) {
            dao.insert(message);
        }
        return message;
    }

    @Override
    public Boolean InsertMessages(List<Message> messages) {
        for (Message message: messages) {
            if (message.getOld() == 0) {
                dao.insert(message);
            }
        }
        return true;
    }
}
