package com.example.cs304.mapper;

import com.example.cs304.entity.Event;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 待办事件 Mapper 接口
 * </p>
 *
 * @author cs304
 * @since 2024-03-06
 */
@Mapper
public interface EventMapper extends BaseMapper<Event> {

}
