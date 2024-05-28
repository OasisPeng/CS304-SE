package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.pinglun;
import com.example.cs304.mapper.PinglunMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PinglunServiceImplTest {

    @Mock
    private PinglunMapper mockMapper;

    private PinglunServiceImpl pinglunServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        pinglunServiceImplUnderTest = new PinglunServiceImpl();
        pinglunServiceImplUnderTest.mapper = mockMapper;
    }

    @Test
    void testInsert() {
        // Setup
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");

        final com.example.cs304.entity.pinglun expectedResult = new pinglun();
        expectedResult.setId(0);
        expectedResult.setCourseId(0);
        expectedResult.setUserId(0);
        expectedResult.setContent("content");

        // Run the test
        final com.example.cs304.entity.pinglun result = pinglunServiceImplUnderTest.insert(pinglun);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

        // Confirm PinglunMapper.insert(...).
        final com.example.cs304.entity.pinglun entity = new pinglun();
        entity.setId(0);
        entity.setCourseId(0);
        entity.setUserId(0);
        entity.setContent("content");
        verify(mockMapper).insert(entity);
    }

    @Test
    void testDeleteById() {
        // Setup
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");

        final com.example.cs304.entity.pinglun expectedResult = new pinglun();
        expectedResult.setId(0);
        expectedResult.setCourseId(0);
        expectedResult.setUserId(0);
        expectedResult.setContent("content");

        // Configure PinglunMapper.deleteById(...).
        final com.example.cs304.entity.pinglun entity = new pinglun();
        entity.setId(0);
        entity.setCourseId(0);
        entity.setUserId(0);
        entity.setContent("content");
        when(mockMapper.deleteById(entity)).thenReturn(1);

        // Run the test
        final com.example.cs304.entity.pinglun result = pinglunServiceImplUnderTest.deleteById(pinglun);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateById() {
        // Setup
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");

        final com.example.cs304.entity.pinglun expectedResult = new pinglun();
        expectedResult.setId(0);
        expectedResult.setCourseId(0);
        expectedResult.setUserId(0);
        expectedResult.setContent("content");

        // Configure PinglunMapper.updateById(...).
        final com.example.cs304.entity.pinglun entity = new pinglun();
        entity.setId(0);
        entity.setCourseId(0);
        entity.setUserId(0);
        entity.setContent("content");
        when(mockMapper.updateById(entity)).thenReturn(1);

        // Run the test
        final com.example.cs304.entity.pinglun result = pinglunServiceImplUnderTest.updateById(pinglun);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByCourseId() {
        // Setup
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");
        final List<com.example.cs304.entity.pinglun> expectedResult = List.of(pinglun);

        // Configure PinglunMapper.selectList(...).
        final com.example.cs304.entity.pinglun pinglun1 = new pinglun();
        pinglun1.setId(0);
        pinglun1.setCourseId(0);
        pinglun1.setUserId(0);
        pinglun1.setContent("content");
        final List<com.example.cs304.entity.pinglun> pingluns = List.of(pinglun1);
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(pingluns);

        // Run the test
        final List<com.example.cs304.entity.pinglun> result = pinglunServiceImplUnderTest.selectByCourseId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByCourseId_PinglunMapperReturnsNoItems() {
        // Setup
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<pinglun> result = pinglunServiceImplUnderTest.selectByCourseId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSelectByUserId() {
        // Setup
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");
        final List<com.example.cs304.entity.pinglun> expectedResult = List.of(pinglun);

        // Configure PinglunMapper.selectList(...).
        final com.example.cs304.entity.pinglun pinglun1 = new pinglun();
        pinglun1.setId(0);
        pinglun1.setCourseId(0);
        pinglun1.setUserId(0);
        pinglun1.setContent("content");
        final List<com.example.cs304.entity.pinglun> pingluns = List.of(pinglun1);
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(pingluns);

        // Run the test
        final List<com.example.cs304.entity.pinglun> result = pinglunServiceImplUnderTest.selectByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByUserId_PinglunMapperReturnsNoItems() {
        // Setup
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<pinglun> result = pinglunServiceImplUnderTest.selectByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
