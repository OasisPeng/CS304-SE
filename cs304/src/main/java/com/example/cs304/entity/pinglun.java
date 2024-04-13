package com.example.cs304.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("pinglun")
@Data
public class pinglun {
    Integer Id;
    Integer courseId;
    Integer userId;
    String content;
}
