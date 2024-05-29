package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {
    private Course course;
    private Course anotherCourse;

    @BeforeEach
    public void setUp() {
        course = new Course();
        course.setId(1);
        course.setTrainingType("Technical");
        course.setCourseCode("CS304");
        course.setChineseName("计算机科学");
        course.setEnglishName("Computer Science");
        course.setCourseNature("Required");
        course.setCourseCategory("Major");
        course.setLanguage("English");
        course.setCredits(3.0);
        course.setHours(48.0);
        course.setDepartment("Computer Science Department");
        course.setTeacher("Dr. Smith");

        anotherCourse = new Course();
        anotherCourse.setId(1);
        anotherCourse.setTrainingType("Technical");
        anotherCourse.setCourseCode("CS304");
        anotherCourse.setChineseName("计算机科学");
        anotherCourse.setEnglishName("Computer Science");
        anotherCourse.setCourseNature("Required");
        anotherCourse.setCourseCategory("Major");
        anotherCourse.setLanguage("English");
        anotherCourse.setCredits(3.0);
        anotherCourse.setHours(48.0);
        anotherCourse.setDepartment("Computer Science Department");
        anotherCourse.setTeacher("Dr. Smith");
    }

    @Test
    public void testCourseAttributes() {
        assertThat(course.getId()).isEqualTo(1);
        assertThat(course.getTrainingType()).isEqualTo("Technical");
        assertThat(course.getCourseCode()).isEqualTo("CS304");
        assertThat(course.getChineseName()).isEqualTo("计算机科学");
        assertThat(course.getEnglishName()).isEqualTo("Computer Science");
        assertThat(course.getCourseNature()).isEqualTo("Required");
        assertThat(course.getCourseCategory()).isEqualTo("Major");
        assertThat(course.getLanguage()).isEqualTo("English");
        assertThat(course.getCredits()).isEqualTo(3.0);
        assertThat(course.getHours()).isEqualTo(48.0);
        assertThat(course.getDepartment()).isEqualTo("Computer Science Department");
        assertThat(course.getTeacher()).isEqualTo("Dr. Smith");
    }

    @Test
    public void testCourseToString() {
        String toString = course.toString();
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("trainingType=Technical");
        assertThat(toString).contains("courseCode=CS304");
        assertThat(toString).contains("chineseName=计算机科学");
        assertThat(toString).contains("englishName=Computer Science");
        assertThat(toString).contains("courseNature=Required");
        assertThat(toString).contains("courseCategory=Major");
        assertThat(toString).contains("language=English");
        assertThat(toString).contains("credits=3.0");
        assertThat(toString).contains("hours=48.0");
        assertThat(toString).contains("department=Computer Science Department");
        assertThat(toString).contains("teacher=Dr. Smith");
    }

    @Test
    public void testCourseEquals() {
        assertThat(course).isEqualTo(anotherCourse);
        assertThat(course.equals(anotherCourse)).isTrue();

        anotherCourse.setId(2);
        assertThat(course).isNotEqualTo(anotherCourse);
    }

    @Test
    public void testCourseHashCode() {
        assertThat(course.hashCode()).isEqualTo(anotherCourse.hashCode());

        anotherCourse.setId(2);
        assertThat(course.hashCode()).isNotEqualTo(anotherCourse.hashCode());
    }
}
