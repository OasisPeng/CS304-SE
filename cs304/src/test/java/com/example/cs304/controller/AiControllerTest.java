package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.AiContent;
import com.example.cs304.entity.dafen;
import com.example.cs304.service.AiContentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
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
class AiControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @MockBean
    private AiContentService mockAiContentService;
    private static String resultString;
    private static String failResultString;
    private static String listResultString;

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        AiContent aiContent = new AiContent();
        aiContent.setUser(0);
        aiContent.setContent("content");
        aiContent.setId(0);
        List<AiContent> aiContents = List.of(aiContent);

        Result result = Result.suc(aiContent);
        Result failResult = Result.fail();
        Result list = Result.suc(aiContents);
        try {
            resultString = objectMapper.writeValueAsString(result);
            failResultString = objectMapper.writeValueAsString(failResult);
            listResultString = objectMapper.writeValueAsString(list);
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
    void testGetById() throws Exception {
        // Setup
        // Configure AiContentService.SelectById(...).
        final AiContent aiContent = new AiContent();
        aiContent.setUser(0);
        aiContent.setContent("content");
        aiContent.setId(0);
        when(mockAiContentService.SelectById(0)).thenReturn(aiContent);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/ai/id/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(resultString);
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup
        when(mockAiContentService.DeleteById(0)).thenReturn(0);
        String st = objectMapper.writeValueAsString( Result.suc(0));
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/ai/id/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(st);
    }

    @Test
    void testGetByUser() throws Exception {
        // Setup
        // Configure AiContentService.SelectByUserName(...).
        final AiContent aiContent = new AiContent();
        aiContent.setUser(0);
        aiContent.setContent("content");
        aiContent.setId(0);
        final List<AiContent> aiContents = List.of(aiContent);
        when(mockAiContentService.SelectByUserName(0)).thenReturn(aiContents);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/ai/user/{user}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(listResultString);
    }

    @Test
    void testGetByUser_AiContentServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockAiContentService.SelectByUserName(0)).thenReturn(Collections.emptyList());
        String st = objectMapper.writeValueAsString( Result.suc(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/ai/user/{user}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(st);
    }
}
