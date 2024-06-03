package com.example.cs304.controller;

import com.alibaba.fastjson.JSON;
import com.example.cs304.entity.Message;
import com.example.cs304.service.impl.MessageServiceImpl;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/chatroom/{id}")
public class ChatController {
    @Autowired
    MessageServiceImpl service;

    private static ConcurrentHashMap<Integer, Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("id") Integer id) {
        sessionMap.put(id, session);
    }

    @OnClose
    public void onClose(@PathParam("id") Integer id) {
        sessionMap.remove(id);
    }

    @OnMessage
    public void onMessage(String jsonMessage, Session session, @PathParam("id") Integer id) {
        Message message = JSON.parseObject(jsonMessage, Message.class);
        Integer to = message.getTo();
        Session toSession = sessionMap.get(to);
        if (toSession != null) {
            sendMessage(jsonMessage, toSession);
        } else {
            service.InsertMessage(message);
        }
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("onError:" + error.getMessage());
    }

    private void sendMessage(String message, Session toSession) {
        try {
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
