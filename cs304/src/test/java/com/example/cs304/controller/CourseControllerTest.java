package com.example.cs304.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cs304.entity.Student;
import com.example.cs304.filter.JwtAuthenticationTokenFilter;
import com.example.cs304.service.ICourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICourseService mockCourseService;

    @Test
    @WithMockUser
    void testListPage() throws Exception {
        // Setup
        final HashMap<String, Object> param = new HashMap<>();
        param.put("courseCategory", "Math");
        param.put("trainingType", "Online");
        param.put("courseCode", "MATH101");
        param.put("department", "Science");
        param.put("teacher", "John Doe");
        param.put("pageSize", 10);
        param.put("pageNum", 1);
        when(mockCourseService.listPage(any(HashMap.class))).thenReturn(new Page());

        // Run the test
        mockMvc.perform(post("/course/listPage")
                        .content(new ObjectMapper().writeValueAsString(param)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    @WithMockUser
    void testQueryKCB() throws Exception {
        Student student = new Student();
        student.setUsername("111");
        student.setPassword("111");
        // Run the test
        mockMvc.perform(post("/course/queryCurrentCourse")
                        .content(new ObjectMapper().writeValueAsString(student)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(2011));
    }

}
