/**
 * AI-generated-content
 * tool: ChatGPT
 * version: 3.5
 * usage: I used the prompt "请给出GoodsController类对应的单元测试类", and
 * copy the code after simple modifications from its response
 *
**/
package com.example.cs304.controller;
import com.example.cs304.common.AliOssUtil;
import com.example.cs304.common.Result;
import com.example.cs304.config.WebSocketConfig;
import com.example.cs304.entity.Goods;
import com.example.cs304.service.GoodsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GoodsControllerTest {

    @MockBean
    private AliOssUtil util;

    @MockBean
    private GoodsService goodsService;

    @Autowired
    private GoodsController goodsController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(goodsController).build();
    }

    @Test
    void uploadTest() {
        try {
            byte[] fileBytes = "test file content".getBytes();
            MultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", fileBytes);
            String expectedFilePath = "http://example.com/test.txt";
            when(util.upload(fileBytes, "test.txt")).thenReturn(expectedFilePath);

            Result result = goodsController.upload(multipartFile);

            assertEquals(Result.suc(expectedFilePath), result);
            verify(util, times(1)).upload(fileBytes, "test.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getGoodsByCategoryTest() {
        String category = "test";
        List<Goods> expectedGoodsList = Collections.singletonList(new Goods());
        when(goodsService.findByCategory(category)).thenReturn(expectedGoodsList);

        Result result = goodsController.getGoodsByCategory(category);
        System.out.println(result);
        assertEquals(Result.suc(expectedGoodsList), result);
        verify(goodsService, times(1)).findByCategory(category);
    }

    @Test
    void getGoodsBySellerIdTest() {
        String sellerId = "testSellerId";
        List<Goods> expectedGoodsList = Collections.singletonList(new Goods());
        when(goodsService.findBySellerId(sellerId)).thenReturn(expectedGoodsList);

        Result result = goodsController.getGoodsBySellerId(sellerId);

        assertEquals(Result.suc(expectedGoodsList), result);
        verify(goodsService, times(1)).findBySellerId(sellerId);
    }

    @Test
    void getGoodsByIdTest() {
        Long id = 1L;
        Goods expectedGoods = new Goods();
        when(goodsService.findById(id)).thenReturn(expectedGoods);

        Result result = goodsController.getGoodsById(id);

        assertEquals(Result.suc(expectedGoods), result);
        verify(goodsService, times(1)).findById(id);
    }

    @Test
    void searchGoodsTest() {
        String keyword = "testKeyword";
        List<Goods> expectedGoodsList = Collections.singletonList(new Goods());
        when(goodsService.searchByKeyword(keyword)).thenReturn(expectedGoodsList);

        Result result = goodsController.searchGoods(keyword);

        assertEquals(Result.suc(expectedGoodsList), result);
        verify(goodsService, times(1)).searchByKeyword(keyword);
    }

    @Test
    void addGoodsTest() {
        Goods goods = new Goods();
        when(goodsService.insert(goods)).thenReturn(goods);

        Result result = goodsController.addGoods(goods);

        assertEquals(Result.suc(goods), result);
        verify(goodsService, times(1)).insert(goods);
    }

    @Test
    void updateGoodsTest() {
        Goods goods = new Goods();
        when(goodsService.updateGoods(goods)).thenReturn(goods);

        Result result = goodsController.updateGoods(goods);

        assertEquals(Result.suc(goods), result);
        verify(goodsService, times(1)).updateGoods(goods);
    }

    @Test
    void deleteGoodsTest() {
        Goods goods = new Goods();
        when(goodsService.deleteById(goods)).thenReturn(goods);

        Result result = goodsController.deleteGoods(goods);

        assertEquals(Result.suc(goods), result);
        verify(goodsService, times(1)).deleteById(goods);
    }
}
