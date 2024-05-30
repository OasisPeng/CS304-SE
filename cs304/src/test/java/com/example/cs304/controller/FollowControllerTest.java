package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.Follow;
import com.example.cs304.entity.Follow;
import com.example.cs304.service.impl.FollowServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.C;
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
class FollowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FollowServiceImpl mockService;
    @Autowired
    private WebApplicationContext wac;
    private static String resultString;
    private static String listResultString;
    private static String failResultString;
    private static String entityString;
    private static String emptyString;
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        Follow follow = new Follow();
        follow.setFollowing(0);
        follow.setFollower(0);
        List<Follow> follows = List.of(follow);
        Result result = Result.suc(follow);
        Result fail = Result.fail();
        Result listResult = Result.suc(follows);
        try {
            entityString = objectMapper.writeValueAsString(follow);
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
        // Configure FollowServiceImpl.insert(...).
        final Follow follow = new Follow();
        follow.setFollowing(0);
        follow.setFollower(0);
        when(mockService.insert(follow)).thenReturn(false);
        String st = objectMapper.writeValueAsString(Result.fail("关注失败,不能重复关注"));
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/follow")
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
        // Configure FollowServiceImpl.delete(...).
        final Follow follow = new Follow();
        follow.setFollowing(0);
        follow.setFollower(0);
        when(mockService.delete(follow)).thenReturn(0);
        String st = objectMapper.writeValueAsString(Result.fail("取消关注失败，请重试"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/follow")
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(st);
    }

    @Test
    void testGetFromFollower() throws Exception {
        // Setup
        // Configure FollowServiceImpl.selectByFollower(...).
        final Follow follow = new Follow();
        follow.setFollowing(0);
        follow.setFollower(0);
        final List<Follow> list = List.of(follow);
        when(mockService.selectByFollower(0)).thenReturn(list);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/follow/follower/{id}", 0)
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(listResultString);
    }

    @Test
    void testGetFromFollower_FollowServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.selectByFollower(0)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/follow/follower/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(emptyString);
    }

    @Test
    void testGetFromFollowing() throws Exception {
        // Setup
        // Configure FollowServiceImpl.selectByFollowing(...).
        final Follow follow = new Follow();
        follow.setFollowing(0);
        follow.setFollower(0);
        final List<Follow> list = List.of(follow);
        when(mockService.selectByFollowing(0)).thenReturn(list);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/follow/following/{id}", 0)
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(listResultString);
    }

    @Test
    void testGetFromFollowing_FollowServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.selectByFollowing(0)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/follow/following/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(emptyString);
    }
}
