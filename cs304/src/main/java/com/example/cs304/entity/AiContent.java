package com.example.cs304.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

@Data
@TableName("ai")
public class AiContent {
    Integer user;
// "[{'role': 'user', 'content':'xxxxxxx'},{'role':'assistant', 'content':'xxxxxxx'},{'role': 'user', 'content':'xxxxxxx'},{'role':'assistant', 'content':'xxxxxxx'}]"
    String content;
    Integer id;
}
