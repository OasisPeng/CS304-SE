package com.example.cs304.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cs304.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cs304
 * @since 2024-02-27
 */
@Transactional
public interface ICourseService{
    @Cacheable(cacheNames = "course", key = "#param.get(\"pageSize\") + #param.get(\"pageNum\") + #param.get(\"courseCategory\") + #param.get(\"trainingType\") + #param.get(\"courseCode\") + #param.get(\"department\") + #param.get(\"teacher\")")
    IPage listPage(HashMap param);
}
