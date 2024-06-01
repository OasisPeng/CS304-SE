package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.Favourite;
import com.example.cs304.mapper.FavouriteMapper;
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
class FavouriteServiceImplTest {

    @Mock
    private FavouriteMapper mockMapper;

    private FavouriteServiceImpl favouriteServiceImplUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        favouriteServiceImplUnderTest = new FavouriteServiceImpl();
        favouriteServiceImplUnderTest.mapper = mockMapper;
    }

    @Test
    void testInsert() {
        // Setup
        final Favourite favourite = new Favourite();
        favourite.setUserId(0);
        favourite.setGoodId(0);

        // Configure FavouriteMapper.selectList(...).
        final Favourite favourite1 = new Favourite();
        favourite1.setUserId(0);
        favourite1.setGoodId(0);
        final List<Favourite> favourites = List.of(favourite1);
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(favourites);

        // Run the test
        final Favourite result = favouriteServiceImplUnderTest.insert(favourite);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testInsert_FavouriteMapperSelectListReturnsNoItems() {
        // Setup
        final Favourite favourite = new Favourite();
        favourite.setUserId(0);
        favourite.setGoodId(0);

        final Favourite expectedResult = new Favourite();
        expectedResult.setUserId(0);
        expectedResult.setGoodId(0);

        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final Favourite result = favouriteServiceImplUnderTest.insert(favourite);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

        // Confirm FavouriteMapper.insert(...).
        final Favourite entity = new Favourite();
        entity.setUserId(0);
        entity.setGoodId(0);
        verify(mockMapper).insert(entity);
    }

    @Test
    void testDelete() {
        // Setup
        final Favourite favourite = new Favourite();
        favourite.setUserId(0);
        favourite.setGoodId(0);

        final Favourite expectedResult = new Favourite();
        expectedResult.setUserId(0);
        expectedResult.setGoodId(0);

        // Run the test
        final Favourite result = favouriteServiceImplUnderTest.delete(favourite);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockMapper).delete(any(LambdaQueryWrapper.class));
    }

    @Test
    void testSelectByUserId() {
        // Setup
        final Favourite favourite = new Favourite();
        favourite.setUserId(0);
        favourite.setGoodId(0);
        final List<Favourite> expectedResult = List.of(favourite);

        // Configure FavouriteMapper.selectList(...).
        final Favourite favourite1 = new Favourite();
        favourite1.setUserId(0);
        favourite1.setGoodId(0);
        final List<Favourite> favourites = List.of(favourite1);
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(favourites);

        // Run the test
        final List<Favourite> result = favouriteServiceImplUnderTest.selectByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByUserId_FavouriteMapperReturnsNoItems() {
        // Setup
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Favourite> result = favouriteServiceImplUnderTest.selectByUserId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSelectByGoodId() {
        // Setup
        final Favourite favourite = new Favourite();
        favourite.setUserId(0);
        favourite.setGoodId(0);
        final List<Favourite> expectedResult = List.of(favourite);

        // Configure FavouriteMapper.selectList(...).
        final Favourite favourite1 = new Favourite();
        favourite1.setUserId(0);
        favourite1.setGoodId(0);
        final List<Favourite> favourites = List.of(favourite1);
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(favourites);

        // Run the test
        final List<Favourite> result = favouriteServiceImplUnderTest.selectByGoodId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByGoodId_FavouriteMapperReturnsNoItems() {
        // Setup
        when(mockMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Favourite> result = favouriteServiceImplUnderTest.selectByGoodId(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
