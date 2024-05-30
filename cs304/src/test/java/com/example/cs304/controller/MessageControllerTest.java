package com.example.cs304.controller;

import com.example.cs304.common.Result;
import com.example.cs304.entity.Follow;
import com.example.cs304.entity.Message;
import com.example.cs304.service.impl.MessageServiceImpl;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(username = "12112310", password = "as")
@RunWith(SpringRunner.class)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageServiceImpl mockService;
    @Autowired
    private WebApplicationContext wac;
    private static String resultString;
    private static String listResultString;
    private static String failResultString;
    private static String entityString;
    private static String emptyString;
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        Message message = new Message();
        message.setId(0);
        message.setFrom(0);
        message.setTo(0);
        message.setText("text");
        message.setTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        List<Message> follows = List.of(message);
        Result result = Result.suc(message);
        Result fail = Result.fail();
        Result listResult = Result.suc(follows);
        try {
            entityString = objectMapper.writeValueAsString(message);
            resultString = objectMapper.writeValueAsString(result);
            listResultString = objectMapper.writeValueAsString(listResult);
            failResultString = objectMapper.writeValueAsString(fail);
            emptyString = objectMapper.writeValueAsString(Result.suc(Collections.emptyList()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testGetByTwoUser() throws Exception {
        // Setup
        // Configure MessageServiceImpl.SelectByTwoUser(...).
        final Message message = new Message();
        message.setId(0);
        message.setFrom(0);
        message.setTo(0);
        message.setText("text");
        message.setTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        final List<Message> messages = List.of(message);
        when(mockService.SelectByTwoUser(0, 0)).thenReturn(messages);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/message/getByTwoUser/{id1}/{id2}", 0, 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetByTwoUser_MessageServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockService.SelectByTwoUser(0, 0)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/message/getByTwoUser/{id1}/{id2}", 0, 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(emptyString);
    }

    @Test
    void testInsertList() throws Exception {
        // Setup
        // Configure MessageServiceImpl.InsertMessages(...).
        final Message message = new Message();
        message.setId(0);
        message.setFrom(0);
        message.setTo(0);
        message.setText("text");
        message.setTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        final List<Message> messages = List.of(message);
        when(mockService.InsertMessages(messages)).thenReturn(false);
        String st = objectMapper.writeValueAsString(messages);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/message/insertList")
                        .content(st).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(objectMapper.writeValueAsString(Result.suc(false)));
    }

    @Test
    void testInsertOne() throws Exception {
        // Setup
        // Configure MessageServiceImpl.InsertMessage(...).
        final Message message = new Message();
        message.setId(0);
        message.setFrom(0);
        message.setTo(0);
        message.setText("text");
        message.setTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        final Message message1 = new Message();
        message1.setId(0);
        message1.setFrom(0);
        message1.setTo(0);
        message1.setText("text");
        message1.setTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        when(mockService.InsertMessage(message1)).thenReturn(message);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/message/insertOne")
                        .content(entityString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
