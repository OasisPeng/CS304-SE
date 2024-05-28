package com.example.cs304.common;

import com.example.cs304.entity.CourseForTimetable;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UtilTest {

    @Test
    void testTimeConvert() {
        Map<Integer, String> timeConvert = new HashMap<>();
        timeConvert.put(1, "08:00");
        String time = "[1-2节][3-4节]";
        int index = 0;
        String[] result = Util.TimeConvert(time, index);
        assertThat(result).containsExactly("8:00", "9:50");
    }

    @Test
    void testGetCourses() {
        // Setup
        final CourseForTimetable courseForTimetable = new CourseForTimetable();
        courseForTimetable.setTeacher("[陶伊达]");
        courseForTimetable.setEnglishName("[Software Engineering-001]");
        courseForTimetable.setChineseName("[软件工程]");
        courseForTimetable.setTeachingBuilding("[一科报告厅]");
        courseForTimetable.setXq(1);
        courseForTimetable.setWeeks("[1-16周]");
        courseForTimetable.setJc("[5-6节]");
        courseForTimetable.setColor(2);
        courseForTimetable.setStartTime("14:00");
        courseForTimetable.setEndTime("15:50");
        courseForTimetable.setClasses("[01班]");
        courseForTimetable.setLanguage("[英文]");
        final ArrayList<CourseForTimetable> expectedResult = new ArrayList<>(List.of(courseForTimetable));

        // Run the test
        final ArrayList<CourseForTimetable> result = Util.getCourses("[{\"KCWZSM\":null,\"RWH\":\"2023-2024-2-CS304-001\",\"SFFXEXW\":null,\"FILEURL\":null,\"SKSJ\":\"软件工程\\n[陶伊达]\\n[软件工程-01班-英文]\\n[1-16周][一科报告厅][5-6节]\",\"XB\":2,\"SKSJ_EN\":\"软件工程\\n[Tao Yida]\\n[Software Engineering-001]\\n[1-16周][一科报告厅][5-6节]\",\"KEY\":\"xq1_jc3\"}]");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCourInfo() throws Exception {
        assertThat(Util.getCourInfo("route", "js")).isEqualTo("{\"content\":\"session已失效\"}");
    }

    @Test
    void testCasLogin() throws Exception {
        assertThat(Util.casLogin("password", "password")).isEqualTo(new String[]{"", ""});
    }

    @Test
    void testRelease() throws Exception {
        // Setup
        final CloseableHttpResponse httpResponse = null;
        final CloseableHttpClient httpClient = null;

        // Run the test
        Util.release(httpResponse, httpClient);

        // Verify the results
    }

    @Test
    void testCalculateWeek() {
        assertThat(Util.calculateWeek(LocalDate.of(2024, 1, 1))).isEqualTo(new int[]{1,1});
    }

    @Test
    void testGetCalculateDate() {
        assertThat(Util.getCalculateDate("2024-01-01")).isEqualTo(new int[]{1,1});
    }

    @Test
    void testLoginVerify() {
        assertThat(Util.loginVerify("password", "password")).isFalse();
    }
}
