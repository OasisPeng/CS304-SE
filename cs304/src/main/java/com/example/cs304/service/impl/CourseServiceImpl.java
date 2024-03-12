package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cs304.entity.Course;
import com.example.cs304.mapper.CourseMapper;
import com.example.cs304.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cs304
 * @since 2024-02-27
 */
@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public IPage listPage(HashMap param) {
        String courseCategory = (String) param.get("courseCategory");
        String trainingType = (String) param.get("trainingType");
        String courseCode = (String) param.get("courseCode");
        String department = (String) param.get("department");
        String teacher = (String) param.get("teacher");

        int pageSize = (int) param.get("pageSize");
        int pageNum = (int) param.get("pageNum");

        Page<Course> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(courseCategory) && !courseCategory.equals("null"))
            lambdaQueryWrapper.like(Course::getCourseCategory, courseCategory);//设置查询条件
        if (StringUtils.isNotBlank(trainingType) && !trainingType.equals("null"))
            lambdaQueryWrapper.eq(Course::getTrainingType, trainingType);//设置查询条件
        if (StringUtils.isNotBlank(courseCode) && !courseCode.equals("null"))
            lambdaQueryWrapper.like(Course::getCourseCode, courseCode);//设置查询条件
        if (StringUtils.isNotBlank(department) && !department.equals("null"))
            lambdaQueryWrapper.eq(Course::getDepartment, department);//设置查询条件
        if (StringUtils.isNotBlank(teacher) && !teacher.equals("null"))
            lambdaQueryWrapper.eq(Course::getTeacher, teacher);//设置查询条件

        IPage result = courseMapper.selectPage(page, lambdaQueryWrapper);
        return result;
    }
}
