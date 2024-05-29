package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CourseForTimetableTest {
    private CourseForTimetable course;

    @BeforeEach
    public void setUp() {
        course = new CourseForTimetable();
        course.setTeacher("Dr. Smith");
        course.setEnglishName("Computer Science");
        course.setChineseName("计算机科学");
        course.setTeachingBuilding("Main Building");
        course.setXq(3);
        course.setWeeks("1-16");
        course.setJc("1-2");
        course.setColor(5);
        course.setStartTime("08:00");
        course.setEndTime("09:50");
        course.setClasses("CS304");
        course.setLanguage("English");
    }

    @Test
    public void testCourseForTimetableAttributes() {
        assertThat(course.getTeacher()).isEqualTo("Dr. Smith");
        assertThat(course.getEnglishName()).isEqualTo("Computer Science");
        assertThat(course.getChineseName()).isEqualTo("计算机科学");
        assertThat(course.getTeachingBuilding()).isEqualTo("Main Building");
        assertThat(course.getXq()).isEqualTo(3);
        assertThat(course.getWeeks()).isEqualTo("1-16");
        assertThat(course.getJc()).isEqualTo("1-2");
        assertThat(course.getColor()).isEqualTo(5);
        assertThat(course.getStartTime()).isEqualTo("08:00");
        assertThat(course.getEndTime()).isEqualTo("09:50");
        assertThat(course.getClasses()).isEqualTo("CS304");
        assertThat(course.getLanguage()).isEqualTo("English");
    }

    @Test
    public void testCourseForTimetableEquals() {
        CourseForTimetable anotherCourse = new CourseForTimetable();
        anotherCourse.setTeacher("Dr. Smith");
        anotherCourse.setEnglishName("Computer Science");
        anotherCourse.setChineseName("计算机科学");
        anotherCourse.setTeachingBuilding("Main Building");
        anotherCourse.setXq(3);
        anotherCourse.setWeeks("1-16");
        anotherCourse.setJc("1-2");
        anotherCourse.setColor(5);
        anotherCourse.setStartTime("08:00");
        anotherCourse.setEndTime("09:50");
        anotherCourse.setClasses("CS304");
        anotherCourse.setLanguage("English");

        assertThat(course).isEqualTo(anotherCourse);
        assertThat(course.equals(anotherCourse)).isTrue();
    }

    @Test
    public void testCourseForTimetableHashCode() {
        CourseForTimetable anotherCourse = new CourseForTimetable();
        anotherCourse.setTeacher("Dr. Smith");
        anotherCourse.setEnglishName("Computer Science");
        anotherCourse.setChineseName("计算机科学");
        anotherCourse.setTeachingBuilding("Main Building");
        anotherCourse.setXq(3);
        anotherCourse.setWeeks("1-16");
        anotherCourse.setJc("1-2");
        anotherCourse.setColor(5);
        anotherCourse.setStartTime("08:00");
        anotherCourse.setEndTime("09:50");
        anotherCourse.setClasses("CS304");
        anotherCourse.setLanguage("English");

        assertThat(course.hashCode()).isEqualTo(anotherCourse.hashCode());
    }
}
