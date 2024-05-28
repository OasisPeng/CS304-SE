package com.example.cs304.controller;

import com.example.cs304.entity.Event;
import com.example.cs304.service.IEventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventControllerTest {
    
    private MockMvc mockMvc;

    @MockBean
    private IEventService mockEventService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mockEventService).build();
    }
    @Test
    void testSave() throws Exception {
        // Setup
        // Configure IEventService.save(...).
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        final Event event1 = new Event();
        event1.setId(0);
        event1.setTitle("title");
        event1.setOwner("owner");
        event1.setWeek(1);
        event1.setXq(0);
        when(mockEventService.save(event1)).thenReturn(event);

        // Run the test
        final MockHttpServletResponse response = this.mockMvc.perform(post("/event/save")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testSave_IEventServiceReturnsNull() throws Exception {
        // Setup
        // Configure IEventService.save(...).
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        when(mockEventService.save(event)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = this.mockMvc.perform(post("/event/save")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        // Configure IEventService.updateById(...).
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        final Event event1 = new Event();
        event1.setId(0);
        event1.setTitle("title");
        event1.setOwner("owner");
        event1.setWeek(1);
        event1.setXq(0);
        when(mockEventService.updateById(event1)).thenReturn(event);

        // Run the test
        final MockHttpServletResponse response = this.mockMvc.perform(post("/event/update")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testUpdate_IEventServiceReturnsNull() throws Exception {
        // Setup
        // Configure IEventService.updateById(...).
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        when(mockEventService.updateById(event)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = this.mockMvc.perform(post("/event/update")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDel() throws Exception {
        // Setup
        // Configure IEventService.removeById(...).
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        final Event event1 = new Event();
        event1.setId(0);
        event1.setTitle("title");
        event1.setOwner("owner");
        event1.setWeek(1);
        event1.setXq(0);
        when(mockEventService.removeById(event1)).thenReturn(event);

        // Run the test
        final MockHttpServletResponse response = this.mockMvc.perform(post("/event/del")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDel_IEventServiceReturnsNull() throws Exception {
        // Setup
        // Configure IEventService.removeById(...).
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        when(mockEventService.removeById(event)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = this.mockMvc.perform(post("/event/del")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testQueryByWeek() throws Exception {
        // Setup
        // Configure IEventService.queryByWeekAndOwner(...).
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        final List<Event> events = List.of(event);
        when(mockEventService.queryByWeekAndOwner(1, "owner")).thenReturn(events);

        // Run the test
        final MockHttpServletResponse response = this.mockMvc.perform(get("/event/queryByWeek")
                        .param("week", "0")
                        .param("owner", "owner")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testQueryByWeek_IEventServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockEventService.queryByWeekAndOwner(1, "owner")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = this.mockMvc.perform(get("/event/queryByWeek")
                        .param("week", "0")
                        .param("owner", "owner")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testQuery() throws Exception {
        // Setup
        // Configure IEventService.queryByDateAndOwner(...).
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        final List<Event> events = List.of(event);
        when(mockEventService.queryByDateAndOwner(any(int[].class), eq("owner"))).thenReturn(events);

        // Run the test
        final MockHttpServletResponse response = this.mockMvc.perform(get("/event/query")
                        .param("date", "date")
                        .param("owner", "owner")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testQuery_IEventServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockEventService.queryByDateAndOwner(any(int[].class), eq("owner"))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = this.mockMvc.perform(get("/event/query")
                        .param("date", "date")
                        .param("owner", "owner")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
