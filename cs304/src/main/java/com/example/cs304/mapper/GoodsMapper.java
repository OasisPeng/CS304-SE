package com.example.cs304.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cs304.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
}
