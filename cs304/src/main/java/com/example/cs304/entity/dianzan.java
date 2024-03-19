package com.example.cs304.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("dianzan")
@Data
public class dianzan {
    @TableId
    Integer id;
    Integer courseId;
    Integer zan;
    Integer userId;
}
