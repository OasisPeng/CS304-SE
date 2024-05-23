package com.example.cs304.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cs304.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
