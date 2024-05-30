package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class pinglunTest {

    private pinglun pinglunUnderTest;
    private pinglun anotherpinglun;

    @BeforeEach
    void setUp() {
        pinglunUnderTest = new pinglun();
        pinglunUnderTest.setId(1);
        pinglunUnderTest.setCourseId(101);
        pinglunUnderTest.setUserId(1001);
        pinglunUnderTest.setContent("Great course!");

        anotherpinglun = new pinglun();
        anotherpinglun.setId(1);
        anotherpinglun.setCourseId(101);
        anotherpinglun.setUserId(1001);
        anotherpinglun.setContent("Great course!");
    }

    @Test
    void testpinglunAttributes() {
        assertThat(pinglunUnderTest.getId()).isEqualTo(1);
        assertThat(pinglunUnderTest.getCourseId()).isEqualTo(101);
        assertThat(pinglunUnderTest.getUserId()).isEqualTo(1001);
        assertThat(pinglunUnderTest.getContent()).isEqualTo("Great course!");
    }

    @Test
    void testEqualsAndHashCode() {
        // Test when all attributes are equal
        assertThat(pinglunUnderTest).isEqualTo(anotherpinglun);
        assertThat(pinglunUnderTest.hashCode()).isEqualTo(anotherpinglun.hashCode());

        // Test when Id is different
        anotherpinglun.setId(2);
        assertThat(pinglunUnderTest).isNotEqualTo(anotherpinglun);
        assertThat(pinglunUnderTest.hashCode()).isNotEqualTo(anotherpinglun.hashCode());

        // Reset Id and test when courseId is different
        anotherpinglun.setId(1);
        anotherpinglun.setCourseId(102);
        assertThat(pinglunUnderTest).isNotEqualTo(anotherpinglun);
        assertThat(pinglunUnderTest.hashCode()).isNotEqualTo(anotherpinglun.hashCode());

        // Reset courseId and test when userId is different
        anotherpinglun.setCourseId(101);
        anotherpinglun.setUserId(1002);
        assertThat(pinglunUnderTest).isNotEqualTo(anotherpinglun);
        assertThat(pinglunUnderTest.hashCode()).isNotEqualTo(anotherpinglun.hashCode());

        // Reset userId and test when content is different
        anotherpinglun.setUserId(1001);
        anotherpinglun.setContent("Good course");
        assertThat(pinglunUnderTest).isNotEqualTo(anotherpinglun);
        assertThat(pinglunUnderTest.hashCode()).isNotEqualTo(anotherpinglun.hashCode());
    }

    @Test
    void testToString() {
        String expectedToString = "pinglun(Id=1, courseId=101, userId=1001, content=Great course!)";
        assertThat(pinglunUnderTest.toString()).isEqualTo(expectedToString);
    }
}
