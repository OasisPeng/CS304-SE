package com.example.cs304.controller;

import com.alibaba.fastjson.JSON;
import com.example.cs304.common.Result;
import com.example.cs304.entity.Favourite;
import com.example.cs304.entity.Favourite;
import com.example.cs304.service.impl.FavouriteServiceImpl;
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
class FavouriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FavouriteServiceImpl mockService;
    @Autowired
    private WebApplicationContext wac;
    private static String resultString;
    private static String listResultString;
    private static String failResultString;

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        Favourite favourite = new Favourite();
        favourite.setUserId(0);
        favourite.setGoodId(0);
        List<Favourite> Favourites = List.of(favourite);
        Result result = Result.suc(favourite);
        Result fail = Result.fail();
        Result listResult = Result.suc(Favourites);
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
        // Configure FavouriteServiceImpl.insert(...).
        final Favourite favourite = new Favourite();
        favourite.setUserId(0);
        favourite.setGoodId(0);
        final Favourite favourite1 = new Favourite();
        favourite1.setUserId(0);
        favourite1.setGoodId(0);
        when(mockService.insert(favourite1)).thenReturn(favourite);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/favourite")
                        .content(JSON.toJSONString(favourite)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(resultString);
    }

    @Test
    void testInsert_FavouriteServiceImplReturnsNull() throws Exception {
        // Setup
        // Configure FavouriteServiceImpl.insert(...).
        final Favourite favourite = new Favourite();
        favourite.setUserId(0);
        favourite.setGoodId(0);
        when(mockService.insert(favourite)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/favourite")
                        .content(JSON.toJSONString(favourite)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(objectMapper.writeValueAsString(Result.fail("不能重复添加")));
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        // Configure FavouriteServiceImpl.delete(...).
        final Favourite favourite = new Favourite();
        favourite.setUserId(0);
        favourite.setGoodId(0);
        final Favourite favourite1 = new Favourite();
        favourite1.setUserId(0);
        favourite1.setGoodId(0);
        when(mockService.delete(favourite1)).thenReturn(favourite);
        String st = objectMapper.writeValueAsString(Result.fail("不能重复添加"));
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/favourite")
                        .content(JSON.toJSONString(favourite)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(resultString);
    }

    @Test
    void testGetByUserId() throws Exception {
        // Setup
        // Configure FavouriteServiceImpl.selectByUserId(...).
        final Favourite favourite = new Favourite();
        favourite.setUserId(0);
        favourite.setGoodId(0);
        final List<Favourite> favourites = List.of(favourite);
        when(mockService.selectByUserId(0)).thenReturn(favourites);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/favourite/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(listResultString);
    }

    @Test
    void testGetByUserId_FavouriteServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.selectByUserId(0)).thenReturn(Collections.emptyList());
        String st = objectMapper.writeValueAsString(Result.suc(Collections.emptyList()));
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/favourite/user/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(st);
    }

    @Test
    void testGetByGoodId() throws Exception {
        // Setup
        // Configure FavouriteServiceImpl.selectByGoodId(...).
        final Favourite favourite = new Favourite();
        favourite.setUserId(0);
        favourite.setGoodId(0);
        final List<Favourite> favourites = List.of(favourite);
        when(mockService.selectByGoodId(0)).thenReturn(favourites);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/favourite/good/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(listResultString);
    }

    @Test
    void testGetByGoodId_FavouriteServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.selectByGoodId(0)).thenReturn(Collections.emptyList());
        String st = objectMapper.writeValueAsString(Result.suc(Collections.emptyList()));
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/favourite/good/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(st);
    }
}
