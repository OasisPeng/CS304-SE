package com.example.cs304.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("favourite")
public class Favourite {
    Integer userId;
    Integer goodId;
}
