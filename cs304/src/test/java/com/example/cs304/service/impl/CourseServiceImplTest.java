package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cs304.mapper.CourseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

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
        final HashMap param = new HashMap<>(Map.ofEntries());
//        when(mockCourseMapper.selectPage(any(Page.class), any(LambdaQueryWrapper.class)))
//                .thenReturn(new Page<>(0L, 0L, 0L, false));

        // Run the test
//        final IPage result = courseServiceImplUnderTest.listPage(param);

        // Verify the results
    }
}
