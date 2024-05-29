package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class dianzanTest {

    private dianzan dianzanUnderTest;

    @BeforeEach
    void setUp() {
        dianzanUnderTest = new dianzan();
        dianzanUnderTest.setCourseId(1);
        dianzanUnderTest.setUserId(100);
    }

    @Test
    void testGetCourseId() {
        // Test when courseId is set
        dianzanUnderTest.setCourseId(1);
        assertThat(dianzanUnderTest.getCourseId()).isEqualTo(1);

    }

    @Test
    void testGetUserId() {
        // Test when userId is set
        dianzanUnderTest.setUserId(100);
        assertThat(dianzanUnderTest.getUserId()).isEqualTo(100);

        // Test when userId is not set
        dianzanUnderTest.setUserId(null);
        assertThat(dianzanUnderTest.getUserId()).isNull();
    }

    @Test
    void testEqualsAndHashCode() {
        dianzan anotherdianzan = new dianzan();
        anotherdianzan.setCourseId(1);
        anotherdianzan.setUserId(100);

        // Test when all attributes are equal
        assertThat(dianzanUnderTest).isEqualTo(anotherdianzan);
        assertThat(dianzanUnderTest.hashCode()).isEqualTo(anotherdianzan.hashCode());

        // Test when courseId is different
        anotherdianzan.setCourseId(2);
        assertThat(dianzanUnderTest).isNotEqualTo(anotherdianzan);
        assertThat(dianzanUnderTest.hashCode()).isNotEqualTo(anotherdianzan.hashCode());

        // Test when userId is different
        anotherdianzan.setCourseId(1);
        anotherdianzan.setUserId(101);
        assertThat(dianzanUnderTest).isNotEqualTo(anotherdianzan);
        assertThat(dianzanUnderTest.hashCode()).isNotEqualTo(anotherdianzan.hashCode());
    }
}
