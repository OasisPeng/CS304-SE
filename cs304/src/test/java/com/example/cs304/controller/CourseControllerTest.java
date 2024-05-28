//package com.example.cs304.controller;
//
//import com.example.cs304.service.ICourseService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(CourseController.class)
//class CourseControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ICourseService mockCourseService;
//
//    @Test
//    void testListPage() throws Exception {
//        // Setup
//        when(mockCourseService.listPage(new HashMap<>(Map.ofEntries()))).thenReturn(null);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/course/listPage")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testQueryKCB() throws Exception {
//        // Setup
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/course/queryCurrentCourse")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testQueryKCB_ThrowsIOException() throws Exception {
//        // Setup
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/course/queryCurrentCourse")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//
//    @Test
//    void testQueryKCB_ThrowsURISyntaxException() throws Exception {
//        // Setup
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(post("/course/queryCurrentCourse")
//                        .content("content").contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
//    }
//}
