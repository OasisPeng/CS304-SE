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
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements IEventService {
    @Autowired
    EventMapper eventMapper;
    @Override
    public List<Event> queryByDateAndOwner(String date, String owner) {
        LambdaQueryWrapper<Event> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Event::getOwner,owner);
        if (StringUtils.isBlank(date) || date.equals("null")) { //查询当前日期
            date = LocalDate.now().toString();
        }
        LocalDate time = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int[] calculateDate = calculateWeek(time);
        lambdaQueryWrapper.eq(Event::getWeek, calculateDate[0]);
        lambdaQueryWrapper.eq(Event::getXq, calculateDate[1]);
        List<Event> res = eventMapper.selectList(lambdaQueryWrapper);
        return res;
    }
}
