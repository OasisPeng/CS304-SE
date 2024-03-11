package com.example.cs304.service;

import com.example.cs304.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cs304
 * @since 2024-02-27
 */
@Transactional
public interface ICourseService extends IService<Course> {

}
