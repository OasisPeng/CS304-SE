package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cs304.entity.Course;
import com.example.cs304.mapper.CourseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseMapper mockCourseMapper;

    private CourseServiceImpl courseServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        courseServiceImplUnderTest = new CourseServiceImpl();
        courseServiceImplUnderTest.courseMapper = mockCourseMapper;
    }

    @Test
    void testListPage() {
        // Setup
        final HashMap<String, Object> param = new HashMap<>();
        param.put("courseCategory", "Math");
        param.put("trainingType", "Online");
        param.put("courseCode", "MATH101");
        param.put("department", "Science");
        param.put("teacher", "John Doe");
        param.put("pageSize", 10);
        param.put("pageNum", 1);

        // Create a mock page
        IPage<Course> mockPage = new Page<>();
        when(mockCourseMapper.selectPage(any(Page.class), any(LambdaQueryWrapper.class)))
                .thenReturn((Page) mockPage);

        // Run the test
        final IPage<Course> result = courseServiceImplUnderTest.listPage(param);

        // Verify the results
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(mockPage);
    }
}
