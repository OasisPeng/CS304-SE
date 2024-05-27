package com.example.cs304.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("have_buy")
public class HaveBuy {
    Integer userId;
    Integer goodId;
}
