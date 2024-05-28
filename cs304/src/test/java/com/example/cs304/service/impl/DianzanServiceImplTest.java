package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.dianzan;
import com.example.cs304.mapper.DianzanMapper;
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
class DianzanServiceImplTest {

    @Mock
    private DianzanMapper mockMapper;

    private DianzanServiceImpl dianzanServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        dianzanServiceImplUnderTest = new DianzanServiceImpl();
        dianzanServiceImplUnderTest.mapper = mockMapper;
    }

    @Test
    void testInsert() {
        // Setup
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);

        final com.example.cs304.entity.dianzan expectedResult = new dianzan();
        expectedResult.setCourseId(0);
        expectedResult.setUserId(0);

        // Run the test
        final com.example.cs304.entity.dianzan result = dianzanServiceImplUnderTest.insert(dianzan);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

        // Confirm DianzanMapper.insert(...).
        final com.example.cs304.entity.dianzan entity = new dianzan();
        entity.setCourseId(0);
        entity.setUserId(0);
        verify(mockMapper).insert(entity);
    }

    @Test
    void testDeleteById() {
        // Setup
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);

        final com.example.cs304.entity.dianzan expectedResult = new dianzan();
        expectedResult.setCourseId(0);
        expectedResult.setUserId(0);

        // Configure DianzanMapper.deleteById(...).
        final com.example.cs304.entity.dianzan entity = new dianzan();
        entity.setCourseId(0);
        entity.setUserId(0);
        when(mockMapper.deleteById(entity)).thenReturn(1);

        // Run the test
        final com.example.cs304.entity.dianzan result = dianzanServiceImplUnderTest.deleteById(dianzan);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateById() {
        // Setup
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);

        final com.example.cs304.entity.dianzan expectedResult = new dianzan();
        expectedResult.setCourseId(0);
        expectedResult.setUserId(0);

        // Configure DianzanMapper.updateById(...).
        final com.example.cs304.entity.dianzan entity = new dianzan();
        entity.setCourseId(0);
        entity.setUserId(0);
        when(mockMapper.updateById(entity)).thenReturn(1);

        // Run the test
        final com.example.cs304.entity.dianzan result = dianzanServiceImplUnderTest.updateById(dianzan);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByCourseId() {
        // Setup
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);
        final List<com.example.cs304.entity.dianzan> expectedResult = List.of(dianzan);

        // Configure DianzanMapper.selectList(...).
        final com.example.cs304.entity.dianzan dianzan1 = new dianzan();
        dianzan1.setCourseId(0);
        dianzan1.setUserId(0);
        final List<com.example.cs304.entity.dianzan> dianzans = List.of(dianzan1);
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(dianzans);

        // Run the test
        final List<com.example.cs304.entity.dianzan> result = dianzanServiceImplUnderTest.selectByCourseId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByCourseId_DianzanMapperReturnsNoItems() {
        // Setup
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<dianzan> result = dianzanServiceImplUnderTest.selectByCourseId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSelectByUserId() {
        // Setup
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);
        final List<com.example.cs304.entity.dianzan> expectedResult = List.of(dianzan);

        // Configure DianzanMapper.selectList(...).
        final com.example.cs304.entity.dianzan dianzan1 = new dianzan();
        dianzan1.setCourseId(0);
        dianzan1.setUserId(0);
        final List<com.example.cs304.entity.dianzan> dianzans = List.of(dianzan1);
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(dianzans);

        // Run the test
        final List<com.example.cs304.entity.dianzan> result = dianzanServiceImplUnderTest.selectByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByUserId_DianzanMapperReturnsNoItems() {
        // Setup
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<dianzan> result = dianzanServiceImplUnderTest.selectByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
