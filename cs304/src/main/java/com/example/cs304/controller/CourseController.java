package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.Course;
import com.example.cs304.service.ICourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    //新增
    @PostMapping("/save")
    @Operation(summary = "save", description = "add a course")
    public Result save(@RequestBody Course course){
        return courseService.save(course)?Result.suc(course):Result.fail();
    }
}
