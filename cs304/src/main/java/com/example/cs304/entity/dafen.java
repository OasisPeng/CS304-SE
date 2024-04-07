package com.example.cs304.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("dafen")
@Data
public class dafen {

    Integer courseId;
    Integer score;
    Integer userId;
}
