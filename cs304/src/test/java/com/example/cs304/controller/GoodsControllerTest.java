package com.example.cs304.controller;

import com.example.cs304.common.AliOssUtil;
import com.example.cs304.entity.Goods;
import com.example.cs304.filter.JwtAuthenticationTokenFilter;
import com.example.cs304.service.GoodsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GoodsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AliOssUtil mockUtil;
    @MockBean
    private GoodsService mockGoodsService;

    @Test
    @WithMockUser
    void testUpload() throws Exception {
        // Setup
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test data".getBytes());
        when(mockUtil.upload(any(byte[].class), any(String.class))).thenReturn("filePath"); //规定upload的返回值
        // Run the test
        mockMvc.perform(multipart("/goods/upload")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    @WithMockUser
    void testGetGoodsByCategory() throws Exception {
        // Configure GoodsService.findByCategory(...).
        final Goods goods1 = new Goods();
        goods1.setId(0L);
        goods1.setName("name");
        goods1.setPrice(new BigDecimal("0.00"));
        goods1.setImage("image");
        goods1.setSellerId("sellerId");
        final List<Goods> goods = List.of(goods1);
        when(mockGoodsService.findByCategory("category")).thenReturn(goods);

        // Run the test and Verify the results
        mockMvc.perform(get("/goods/category/{category}", "category")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));

    }
    @Test
    @WithMockUser
    void testGetGoodsBySellerId() throws Exception {
        // Configure GoodsService.findBySellerId(...).
        final Goods goods1 = new Goods();
        goods1.setId(0L);
        goods1.setName("name");
        goods1.setPrice(new BigDecimal("0.00"));
        goods1.setImage("image");
        goods1.setSellerId("sellerId");
        final List<Goods> goods = List.of(goods1);
        when(mockGoodsService.findBySellerId("sellerId")).thenReturn(goods);

        // Run the test
        mockMvc.perform(get("/goods/seller/{sellerId}", "sellerId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    @WithMockUser
    void testGetGoodsByBuyerId() throws Exception {
        // Configure GoodsService.findByBuyerId(...).
        final Goods goods1 = new Goods();
        goods1.setId(0L);
        goods1.setName("name");
        goods1.setPrice(new BigDecimal("0.00"));
        goods1.setImage("image");
        goods1.setSellerId("sellerId");
        final List<Goods> goods = List.of(goods1);
        when(mockGoodsService.findByBuyerId("buyerId")).thenReturn(goods);

        // Run the test
        mockMvc.perform(get("/goods/buyer/{buyerId}", "buyerId")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    @WithMockUser
    void testGetGoodsById() throws Exception {
        // Setup
        // Configure GoodsService.findById(...).
        final Goods goods = new Goods();
        goods.setId(0L);
        goods.setName("name");
        goods.setPrice(new BigDecimal("0.00"));
        goods.setImage("image");
        goods.setSellerId("sellerId");
        when(mockGoodsService.findById(0L)).thenReturn(goods);

        // Run the test
        mockMvc.perform(get("/goods/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    @WithMockUser
    void testSearchGoods() throws Exception {
        // Setup
        // Configure GoodsService.searchByKeyword(...).
        final Goods goods1 = new Goods();
        goods1.setId(0L);
        goods1.setName("name");
        goods1.setPrice(new BigDecimal("0.00"));
        goods1.setImage("image");
        goods1.setSellerId("sellerId");
        final List<Goods> goods = List.of(goods1);
        when(mockGoodsService.searchByKeyword("keyword")).thenReturn(goods);

        // Run the test
        mockMvc.perform(get("/goods/search")
                        .param("keyword", "keyword")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    @WithMockUser
    void testAddGoods() throws Exception {
        // Setup
        // Configure GoodsService.insert(...).
        final Goods goods = new Goods();
        goods.setId(0L);
        goods.setName("name");
        goods.setPrice(new BigDecimal("0.00"));
        goods.setImage("image");
        goods.setSellerId("sellerId");
        final Goods goods1 = new Goods();
        goods1.setId(1L);
        goods1.setName("name");
        goods1.setPrice(new BigDecimal("0.00"));
        goods1.setImage("image");
        goods1.setSellerId("sellerId");
        when(mockGoodsService.insert(goods1)).thenReturn(goods1);
        when(mockGoodsService.insert(goods)).thenReturn(null);
        // Run the test
        mockMvc.perform(post("/goods/add")
                        .content(new ObjectMapper().writeValueAsString(goods1)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));
        // Run the test
        mockMvc.perform(post("/goods/add")
                        .content(new ObjectMapper().writeValueAsString(goods)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(400));
    }
    @Test
    @WithMockUser
    void testUpdateGoods() throws Exception {
        // Setup
        // Configure GoodsService.updateGoods(...).
        final Goods goods = new Goods();
        goods.setId(0L);
        goods.setName("name");
        goods.setPrice(new BigDecimal("0.00"));
        goods.setImage("image");
        goods.setSellerId("sellerId");
        final Goods goods1 = new Goods();
        goods1.setId(1L);
        goods1.setName("name");
        goods1.setPrice(new BigDecimal("0.00"));
        goods1.setImage("image");
        goods1.setSellerId("sellerId");
        when(mockGoodsService.updateGoods(goods1)).thenReturn(goods1);
        when(mockGoodsService.updateGoods(goods)).thenReturn(null);

        // Run the test
        mockMvc.perform(post("/goods/update")
                        .content(new ObjectMapper().writeValueAsString(goods1)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));
        mockMvc.perform(post("/goods/update")
                        .content(new ObjectMapper().writeValueAsString(goods)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(400));
    }

    @Test
    @WithMockUser
    void testDeleteGoods() throws Exception {
        // Setup
        // Configure GoodsService.deleteById(...).
        final Goods goods = new Goods();
        goods.setId(0L);
        goods.setName("name");
        goods.setPrice(new BigDecimal("0.00"));
        goods.setImage("image");
        goods.setSellerId("sellerId");
        final Goods goods1 = new Goods();
        goods1.setId(1L);
        goods1.setName("name");
        goods1.setPrice(new BigDecimal("0.00"));
        goods1.setImage("image");
        goods1.setSellerId("sellerId");
        when(mockGoodsService.deleteById(goods)).thenReturn(goods);
        when(mockGoodsService.deleteById(goods1)).thenReturn(null);
        // Run the test
        mockMvc.perform(post("/goods/del")
                        .content(new ObjectMapper().writeValueAsString(goods)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));
        mockMvc.perform(post("/goods/del")
                        .content(new ObjectMapper().writeValueAsString(goods1)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(400));
    }

}
