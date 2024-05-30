package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.HaveBuy;
import com.example.cs304.entity.Message;
import com.example.cs304.service.impl.HaveBuyServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(username = "12112310", password = "as")
@RunWith(SpringRunner.class)
class HaveBuyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HaveBuyServiceImpl mockService;
    private static String resultString;
    private static String listResultString;
    private static String failResultString;
    private static String entityString;
    private static String emptyString;
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        HaveBuy haveBuy = new HaveBuy();
        haveBuy.setUserId(0);
        haveBuy.setGoodId(0);
        List<HaveBuy> haveBuys = List.of(haveBuy);
        Result result = Result.suc(haveBuy);
        Result fail = Result.fail();
        Result listResult = Result.suc(haveBuys);
        try {
            entityString = objectMapper.writeValueAsString(haveBuy);
            resultString = objectMapper.writeValueAsString(result);
            listResultString = objectMapper.writeValueAsString(listResult);
            failResultString = objectMapper.writeValueAsString(fail);
            emptyString = objectMapper.writeValueAsString(Result.suc(Collections.emptyList()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testInsert() throws Exception {
        // Setup
        // Configure HaveBuyServiceImpl.insert(...).
        final HaveBuy haveBuy = new HaveBuy();
        haveBuy.setUserId(0);
        haveBuy.setGoodId(0);
        final HaveBuy haveBuy1 = new HaveBuy();
        haveBuy1.setUserId(0);
        haveBuy1.setGoodId(0);
        when(mockService.insert(haveBuy1)).thenReturn(haveBuy);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/haveBuy")
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(resultString);
    }

    @Test
    void testInsert_HaveBuyServiceImplReturnsNull() throws Exception {
        // Setup
        // Configure HaveBuyServiceImpl.insert(...).
        final HaveBuy haveBuy = new HaveBuy();
        haveBuy.setUserId(0);
        haveBuy.setGoodId(0);
        when(mockService.insert(haveBuy)).thenReturn(null);
        String st = objectMapper.writeValueAsString(Result.fail("不能重复添加"));
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/haveBuy")
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(st);
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        // Configure HaveBuyServiceImpl.delete(...).
        final HaveBuy haveBuy = new HaveBuy();
        haveBuy.setUserId(0);
        haveBuy.setGoodId(0);
        final HaveBuy haveBuy1 = new HaveBuy();
        haveBuy1.setUserId(0);
        haveBuy1.setGoodId(0);
        when(mockService.delete(haveBuy1)).thenReturn(haveBuy);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/haveBuy")
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(resultString);
    }

    @Test
    void testGetByUserId() throws Exception {
        // Setup
        // Configure HaveBuyServiceImpl.selectByUserId(...).
        final HaveBuy haveBuy = new HaveBuy();
        haveBuy.setUserId(0);
        haveBuy.setGoodId(0);
        final List<HaveBuy> haveBuys = List.of(haveBuy);
        when(mockService.selectByUserId(0)).thenReturn(haveBuys);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/haveBuy/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(listResultString);
    }

    @Test
    void testGetByUserId_HaveBuyServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.selectByUserId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/haveBuy/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(emptyString);
    }

    @Test
    void testGetByGoodId() throws Exception {
        // Setup
        // Configure HaveBuyServiceImpl.selectByGoodId(...).
        final HaveBuy haveBuy = new HaveBuy();
        haveBuy.setUserId(0);
        haveBuy.setGoodId(0);
        final List<HaveBuy> haveBuys = List.of(haveBuy);
        when(mockService.selectByGoodId(0)).thenReturn(haveBuys);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/haveBuy/good/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(listResultString);
    }

    @Test
    void testGetByGoodId_HaveBuyServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.selectByGoodId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/haveBuy/good/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(emptyString);
    }
}
