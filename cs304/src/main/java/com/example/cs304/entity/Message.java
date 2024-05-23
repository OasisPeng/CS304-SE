package com.example.cs304.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("message")
public class Message {
    Integer id;
    Integer from;
    Integer to;
    String text;
    Timestamp time;
    Integer old = 0;
}
