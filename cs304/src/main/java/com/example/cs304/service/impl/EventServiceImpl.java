package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.cs304.entity.Event;
import com.example.cs304.mapper.EventMapper;
import com.example.cs304.service.IEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.cs304.controller.EventController.calculateWeek;

/**
 * <p>
 * 待办事件 服务实现类
 * </p>
 *
 * @author cs304
 * @since 2024-03-06
 */
@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    EventMapper eventMapper;
    @Override
    public List<Event> queryByDateAndOwner(int[] calculateDate, String owner) {
        LambdaQueryWrapper<Event> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Event::getOwner,owner);
        lambdaQueryWrapper.eq(Event::getWeek, calculateDate[0]);
        lambdaQueryWrapper.eq(Event::getXq, calculateDate[1]);
        List<Event> res = eventMapper.selectList(lambdaQueryWrapper);
        return res;
    }

    @Override
    public Event save(Event event) {
        int cnt = eventMapper.insert(event);
        return (cnt == 1) ? event : null;
    }

    @Override
    public Event updateById(Event event) {
        int cnt = eventMapper.updateById(event);
        return (cnt == 1) ? event : null;
    }

    @Override
    public Event removeById(Event event) {
        int cnt = eventMapper.deleteById(event);
        return (cnt == 1) ? event : null;
    }


}
