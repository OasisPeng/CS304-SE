package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class dafenTest {

    private dafen dafenUnderTest;

    @BeforeEach
    void setUp() {
        dafenUnderTest = new dafen();
        dafenUnderTest.setCourseId(1);
        dafenUnderTest.setScore(80);
        dafenUnderTest.setUserId(100);

    }

    @Test
    void testGetCourseId() {
        // Test when courseId is set
        dafenUnderTest.setCourseId(1);
        assertThat(dafenUnderTest.getCourseId()).isEqualTo(1);

    }

    @Test
    void testGetScore() {
        // Test when score is set
        dafenUnderTest.setScore(80);
        assertThat(dafenUnderTest.getScore()).isEqualTo(80);

    }

    @Test
    void testGetUserId() {
        // Test when userId is set
        dafenUnderTest.setUserId(100);
        assertThat(dafenUnderTest.getUserId()).isEqualTo(100);
    }

    @Test
    void testEqualsAndHashCode() {
        dafen anotherdafen = new dafen();
        anotherdafen.setCourseId(1);
        anotherdafen.setScore(80);
        anotherdafen.setUserId(100);

        // Test when all attributes are equal
        assertThat(dafenUnderTest).isEqualTo(anotherdafen);
        assertThat(dafenUnderTest.hashCode()).isEqualTo(anotherdafen.hashCode());

        // Test when courseId is different
        anotherdafen.setCourseId(2);
        assertThat(dafenUnderTest).isNotEqualTo(anotherdafen);
        assertThat(dafenUnderTest.hashCode()).isNotEqualTo(anotherdafen.hashCode());

        // Test when score is different
        anotherdafen.setCourseId(1);
        anotherdafen.setScore(90);
        assertThat(dafenUnderTest).isNotEqualTo(anotherdafen);
        assertThat(dafenUnderTest.hashCode()).isNotEqualTo(anotherdafen.hashCode());

        // Test when userId is different
        anotherdafen.setScore(80);
        anotherdafen.setUserId(101);
        assertThat(dafenUnderTest).isNotEqualTo(anotherdafen);
        assertThat(dafenUnderTest.hashCode()).isNotEqualTo(anotherdafen.hashCode());
    }
}
