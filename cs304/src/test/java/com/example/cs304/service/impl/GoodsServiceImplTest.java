package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.Goods;
import com.example.cs304.mapper.GoodsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoodsServiceImplTest {

    @Mock
    private GoodsMapper mockGoodsMapper;

    @InjectMocks
    private GoodsServiceImpl goodsServiceImplUnderTest;

    @Test
    void testFindByCategory() {
        // Setup
        final Goods goods = new Goods();
        goods.setName("name");
        goods.setSellerId("sellerId");
        goods.setBuyerId("buyerId");
        goods.setDescription("description");
        goods.setCategory("category");
        final List<Goods> expectedResult = List.of(goods);

        // Configure GoodsMapper.selectList(...).
        final Goods goods2 = new Goods();
        goods2.setName("name");
        goods2.setSellerId("sellerId");
        goods2.setBuyerId("buyerId");
        goods2.setDescription("description");
        goods2.setCategory("category");
        final List<Goods> goods1 = List.of(goods2);
        when(mockGoodsMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(goods1);

        // Run the test
        final List<Goods> result = goodsServiceImplUnderTest.findByCategory("category");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindByCategory_GoodsMapperReturnsNoItems() {
        // Setup
        when(mockGoodsMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Goods> result = goodsServiceImplUnderTest.findByCategory("category");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindBySellerId() {
        // Setup
        final Goods goods = new Goods();
        goods.setName("name");
        goods.setSellerId("sellerId");
        goods.setBuyerId("buyerId");
        goods.setDescription("description");
        goods.setCategory("category");
        final List<Goods> expectedResult = List.of(goods);

        // Configure GoodsMapper.selectList(...).
        final Goods goods2 = new Goods();
        goods2.setName("name");
        goods2.setSellerId("sellerId");
        goods2.setBuyerId("buyerId");
        goods2.setDescription("description");
        goods2.setCategory("category");
        final List<Goods> goods1 = List.of(goods2);
        when(mockGoodsMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(goods1);

        // Run the test
        final List<Goods> result = goodsServiceImplUnderTest.findBySellerId("sellerId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindBySellerId_GoodsMapperReturnsNoItems() {
        // Setup
        when(mockGoodsMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Goods> result = goodsServiceImplUnderTest.findBySellerId("sellerId");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindByBuyerId() {
        // Setup
        final Goods goods = new Goods();
        goods.setName("name");
        goods.setSellerId("sellerId");
        goods.setBuyerId("buyerId");
        goods.setDescription("description");
        goods.setCategory("category");
        final List<Goods> expectedResult = List.of(goods);

        // Configure GoodsMapper.selectList(...).
        final Goods goods2 = new Goods();
        goods2.setName("name");
        goods2.setSellerId("sellerId");
        goods2.setBuyerId("buyerId");
        goods2.setDescription("description");
        goods2.setCategory("category");
        final List<Goods> goods1 = List.of(goods2);
        when(mockGoodsMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(goods1);

        // Run the test
        final List<Goods> result = goodsServiceImplUnderTest.findByBuyerId("buyerId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindByBuyerId_GoodsMapperReturnsNoItems() {
        // Setup
        when(mockGoodsMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Goods> result = goodsServiceImplUnderTest.findByBuyerId("buyerId");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindById() {
        // Setup
        final Goods expectedResult = new Goods();
        expectedResult.setName("name");
        expectedResult.setSellerId("sellerId");
        expectedResult.setBuyerId("buyerId");
        expectedResult.setDescription("description");
        expectedResult.setCategory("category");

        // Configure GoodsMapper.selectById(...).
        final Goods goods = new Goods();
        goods.setName("name");
        goods.setSellerId("sellerId");
        goods.setBuyerId("buyerId");
        goods.setDescription("description");
        goods.setCategory("category");
        when(mockGoodsMapper.selectById(0L)).thenReturn(goods);

        // Run the test
        final Goods result = goodsServiceImplUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSearchByKeyword() {
        // Setup
        final Goods goods = new Goods();
        goods.setName("name");
        goods.setSellerId("sellerId");
        goods.setBuyerId("buyerId");
        goods.setDescription("description");
        goods.setCategory("category");
        final List<Goods> expectedResult = List.of(goods);

        // Configure GoodsMapper.selectList(...).
        final Goods goods2 = new Goods();
        goods2.setName("name");
        goods2.setSellerId("sellerId");
        goods2.setBuyerId("buyerId");
        goods2.setDescription("description");
        goods2.setCategory("category");
        final List<Goods> goods1 = List.of(goods2);
        when(mockGoodsMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(goods1);

        // Run the test
        final List<Goods> result = goodsServiceImplUnderTest.searchByKeyword("keyword");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSearchByKeyword_GoodsMapperReturnsNoItems() {
        // Setup
        when(mockGoodsMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Goods> result = goodsServiceImplUnderTest.searchByKeyword("keyword");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testInsert() {
        // Setup
        final Goods goods = new Goods();
        goods.setName("name");
        goods.setSellerId("sellerId");
        goods.setBuyerId("buyerId");
        goods.setDescription("description");
        goods.setCategory("category");

        final Goods expectedResult = new Goods();
        expectedResult.setName("name");
        expectedResult.setSellerId("sellerId");
        expectedResult.setBuyerId("buyerId");
        expectedResult.setDescription("description");
        expectedResult.setCategory("category");

        // Configure GoodsMapper.insert(...).
        final Goods entity = new Goods();
        entity.setName("name");
        entity.setSellerId("sellerId");
        entity.setBuyerId("buyerId");
        entity.setDescription("description");
        entity.setCategory("category");
        when(mockGoodsMapper.insert(entity)).thenReturn(0);

        // Run the test
        final Goods result = goodsServiceImplUnderTest.insert(goods);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    @Test
    void testUpdateGoods() {
        // Setup
        final Goods goods = new Goods();
        goods.setName("name");
        goods.setSellerId("sellerId");
        goods.setBuyerId("buyerId");
        goods.setDescription("description");
        goods.setCategory("category");

        final Goods expectedResult = new Goods();
        expectedResult.setName("name");
        expectedResult.setSellerId("sellerId");
        expectedResult.setBuyerId("buyerId");
        expectedResult.setDescription("description");
        expectedResult.setCategory("category");

        // Configure GoodsMapper.updateById(...).
        final Goods entity = new Goods();
        entity.setName("name");
        entity.setSellerId("sellerId");
        entity.setBuyerId("buyerId");
        entity.setDescription("description");
        entity.setCategory("category");
        when(mockGoodsMapper.updateById(entity)).thenReturn(0);

        // Run the test
        final Goods result = goodsServiceImplUnderTest.updateGoods(goods);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    @Test
    void testDeleteById() {
        // Setup
        final Goods goods = new Goods();
        goods.setName("name");
        goods.setSellerId("sellerId");
        goods.setBuyerId("buyerId");
        goods.setDescription("description");
        goods.setCategory("category");

        final Goods expectedResult = new Goods();
        expectedResult.setName("name");
        expectedResult.setSellerId("sellerId");
        expectedResult.setBuyerId("buyerId");
        expectedResult.setDescription("description");
        expectedResult.setCategory("category");

        // Configure GoodsMapper.deleteById(...).
        final Goods entity = new Goods();
        entity.setName("name");
        entity.setSellerId("sellerId");
        entity.setBuyerId("buyerId");
        entity.setDescription("description");
        entity.setCategory("category");
        when(mockGoodsMapper.deleteById(entity)).thenReturn(1);

        // Run the test
        final Goods result = goodsServiceImplUnderTest.deleteById(goods);
        System.out.println(result);
        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
