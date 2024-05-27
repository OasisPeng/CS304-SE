//package com.example.cs304.controller;
//
//import com.example.cs304.common.Code;
//import com.example.cs304.common.Result;
//import com.example.cs304.common.Util;
//import com.example.cs304.entity.CourseForTimetable;
//import com.example.cs304.entity.Student;
//import com.example.cs304.service.ICourseService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class CourseControllerTest {
//
//    @Mock
//    private ICourseService courseService;
//
//    @InjectMocks
//    private CourseController courseController;
//
//    public CourseControllerTest() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void listPageTest() {
//        HashMap<String, Object> param = new HashMap<>();
//        when(courseService.listPage(param)).thenReturn(null);
//
//        Result result = courseController.listPage(param);
//
//        assertEquals(Result.suc(null), result);
//        verify(courseService, times(1)).listPage(param);
//    }
//
//    @Test
//    void queryKCBTest() throws IOException, URISyntaxException {
//        Student student = new Student();
//        student.setUsername("testUsername");
//        student.setPassword("testPassword");
//
//        ArrayList<CourseForTimetable> expectedCoursesInfo = new ArrayList<>();
//        // Assuming you have some mock data for courses info
//
//        // Mocking the CAS login and course info retrieval
//        when(Util.casLogin(student.getUsername(), student.getPassword())).thenReturn(new String[]{"cookie1", "cookie2"});
//        when(Util.getCourInfo("cookie1", "cookie2")).thenReturn("rawCourseInfo");
//        when(Util.getCourses("rawCourseInfo")).thenReturn(expectedCoursesInfo);
//
//        Result result = courseController.queryKCB(student);
//
//        assertEquals(new Result(Code.GET_OK, "查询成功", expectedCoursesInfo), result);
//        verify(Util, times(1)).casLogin(student.getUsername(), student.getPassword());
//        verify(Util, times(1)).getCourInfo("cookie1", "cookie2");
//        verify(Util, times(1)).getCourses("rawCourseInfo");
//    }
//}
