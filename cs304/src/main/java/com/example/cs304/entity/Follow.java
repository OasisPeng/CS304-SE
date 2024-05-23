package com.example.cs304.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

@Data
@TableName("follow")
public class Follow {
//   关注别人的人
    Integer following;
//    被关注的人
    Integer follower;
}
