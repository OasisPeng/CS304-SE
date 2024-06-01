package com.example.cs304.controller;

import com.example.cs304.entity.Event;
import com.example.cs304.filter.JwtAuthenticationTokenFilter;
import com.example.cs304.service.IEventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEventService mockEventService;

    @Test
    @WithMockUser
    void testSave() throws Exception {
        // Setup
        // Configure IEventService.save(...).
        // Create a sample Event object
        Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        event.setCategory("");
        event.setLevel("");
        event.setFinish(0);
        event.setEmotion("");
        event.setText("");

        // Mock the behavior of IEventService.save(...)
        when(mockEventService.save(any(Event.class))).thenReturn(event);

        // Run the test
        mockMvc.perform(post("/event/save")
                        .content(new ObjectMapper().writeValueAsString(event)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));

    }

    @Test
    @WithMockUser
    void testSave_IEventServiceReturnsNull() throws Exception {
        // Setup
        // Configure IEventService.save(...).
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        event.setCategory("");
        event.setLevel("");
        event.setFinish(0);
        event.setEmotion("");
        event.setText("");
        when(mockEventService.save(event)).thenReturn(null);

        // Run the test
        mockMvc.perform(post("/event/save")
                        .content(new ObjectMapper().writeValueAsString(event))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(400));
    }

    @Test
    @WithMockUser
    void testUpdate() throws Exception {
        // Setup
        // Configure IEventService.updateById(...).
        Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        event.setCategory("");
        event.setLevel("");
        event.setFinish(0);
        event.setEmotion("");
        event.setText("");

        // Mock the behavior of IEventService.save(...)
        when(mockEventService.updateById(any(Event.class))).thenReturn(event);

        // Run the test
        mockMvc.perform(post("/event/update")
                        .content(new ObjectMapper().writeValueAsString(event)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));
        // Verify the results
    }

    @Test
    @WithMockUser
    void testUpdate_IEventServiceReturnsNull() throws Exception {
        // Setup
        // Configure IEventService.updateById(...).
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        event.setCategory("");
        event.setLevel("");
        event.setFinish(0);
        event.setEmotion("");
        event.setText("");

        when(mockEventService.updateById(event)).thenReturn(null);

        // Run the test
        mockMvc.perform(post("/event/update")
                        .content(new ObjectMapper().writeValueAsString(event)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(400));

        // Verify the results
    }

    @Test
    @WithMockUser
    void testDel() throws Exception {
        // Setup
        // Configure IEventService.removeById(...).
        Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        event.setCategory("");
        event.setLevel("");
        event.setFinish(0);
        event.setEmotion("");
        event.setText("");

        // Mock the behavior of IEventService.save(...)
        when(mockEventService.removeById(any(Event.class))).thenReturn(event);

        // Run the test
        mockMvc.perform(post("/event/del")
                        .content(new ObjectMapper().writeValueAsString(event)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));

        // Verify the results
    }

    @Test
    @WithMockUser
    void testDel_IEventServiceReturnsNull() throws Exception {
        // Setup
        // Configure IEventService.removeById(...).
        Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        event.setCategory("");
        event.setLevel("");
        event.setFinish(0);
        event.setEmotion("");
        event.setText("");

        // Mock the behavior of IEventService.save(...)
        when(mockEventService.removeById(any(Event.class))).thenReturn(null);

        // Run the test
        mockMvc.perform(post("/event/del")
                        .content(new ObjectMapper().writeValueAsString(event)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(400));

        // Verify the results
    }

    @Test
    @WithMockUser
    void testQueryByWeek() throws Exception {
        // Setup
        // Configure IEventService.queryByWeekAndOwner(...).
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        event.setCategory("");
        event.setLevel("");
        event.setFinish(0);
        event.setEmotion("");
        event.setText("");

        final List<Event> events = List.of(event);
        when(mockEventService.queryByWeekAndOwner(1, "owner")).thenReturn(events);

        // Run the test
        mockMvc.perform(get("/event/queryByWeek")
                        .param("week", "1")
                        .param("owner", "owner")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));

        // Verify the results
    }

    @Test
    @WithMockUser
    void testQueryByWeek_OwnerIsNull() throws Exception {
        // Setup
        // Run the test
        mockMvc.perform(get("/event/queryByWeek")
                        .param("week", "0")
                        .param("owner", "")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(400));
    }

    @Test
    @WithMockUser
    void testQuery() throws Exception {
        // Setup
        // Configure IEventService.queryByDateAndOwner(...).
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        event.setCategory("");
        event.setLevel("");
        event.setFinish(0);
        event.setEmotion("");
        event.setText("");
        final List<Event> events = List.of(event);
        when(mockEventService.queryByDateAndOwner(any(int[].class), eq("owner"))).thenReturn(events);

        // Run the test
        mockMvc.perform(get("/event/query")
                        .param("date", "2024-01-01")
                        .param("owner", "owner")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));

        // Verify the results
    }

    @Test
    @WithMockUser
    void testQuery_OwnerIsNull() throws Exception {
        // Setup
        // Run the test
        mockMvc.perform(get("/event/query")
                        .param("date", "date")
                        .param("owner", "")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(400));
    }

    @Test
    @WithMockUser
    void testQueryByOwner() throws Exception {
        // Setup
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        event.setCategory("");
        event.setLevel("");
        event.setFinish(0);
        event.setEmotion("");
        event.setText("");
        final List<Event> events = List.of(event);
        when(mockEventService.queryByOwner(eq("owner"))).thenReturn(events);

        // Run the test
        mockMvc.perform(get("/event/queryByOwner")
                        .param("owner", "owner")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));

        // Verify the results
    }

    @Test
    @WithMockUser
    void testQueryByOwner_OwnerIsNull() throws Exception {
        // Setup

        // Run the test
        mockMvc.perform(get("/event/queryByOwner")
                        .param("owner", "")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(400));
    }

}
