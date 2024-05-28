package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.HaveBuy;
import com.example.cs304.mapper.HaveBuyMapper;
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
class HaveBuyServiceImplTest {

    @Mock
    private HaveBuyMapper mockMapper;

    private HaveBuyServiceImpl haveBuyServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        haveBuyServiceImplUnderTest = new HaveBuyServiceImpl();
        haveBuyServiceImplUnderTest.mapper = mockMapper;
    }

    @Test
    void testInsert() {
        // Setup
        final HaveBuy haveBuy = new HaveBuy();
        haveBuy.setUserId(0);
        haveBuy.setGoodId(0);

        // Configure HaveBuyMapper.selectList(...).
        final HaveBuy haveBuy1 = new HaveBuy();
        haveBuy1.setUserId(0);
        haveBuy1.setGoodId(0);
        final List<HaveBuy> haveBuys = List.of(haveBuy1);
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(haveBuys);

        // Run the test
        final HaveBuy result = haveBuyServiceImplUnderTest.insert(haveBuy);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testInsert_HaveBuyMapperSelectListReturnsNoItems() {
        // Setup
        final HaveBuy haveBuy = new HaveBuy();
        haveBuy.setUserId(0);
        haveBuy.setGoodId(0);

        final HaveBuy expectedResult = new HaveBuy();
        expectedResult.setUserId(0);
        expectedResult.setGoodId(0);

        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final HaveBuy result = haveBuyServiceImplUnderTest.insert(haveBuy);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

        // Confirm HaveBuyMapper.insert(...).
        final HaveBuy entity = new HaveBuy();
        entity.setUserId(0);
        entity.setGoodId(0);
        verify(mockMapper).insert(entity);
    }

    @Test
    void testDelete() {
        // Setup
        final HaveBuy haveBuy = new HaveBuy();
        haveBuy.setUserId(0);
        haveBuy.setGoodId(0);

        final HaveBuy expectedResult = new HaveBuy();
        expectedResult.setUserId(0);
        expectedResult.setGoodId(0);

        // Run the test
        final HaveBuy result = haveBuyServiceImplUnderTest.delete(haveBuy);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockMapper).delete(any(LambdaQueryWrapper.class));
    }

    @Test
    void testSelectByUserId() {
        // Setup
        final HaveBuy haveBuy = new HaveBuy();
        haveBuy.setUserId(0);
        haveBuy.setGoodId(0);
        final List<HaveBuy> expectedResult = List.of(haveBuy);

        // Configure HaveBuyMapper.selectList(...).
        final HaveBuy haveBuy1 = new HaveBuy();
        haveBuy1.setUserId(0);
        haveBuy1.setGoodId(0);
        final List<HaveBuy> haveBuys = List.of(haveBuy1);
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(haveBuys);

        // Run the test
        final List<HaveBuy> result = haveBuyServiceImplUnderTest.selectByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByUserId_HaveBuyMapperReturnsNoItems() {
        // Setup
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<HaveBuy> result = haveBuyServiceImplUnderTest.selectByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSelectByGoodId() {
        // Setup
        final HaveBuy haveBuy = new HaveBuy();
        haveBuy.setUserId(0);
        haveBuy.setGoodId(0);
        final List<HaveBuy> expectedResult = List.of(haveBuy);

        // Configure HaveBuyMapper.selectList(...).
        final HaveBuy haveBuy1 = new HaveBuy();
        haveBuy1.setUserId(0);
        haveBuy1.setGoodId(0);
        final List<HaveBuy> haveBuys = List.of(haveBuy1);
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(haveBuys);

        // Run the test
        final List<HaveBuy> result = haveBuyServiceImplUnderTest.selectByGoodId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByGoodId_HaveBuyMapperReturnsNoItems() {
        // Setup
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<HaveBuy> result = haveBuyServiceImplUnderTest.selectByGoodId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
