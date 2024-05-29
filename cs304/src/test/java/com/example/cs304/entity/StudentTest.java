package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest {

    private Student studentUnderTest;
    private Student anotherStudent;

    @BeforeEach
    void setUp() {
        studentUnderTest = new Student();
        studentUnderTest.setUsername("john_doe");
        studentUnderTest.setPassword("password123");

        anotherStudent = new Student();
        anotherStudent.setUsername("john_doe");
        anotherStudent.setPassword("password123");
    }

    @Test
    void testStudentAttributes() {
        assertThat(studentUnderTest.getUsername()).isEqualTo("john_doe");
        assertThat(studentUnderTest.getPassword()).isEqualTo("password123");
    }

    @Test
    void testEqualsAndHashCode() {
        // Test when all attributes are equal
        assertThat(studentUnderTest).isEqualTo(anotherStudent);
        assertThat(studentUnderTest.hashCode()).isEqualTo(anotherStudent.hashCode());

        // Test when username is different
        anotherStudent.setUsername("jane_doe");
        assertThat(studentUnderTest).isNotEqualTo(anotherStudent);
        assertThat(studentUnderTest.hashCode()).isNotEqualTo(anotherStudent.hashCode());

        // Reset username and test when password is different
        anotherStudent.setUsername("john_doe");
        anotherStudent.setPassword("different_password");
        assertThat(studentUnderTest).isNotEqualTo(anotherStudent);
        assertThat(studentUnderTest.hashCode()).isNotEqualTo(anotherStudent.hashCode());
    }

    @Test
    void testToString() {
        String expectedToString = "Student(username=john_doe, password=password123)";
        assertThat(studentUnderTest.toString()).isEqualTo(expectedToString);
    }
}
