package com.example.cs304.controller;
import com.example.cs304.filter.JwtAuthenticationTokenFilter;
import com.example.cs304.service.ICourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICourseService mockCourseService;

    @MockBean
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Test
    void testListPage() throws Exception {
        // Setup
        when(mockCourseService.listPage(any())).thenReturn(null);
        doNothing().when(jwtAuthenticationTokenFilter).doFilter(any(), any(), any());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/course/listPage")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testQueryKCB() throws Exception {
        // Setup
        doNothing().when(jwtAuthenticationTokenFilter).doFilter(any(), any(), any());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/course/queryCurrentCourse")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
