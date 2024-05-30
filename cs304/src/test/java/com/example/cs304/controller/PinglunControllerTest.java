package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.HaveBuy;
import com.example.cs304.entity.pinglun;
import com.example.cs304.service.impl.HaveBuyServiceImpl;
import com.example.cs304.service.impl.PinglunServiceImpl;
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
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(username = "12112310", password = "as")
@RunWith(SpringRunner.class)
class PinglunControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PinglunServiceImpl mockService;
     static String resultString;
    private static String listResultString;
    private static String failResultString;
    private static String entityString;
    private static String emptyString;
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        List<pinglun> haveBuys = List.of(pinglun);
        Result result = Result.suc(pinglun);
        Result fail = Result.fail();
        Result listResult = Result.suc(haveBuys);
        try {
            entityString = objectMapper.writeValueAsString(pinglun);
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
        // Configure PinglunServiceImpl.insert(...).
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");
        final com.example.cs304.entity.pinglun pinglun1 = new pinglun();
        pinglun1.setId(0);
        pinglun1.setCourseId(0);
        pinglun1.setUserId(0);
        pinglun1.setContent("content");
        when(mockService.insert(pinglun1)).thenReturn(pinglun);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/pinglun")
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testInsert_PinglunServiceImplReturnsNull() throws Exception {
        // Setup
        // Configure PinglunServiceImpl.insert(...).
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");
        when(mockService.insert(pinglun)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/pinglun")
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        // Configure PinglunServiceImpl.deleteById(...).
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");
        final com.example.cs304.entity.pinglun pinglun1 = new pinglun();
        pinglun1.setId(0);
        pinglun1.setCourseId(0);
        pinglun1.setUserId(0);
        pinglun1.setContent("content");
        when(mockService.deleteById(pinglun1)).thenReturn(pinglun);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/pinglun")
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testDelete_PinglunServiceImplReturnsNull() throws Exception {
        // Setup
        // Configure PinglunServiceImpl.deleteById(...).
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");
        when(mockService.deleteById(pinglun)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/pinglun")
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testUpdateById() throws Exception {
        // Setup
        // Configure PinglunServiceImpl.updateById(...).
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");
        final com.example.cs304.entity.pinglun pinglun1 = new pinglun();
        pinglun1.setId(0);
        pinglun1.setCourseId(0);
        pinglun1.setUserId(0);
        pinglun1.setContent("content");
        when(mockService.updateById(pinglun1)).thenReturn(pinglun);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/pinglun")
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testUpdateById_PinglunServiceImplReturnsNull() throws Exception {
        // Setup
        // Configure PinglunServiceImpl.updateById(...).
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");
        when(mockService.updateById(pinglun)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/pinglun")
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testGetByCourseId() throws Exception {
        // Setup
        // Configure PinglunServiceImpl.selectByCourseId(...).
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");
        final List<com.example.cs304.entity.pinglun> pingluns = List.of(pinglun);
        when(mockService.selectByCourseId(0)).thenReturn(pingluns);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/pinglun/course/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetByCourseId_PinglunServiceImplReturnsNull() throws Exception {
        // Setup
        when(mockService.selectByCourseId(0)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/pinglun/course/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testGetByCourseId_PinglunServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.selectByCourseId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/pinglun/course/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(emptyString);
    }

    @Test
    void testGetByUserId() throws Exception {
        // Setup
        // Configure PinglunServiceImpl.selectByUserId(...).
        final pinglun pinglun = new pinglun();
        pinglun.setId(0);
        pinglun.setCourseId(0);
        pinglun.setUserId(0);
        pinglun.setContent("content");
        final List<com.example.cs304.entity.pinglun> pingluns = List.of(pinglun);
        when(mockService.selectByUserId(0)).thenReturn(pingluns);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/pinglun/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetByUserId_PinglunServiceImplReturnsNull() throws Exception {
        // Setup
        when(mockService.selectByUserId(0)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/pinglun/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(failResultString);
    }

    @Test
    void testGetByUserId_PinglunServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.selectByUserId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/pinglun/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(emptyString);
    }
}
