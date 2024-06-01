package com.example.cs304.controller;

import com.alibaba.fastjson.JSON;
import com.example.cs304.common.Result;
import com.example.cs304.entity.dafen;
//import com.example.cs304.filter.JwtAuthenticationTokenFilter;
import com.example.cs304.service.impl.DafenServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(username = "12112310", password = "as")
@RunWith(SpringRunner.class)
class DafenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DafenServiceImpl mockService;
//    @MockBean
//    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
//    @BeforeEach
//    void setup() throws ServletException, IOException {
//        doNothing().when(jwtAuthenticationTokenFilter).doFilter(any(), any(), any());
//    }
    @Autowired
    private WebApplicationContext wac;
    private static String resultString;
    private static String listResultString;
    private static String failResultString;

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);
        List<dafen> dafens = List.of(dafen);
        Result result = Result.suc(dafen);
        Result fail = Result.fail();
        Result listResult = Result.suc(dafens);
        try {
            resultString = objectMapper.writeValueAsString(result);
            listResultString = objectMapper.writeValueAsString(listResult);
            failResultString = objectMapper.writeValueAsString(fail);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @BeforeEach
    void setup() throws ServletException, IOException {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();

    }
    @Test
    void testInsert_DafenServiceImplReturnsNull() throws Exception {
        // Setup
        // Configure DafenServiceImpl.insert(...).
        final dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);
        when(mockService.insert(dafen)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/dafen")
                        .content(JSON.toJSONString(dafen)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        // Configure DafenServiceImpl.deleteById(...).
        final dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);
        final com.example.cs304.entity.dafen dafen1 = new dafen();
        dafen1.setCourseId(0);
        dafen1.setScore(0);
        dafen1.setUserId(0);
        when(mockService.deleteById(dafen1)).thenReturn(dafen);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/dafen")
                        .content(JSON.toJSONString(dafen1)).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(resultString);
    }

    @Test
    void testDelete_DafenServiceImplReturnsNull() throws Exception {
        // Setup
        // Configure DafenServiceImpl.deleteById(...).
        final dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);
        when(mockService.deleteById(dafen)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/dafen")
                        .content(JSON.toJSONString(dafen)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testUpdateById() throws Exception {
        // Setup
        // Configure DafenServiceImpl.updateById(...).
        final dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);
        when(mockService.updateById(dafen)).thenReturn(dafen);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/dafen")
                        .content(JSON.toJSONString(dafen)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(resultString);
    }

    @Test
    void testUpdateById_DafenServiceImplReturnsNull() throws Exception {
        // Setup
        // Configure DafenServiceImpl.updateById(...).
        final dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);
        when(mockService.updateById(dafen)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/dafen")
                        .content(JSON.toJSONString(dafen)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testGetByCourseId() throws Exception {
        // Setup
        // Configure DafenServiceImpl.selectByCourseId(...).
        final dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);
        final List<com.example.cs304.entity.dafen> dafens = List.of(dafen);
        when(mockService.selectByCourseId(0)).thenReturn(dafens);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/dafen/course/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(listResultString);
    }

    @Test
    void testGetByCourseId_DafenServiceImplReturnsNull() throws Exception {
        // Setup
        when(mockService.selectByCourseId(0)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/dafen/course/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testGetByCourseId_DafenServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.selectByCourseId(0)).thenReturn(Collections.emptyList());
        Result result = Result.suc(Collections.emptyList());
        String st = objectMapper.writeValueAsString(result);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/dafen/course/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(st);
    }

    @Test
    void testGetByUserId() throws Exception {
        // Setup
        // Configure DafenServiceImpl.selectByUserId(...).
        final dafen dafen = new dafen();
        dafen.setCourseId(0);
        dafen.setScore(0);
        dafen.setUserId(0);
        final List<com.example.cs304.entity.dafen> dafens = List.of(dafen);
        when(mockService.selectByUserId(0)).thenReturn(dafens);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/dafen/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(listResultString);
    }

    @Test
    void testGetByUserId_DafenServiceImplReturnsNull() throws Exception {
        // Setup
        when(mockService.selectByUserId(0)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/dafen/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testGetByUserId_DafenServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.selectByUserId(0)).thenReturn(Collections.emptyList());
        Result result = Result.suc(Collections.emptyList());
        String st = objectMapper.writeValueAsString(result);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/dafen/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(st);
    }
}
