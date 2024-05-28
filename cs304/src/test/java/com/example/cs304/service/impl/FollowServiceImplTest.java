package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.Follow;
import com.example.cs304.mapper.FollowMapper;
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
class FollowServiceImplTest {

    @Mock
    private FollowMapper mockDao;

    private FollowServiceImpl followServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        followServiceImplUnderTest = new FollowServiceImpl();
        followServiceImplUnderTest.dao = mockDao;
    }

    @Test
    void testInsert() {
        // Setup
        final Follow follow = new Follow();
        follow.setFollowing(0);
        follow.setFollower(0);

        // Configure FollowMapper.selectList(...).
        final Follow follow1 = new Follow();
        follow1.setFollowing(0);
        follow1.setFollower(0);
        final List<Follow> list = List.of(follow1);
        when(mockDao.selectList(any(LambdaQueryWrapper.class))).thenReturn(list);

        // Run the test
        final boolean result = followServiceImplUnderTest.insert(follow);

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testInsert_FollowMapperSelectListReturnsNoItems() {
        // Setup
        final Follow follow = new Follow();
        follow.setFollowing(0);
        follow.setFollower(0);

        when(mockDao.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final boolean result = followServiceImplUnderTest.insert(follow);

        // Verify the results
        assertThat(result).isTrue();

        // Confirm FollowMapper.insert(...).
        final Follow entity = new Follow();
        entity.setFollowing(0);
        entity.setFollower(0);
        verify(mockDao).insert(entity);
    }

    @Test
    void testDelete() {
        // Setup
        final Follow follow = new Follow();
        follow.setFollowing(0);
        follow.setFollower(0);

        when(mockDao.delete(any(LambdaQueryWrapper.class))).thenReturn(0);

        // Run the test
        final Integer result = followServiceImplUnderTest.delete(follow);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testSelectByFollowing() {
        // Setup
        final Follow follow = new Follow();
        follow.setFollowing(0);
        follow.setFollower(0);
        final List<Follow> expectedResult = List.of(follow);

        // Configure FollowMapper.selectList(...).
        final Follow follow1 = new Follow();
        follow1.setFollowing(0);
        follow1.setFollower(0);
        final List<Follow> list = List.of(follow1);
        when(mockDao.selectList(any(LambdaQueryWrapper.class))).thenReturn(list);

        // Run the test
        final List<Follow> result = followServiceImplUnderTest.selectByFollowing(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByFollowing_FollowMapperReturnsNoItems() {
        // Setup
        when(mockDao.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Follow> result = followServiceImplUnderTest.selectByFollowing(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSelectByFollower() {
        // Setup
        final Follow follow = new Follow();
        follow.setFollowing(0);
        follow.setFollower(0);
        final List<Follow> expectedResult = List.of(follow);

        // Configure FollowMapper.selectList(...).
        final Follow follow1 = new Follow();
        follow1.setFollowing(0);
        follow1.setFollower(0);
        final List<Follow> list = List.of(follow1);
        when(mockDao.selectList(any(LambdaQueryWrapper.class))).thenReturn(list);

        // Run the test
        final List<Follow> result = followServiceImplUnderTest.selectByFollower(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByFollower_FollowMapperReturnsNoItems() {
        // Setup
        when(mockDao.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Follow> result = followServiceImplUnderTest.selectByFollower(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
