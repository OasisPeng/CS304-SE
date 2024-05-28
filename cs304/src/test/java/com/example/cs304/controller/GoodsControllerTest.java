//package com.example.cs304.controller;
//
//import com.example.cs304.common.AliOssUtil;
//import com.example.cs304.entity.Goods;
//import com.example.cs304.service.GoodsService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(GoodsController.class)
//class GoodsControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AliOssUtil mockUtil;
//    @MockBean
//    private GoodsService mockGoodsService;
//
//    @Test
//    void testUpload() throws Exception {
//        // Setup
//        when(mockUtil.upload(any(byte[].class), eq("objectName"))).thenReturn("result");
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(multipart("/goods/upload")
//                        .file(new MockMultipartFile("file", "originalFilename", MediaType.APPLICATION_JSON_VALUE,
//                                "content".getBytes()))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testGetGoodsByCategory() throws Exception {
//        // Setup
//        // Configure GoodsService.findByCategory(...).
//        final Goods goods1 = new Goods();
//        goods1.setId(0L);
//        goods1.setName("name");
//        goods1.setPrice(new BigDecimal("0.00"));
//        goods1.setImage("image");
//        goods1.setSellerId("sellerId");
//        final List<Goods> goods = List.of(goods1);
//        when(mockGoodsService.findByCategory("category")).thenReturn(goods);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/goods/category/{category}", "category")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testGetGoodsByCategory_GoodsServiceReturnsNoItems() throws Exception {
//        // Setup
//        when(mockGoodsService.findByCategory("category")).thenReturn(Collections.emptyList());
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/goods/category/{category}", "category")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testGetGoodsBySellerId() throws Exception {
//        // Setup
//        // Configure GoodsService.findBySellerId(...).
//        final Goods goods1 = new Goods();
//        goods1.setId(0L);
//        goods1.setName("name");
//        goods1.setPrice(new BigDecimal("0.00"));
//        goods1.setImage("image");
//        goods1.setSellerId("sellerId");
//        final List<Goods> goods = List.of(goods1);
//        when(mockGoodsService.findBySellerId("sellerId")).thenReturn(goods);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/goods/seller/{sellerId}", "sellerId")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testGetGoodsBySellerId_GoodsServiceReturnsNoItems() throws Exception {
//        // Setup
//        when(mockGoodsService.findBySellerId("sellerId")).thenReturn(Collections.emptyList());
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/goods/seller/{sellerId}", "sellerId")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testGetGoodsByBuyerId() throws Exception {
//        // Setup
//        // Configure GoodsService.findByBuyerId(...).
//        final Goods goods1 = new Goods();
//        goods1.setId(0L);
//        goods1.setName("name");
//        goods1.setPrice(new BigDecimal("0.00"));
//        goods1.setImage("image");
//        goods1.setSellerId("sellerId");
//        final List<Goods> goods = List.of(goods1);
//        when(mockGoodsService.findByBuyerId("buyerId")).thenReturn(goods);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/goods/buyer/{buyerId}", "buyerId")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testGetGoodsByBuyerId_GoodsServiceReturnsNoItems() throws Exception {
//        // Setup
//        when(mockGoodsService.findByBuyerId("buyerId")).thenReturn(Collections.emptyList());
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/goods/buyer/{buyerId}", "buyerId")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testGetGoodsById() throws Exception {
//        // Setup
//        // Configure GoodsService.findById(...).
//        final Goods goods = new Goods();
//        goods.setId(0L);
//        goods.setName("name");
//        goods.setPrice(new BigDecimal("0.00"));
//        goods.setImage("image");
//        goods.setSellerId("sellerId");
//        when(mockGoodsService.findById(0L)).thenReturn(goods);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/goods/{id}", 0)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testSearchGoods() throws Exception {
//        // Setup
//        // Configure GoodsService.searchByKeyword(...).
//        final Goods goods1 = new Goods();
//        goods1.setId(0L);
//        goods1.setName("name");
//        goods1.setPrice(new BigDecimal("0.00"));
//        goods1.setImage("image");
//        goods1.setSellerId("sellerId");
//        final List<Goods> goods = List.of(goods1);
//        when(mockGoodsService.searchByKeyword("keyword")).thenReturn(goods);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/goods/search")
//                        .param("keyword", "keyword")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testSearchGoods_GoodsServiceReturnsNoItems() throws Exception {
//        // Setup
//        when(mockGoodsService.searchByKeyword("keyword")).thenReturn(Collections.emptyList());
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("/goods/search")
//                        .param("keyword", "keyword")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testAddGoods() throws Exception {
//        // Setup
//        // Configure GoodsService.insert(...).
//        final Goods goods = new Goods();
//        goods.setId(0L);
//        goods.setName("name");
//        goods.setPrice(new BigDecimal("0.00"));
//        goods.setImage("image");
//        goods.setSellerId("sellerId");
//        final Goods goods1 = new Goods();
//        goods1.setId(0L);
//        goods1.setName("name");
//        goods1.setPrice(new BigDecimal("0.00"));
//        goods1.setImage("image");
//        goods1.setSellerId("sellerId");
//        when(mockGoodsService.insert(goods1)).thenReturn(goods);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/goods/add")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testAddGoods_GoodsServiceReturnsNull() throws Exception {
//        // Setup
//        // Configure GoodsService.insert(...).
//        final Goods goods = new Goods();
//        goods.setId(0L);
//        goods.setName("name");
//        goods.setPrice(new BigDecimal("0.00"));
//        goods.setImage("image");
//        goods.setSellerId("sellerId");
//        when(mockGoodsService.insert(goods)).thenReturn(null);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/goods/add")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testUpdateGoods() throws Exception {
//        // Setup
//        // Configure GoodsService.updateGoods(...).
//        final Goods goods = new Goods();
//        goods.setId(0L);
//        goods.setName("name");
//        goods.setPrice(new BigDecimal("0.00"));
//        goods.setImage("image");
//        goods.setSellerId("sellerId");
//        final Goods goods1 = new Goods();
//        goods1.setId(0L);
//        goods1.setName("name");
//        goods1.setPrice(new BigDecimal("0.00"));
//        goods1.setImage("image");
//        goods1.setSellerId("sellerId");
//        when(mockGoodsService.updateGoods(goods1)).thenReturn(goods);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/goods/update")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testUpdateGoods_GoodsServiceReturnsNull() throws Exception {
//        // Setup
//        // Configure GoodsService.updateGoods(...).
//        final Goods goods = new Goods();
//        goods.setId(0L);
//        goods.setName("name");
//        goods.setPrice(new BigDecimal("0.00"));
//        goods.setImage("image");
//        goods.setSellerId("sellerId");
//        when(mockGoodsService.updateGoods(goods)).thenReturn(null);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/goods/update")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testDeleteGoods() throws Exception {
//        // Setup
//        // Configure GoodsService.deleteById(...).
//        final Goods goods = new Goods();
//        goods.setId(0L);
//        goods.setName("name");
//        goods.setPrice(new BigDecimal("0.00"));
//        goods.setImage("image");
//        goods.setSellerId("sellerId");
//        final Goods goods1 = new Goods();
//        goods1.setId(0L);
//        goods1.setName("name");
//        goods1.setPrice(new BigDecimal("0.00"));
//        goods1.setImage("image");
//        goods1.setSellerId("sellerId");
//        when(mockGoodsService.deleteById(goods1)).thenReturn(goods);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/goods/del")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testDeleteGoods_GoodsServiceReturnsNull() throws Exception {
//        // Setup
//        // Configure GoodsService.deleteById(...).
//        final Goods goods = new Goods();
//        goods.setId(0L);
//        goods.setName("name");
//        goods.setPrice(new BigDecimal("0.00"));
//        goods.setImage("image");
//        goods.setSellerId("sellerId");
//        when(mockGoodsService.deleteById(goods)).thenReturn(null);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/goods/del")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//}
