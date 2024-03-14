package com.example.cs304.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cs304.common.Code;
import com.example.cs304.common.Result;
import com.example.cs304.common.Util;
import com.example.cs304.entity.Course;
import com.example.cs304.entity.CourseForTimetable;
import com.example.cs304.entity.Student;
import com.example.cs304.service.ICourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cs304
 * @since 2024-02-27
 */
@RestController
@RequestMapping("/course")
@Tag(name = "Course")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    //分页查询
    @PostMapping("/listPage")
    public Result listPage(@RequestBody HashMap param) {
        IPage result = courseService.listPage(param);
        return Result.suc(result.getRecords());
    }

    //查找课程表
    @PostMapping ("/queryCurrentCourse")
    public Result queryKCB(@RequestBody Student student) throws IOException, URISyntaxException {
       String[] cookies =  Util.casLogin(student.getUsername(), student.getPassword());
       String rawCourseInfo = Util.getCourInfo(cookies[0], cookies[1]);
       ArrayList<CourseForTimetable> coursesInfo = Util.getCourses(rawCourseInfo);
       if (coursesInfo == null) {
           return new Result(Code.GET_Err, "查询失败，请重试", "Err");
       } else {
           return new Result(Code.GET_OK, "查询成功", coursesInfo);
       }
    }
}
