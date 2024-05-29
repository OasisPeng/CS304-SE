package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest {

    private Student studentUnderTest;

    @BeforeEach
    void setUp() {
        studentUnderTest = new Student();
    }

    @Test
    void testGetUsername() {
        // Test when username is set
        studentUnderTest.setUsername("testuser");
        assertThat(studentUnderTest.getUsername()).isEqualTo("testuser");

    }

    @Test
    void testGetPassword() {
        // Test when password is set
        studentUnderTest.setPassword("testpassword");
        assertThat(studentUnderTest.getPassword()).isEqualTo("testpassword");

    }

    @Test
    void testSetUsername() {
        // Test setting username
        studentUnderTest.setUsername("testuser");
        assertThat(studentUnderTest.getUsername()).isEqualTo("testuser");
    }

    @Test
    void testSetPassword() {
        // Test setting password
        studentUnderTest.setPassword("testpassword");
        assertThat(studentUnderTest.getPassword()).isEqualTo("testpassword");
    }
}
