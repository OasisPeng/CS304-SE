package com.example.cs304.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.cs304.entity.Favourite;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.jmx.export.annotation.ManagedNotification;

@Mapper
public interface FavouriteMapper extends BaseMapper<Favourite> {
}
