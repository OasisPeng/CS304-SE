package com.example.cs304.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.cs304.common.Result;
import com.example.cs304.common.Util;
import com.example.cs304.entity.Course;
import com.example.cs304.entity.Event;
import com.example.cs304.mapper.EventMapper;
import com.example.cs304.service.IEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

/**
 * <p>
 * 待办事件 前端控制器
 * </p>
 *
 * @author cs304
 * @since 2024-03-06
 */
@RestController
@RequestMapping("/event")
@Tag(name = "Event")
public class EventController {
    @Autowired
    private IEventService eventService;

    /**
     * 新增
     * @param event
     * @return
     */
    @PostMapping("/save")
    @Operation(description = "新增一个待办, owner和title非空")
    @Parameter(name = "event")
    public Result save(@RequestBody Event event){
        if(event.getOwner().equals("") || event.getTitle().equals("")){
            return Result.fail();
        }
        //Todo:设置owner
        //2024-01-01是第一周周一
        int[] date = Util.calculateWeek(LocalDate.now());
        event.setWeek(date[0]);
        event.setXq(date[1]);
        return (eventService.save(event) != null) ? Result.suc(event):Result.fail();
    }

    /**
     * 修改
     * @param event
     * @return
     */
    @PostMapping("/update")
    @Operation(description = "修改一个待办")
    @Parameter(name = "event")
    public Result update(@RequestBody Event event){
        return (eventService.updateById(event) != null) ? Result.suc(event):Result.fail();
    }

    /**
     * 删除
     * @param event
     * @return
     */
    @PostMapping("/del")
    @Operation(description = "删除一个待办")
    @Parameter(name = "event")
    public Result del(@RequestBody Event event){
       return  (eventService.removeById(event) != null) ? Result.suc(event):Result.fail();
    }

    /**
     * 根据日期和owner查询
     * @return
     */
    @GetMapping("/query")
    @Operation(description = "根据owner和date(形如YYYY-MM-DD)查询一个待办,date为空则查询当日，返回值类型是List<Event>")
    @Parameters({@Parameter(name = "date"),@Parameter(name = "owner")})
    public Result query(@RequestParam String date, @RequestParam String owner){
        if (StringUtils.isBlank(owner) || owner.equals("null")) {
            return Result.fail();
        }
        return Result.suc(eventService.queryByDateAndOwner(Util.getCalculateDate(date), owner)); // date形如YYYY-MM-DD
    }
}
