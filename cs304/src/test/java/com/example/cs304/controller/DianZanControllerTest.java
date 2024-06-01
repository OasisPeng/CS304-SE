package com.example.cs304.controller;

import com.alibaba.fastjson.JSON;
import com.example.cs304.common.Result;
import com.example.cs304.entity.dianzan;
import com.example.cs304.entity.dianzan;
import com.example.cs304.service.impl.DianzanServiceImpl;
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
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(username = "12112310", password = "as")
@RunWith(SpringRunner.class)
class DianZanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DianzanServiceImpl mockService;
    @Autowired
    private WebApplicationContext wac;
    private static String resultString;
    private static String listResultString;
    private static String failResultString;

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);
        List<dianzan> dianzans = List.of(dianzan);
        Result result = Result.suc(dianzan);
        Result fail = Result.fail();
        Result listResult = Result.suc(dianzans);
        try {
            resultString = objectMapper.writeValueAsString(result);
            listResultString = objectMapper.writeValueAsString(listResult);
            failResultString = objectMapper.writeValueAsString(fail);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testInsert() throws Exception {
        // Setup
        // Configure DianzanServiceImpl.insert(...).
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);
        final com.example.cs304.entity.dianzan dianzan1 = new dianzan();
        dianzan1.setCourseId(0);
        dianzan1.setUserId(0);
        when(mockService.insert(dianzan1)).thenReturn(dianzan);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/dianzan")
                        .content(JSON.toJSONString(dianzan1)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(resultString);
    }

    @Test
    void testInsert_DianzanServiceImplReturnsNull() throws Exception {
        // Setup
        // Configure DianzanServiceImpl.insert(...).
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);
        when(mockService.insert(dianzan)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/dianzan")
                        .content(JSON.toJSONString(dianzan)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        // Configure DianzanServiceImpl.deleteById(...).
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);
        final com.example.cs304.entity.dianzan dianzan1 = new dianzan();
        dianzan1.setCourseId(0);
        dianzan1.setUserId(0);
        when(mockService.deleteById(dianzan1)).thenReturn(dianzan);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/dianzan")
                        .content(JSON.toJSONString(dianzan1)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(resultString);
    }

    @Test
    void testDelete_DianzanServiceImplReturnsNull() throws Exception {
        // Setup
        // Configure DianzanServiceImpl.deleteById(...).
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);
        when(mockService.deleteById(dianzan)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/dianzan")
                        .content(JSON.toJSONString(dianzan)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testUpdateById() throws Exception {
        // Setup
        // Configure DianzanServiceImpl.updateById(...).
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);
        final com.example.cs304.entity.dianzan dianzan1 = new dianzan();
        dianzan1.setCourseId(0);
        dianzan1.setUserId(0);
        when(mockService.updateById(dianzan1)).thenReturn(dianzan);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/dianzan")
                        .content(JSON.toJSONString(dianzan1)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(resultString);
    }

    @Test
    void testUpdateById_DianzanServiceImplReturnsNull() throws Exception {
        // Setup
        // Configure DianzanServiceImpl.updateById(...).
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);
        when(mockService.updateById(dianzan)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/dianzan")
                        .content(JSON.toJSONString(dianzan)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testGetByCourseId() throws Exception {
        // Setup
        // Configure DianzanServiceImpl.selectByCourseId(...).
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);
        final List<com.example.cs304.entity.dianzan> dianzans = List.of(dianzan);
        when(mockService.selectByCourseId(0)).thenReturn(dianzans);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/dianzan/course/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(listResultString);
    }

    @Test
    void testGetByCourseId_DianzanServiceImplReturnsNull() throws Exception {
        // Setup
        when(mockService.selectByCourseId(0)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/dianzan/course/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testGetByCourseId_DianzanServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.selectByCourseId(0)).thenReturn(Collections.emptyList());
        Result result = Result.suc(Collections.emptyList());
        String st = objectMapper.writeValueAsString(result);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/dianzan/course/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(st);
    }

    @Test
    void testGetByUserId() throws Exception {
        // Setup
        // Configure DianzanServiceImpl.selectByUserId(...).
        final dianzan dianzan = new dianzan();
        dianzan.setCourseId(0);
        dianzan.setUserId(0);
        final List<com.example.cs304.entity.dianzan> dianzans = List.of(dianzan);
        when(mockService.selectByUserId(0)).thenReturn(dianzans);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/dianzan/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(listResultString);
    }

    @Test
    void testGetByUserId_DianzanServiceImplReturnsNull() throws Exception {
        // Setup
        when(mockService.selectByUserId(0)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/dianzan/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testGetByUserId_DianzanServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.selectByUserId(0)).thenReturn(Collections.emptyList());
        Result result = Result.suc(Collections.emptyList());
        String st = objectMapper.writeValueAsString(result);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/dianzan/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(st);
    }
}
