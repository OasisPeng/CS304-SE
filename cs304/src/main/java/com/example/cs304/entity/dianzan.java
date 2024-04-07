package com.example.cs304.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("dianzan")
@Data
public class dianzan {

    Integer courseId;
    Integer userId;
}
