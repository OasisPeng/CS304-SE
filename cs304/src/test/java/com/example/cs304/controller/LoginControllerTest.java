package com.example.cs304.controller;

import com.example.cs304.common.JwtUtil;
import com.example.cs304.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtil mockUtil;

    @Test
    @WithMockUser
    void testLogin() throws Exception {
        Student student= new Student();
        student.setUsername("111");
        student.setPassword("111");
        // Setup
        when(mockUtil.createJWT(36000000L, Map.ofEntries(Map.entry("value", "value")))).thenReturn("result");

        // Run the test
        mockMvc.perform(post("/login/login")
                        .content(new ObjectMapper().writeValueAsString(student)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(400));

    }

    @Test
    @WithMockUser
    void testLogout() throws Exception {
        // Setup
        // Run the test
        mockMvc.perform(get("/login/logout/{username}", "111")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(400));
    }
}
