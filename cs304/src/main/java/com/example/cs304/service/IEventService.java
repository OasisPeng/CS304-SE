package com.example.cs304.service;

import com.example.cs304.entity.Event;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 待办事件 服务类
 * </p>
 *
 * @author cs304
 * @since 2024-03-06
 */
public interface IEventService extends IService<Event> {

    List<Event> queryByDateAndOwner(String date, String owner);
}
