package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.dafen;
import com.example.cs304.mapper.DafenMapper;
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
class DafenServiceImplTest {

    @Mock
    private DafenMapper mockMapper;

    private DafenServiceImpl dafenServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        dafenServiceImplUnderTest = new DafenServiceImpl();
        dafenServiceImplUnderTest.mapper = mockMapper;
    }

    @Test
    void testInsert() {
        // Setup
        final dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);

        final com.example.cs304.entity.dafen expectedResult = new dafen();
        expectedResult.setCourseId(0);
        expectedResult.setScore(0);
        expectedResult.setUserId(0);

        // Run the test
        final com.example.cs304.entity.dafen result = dafenServiceImplUnderTest.insert(dafen);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

        // Confirm DafenMapper.insert(...).
        final com.example.cs304.entity.dafen entity = new dafen();
        entity.setCourseId(0);
        entity.setScore(0);
        entity.setUserId(0);
        verify(mockMapper).insert(entity);
    }

    @Test
    void testDeleteById() {
        // Setup
        final dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);

        final com.example.cs304.entity.dafen expectedResult = new dafen();
        expectedResult.setCourseId(0);
        expectedResult.setScore(0);
        expectedResult.setUserId(0);

        // Configure DafenMapper.deleteById(...).
        final com.example.cs304.entity.dafen entity = new dafen();
        entity.setCourseId(0);
        entity.setScore(0);
        entity.setUserId(0);
        when(mockMapper.deleteById(entity)).thenReturn(0);

        // Run the test
        final com.example.cs304.entity.dafen result = dafenServiceImplUnderTest.deleteById(dafen);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateById() {
        // Setup
        final dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);

        final com.example.cs304.entity.dafen expectedResult = new dafen();
        expectedResult.setCourseId(0);
        expectedResult.setScore(0);
        expectedResult.setUserId(0);

        // Configure DafenMapper.updateById(...).
        final com.example.cs304.entity.dafen entity = new dafen();
        entity.setCourseId(0);
        entity.setScore(0);
        entity.setUserId(0);
        when(mockMapper.updateById(entity)).thenReturn(0);

        // Run the test
        final com.example.cs304.entity.dafen result = dafenServiceImplUnderTest.updateById(dafen);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByCourseId() {
        // Setup
        final dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);
        final List<com.example.cs304.entity.dafen> expectedResult = List.of(dafen);

        // Configure DafenMapper.selectList(...).
        final com.example.cs304.entity.dafen dafen1 = new dafen();
        dafen1.setCourseId(0);
        dafen1.setScore(0);
        dafen1.setUserId(0);
        final List<com.example.cs304.entity.dafen> dafens = List.of(dafen1);
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(dafens);

        // Run the test
        final List<com.example.cs304.entity.dafen> result = dafenServiceImplUnderTest.selectByCourseId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByCourseId_DafenMapperReturnsNoItems() {
        // Setup
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<dafen> result = dafenServiceImplUnderTest.selectByCourseId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSelectByUserId() {
        // Setup
        final dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);
        final List<com.example.cs304.entity.dafen> expectedResult = List.of(dafen);

        // Configure DafenMapper.selectList(...).
        final com.example.cs304.entity.dafen dafen1 = new dafen();
        dafen1.setCourseId(0);
        dafen1.setScore(0);
        dafen1.setUserId(0);
        final List<com.example.cs304.entity.dafen> dafens = List.of(dafen1);
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(dafens);

        // Run the test
        final List<com.example.cs304.entity.dafen> result = dafenServiceImplUnderTest.selectByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByUserId_DafenMapperReturnsNoItems() {
        // Setup
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<dafen> result = dafenServiceImplUnderTest.selectByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
