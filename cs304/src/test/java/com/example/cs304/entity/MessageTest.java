package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class MessageTest {

    private Message messageUnderTest;
    private Message anotherMessage;

    @BeforeEach
    void setUp() {
        messageUnderTest = new Message();
        messageUnderTest.setId(1);
        messageUnderTest.setFrom(100);
        messageUnderTest.setTo(200);
        messageUnderTest.setText("Hello");
        messageUnderTest.setTime(Timestamp.valueOf("2024-05-29 12:00:00"));
        messageUnderTest.setOld(0);

        anotherMessage = new Message();
        anotherMessage.setId(1);
        anotherMessage.setFrom(100);
        anotherMessage.setTo(200);
        anotherMessage.setText("Hello");
        anotherMessage.setTime(Timestamp.valueOf("2024-05-29 12:00:00"));
        anotherMessage.setOld(0);
    }

    @Test
    void testMessageAttributes() {
        assertThat(messageUnderTest.getId()).isEqualTo(1);
        assertThat(messageUnderTest.getFrom()).isEqualTo(100);
        assertThat(messageUnderTest.getTo()).isEqualTo(200);
        assertThat(messageUnderTest.getText()).isEqualTo("Hello");
        assertThat(messageUnderTest.getTime()).isEqualTo(Timestamp.valueOf("2024-05-29 12:00:00"));
        assertThat(messageUnderTest.getOld()).isEqualTo(0);
    }

    @Test
    void testEqualsAndHashCode() {
        // Test when all attributes are equal
        assertThat(messageUnderTest).isEqualTo(anotherMessage);
        assertThat(messageUnderTest.hashCode()).isEqualTo(anotherMessage.hashCode());

        // Test when id is different
        anotherMessage.setId(2);
        assertThat(messageUnderTest).isNotEqualTo(anotherMessage);
        assertThat(messageUnderTest.hashCode()).isNotEqualTo(anotherMessage.hashCode());

        // Reset id and test when from is different
        anotherMessage.setId(1);
        anotherMessage.setFrom(101);
        assertThat(messageUnderTest).isNotEqualTo(anotherMessage);
        assertThat(messageUnderTest.hashCode()).isNotEqualTo(anotherMessage.hashCode());

        // Reset from and test when to is different
        anotherMessage.setFrom(100);
        anotherMessage.setTo(201);
        assertThat(messageUnderTest).isNotEqualTo(anotherMessage);
        assertThat(messageUnderTest.hashCode()).isNotEqualTo(anotherMessage.hashCode());

        // Reset to and test when text is different
        anotherMessage.setTo(200);
        anotherMessage.setText("Hi");
        assertThat(messageUnderTest).isNotEqualTo(anotherMessage);
        assertThat(messageUnderTest.hashCode()).isNotEqualTo(anotherMessage.hashCode());

        // Reset text and test when time is different
        anotherMessage.setText("Hello");
        anotherMessage.setTime(Timestamp.valueOf("2024-05-30 12:00:00"));
        assertThat(messageUnderTest).isNotEqualTo(anotherMessage);
        assertThat(messageUnderTest.hashCode()).isNotEqualTo(anotherMessage.hashCode());

        // Reset time and test when old is different
        anotherMessage.setTime(Timestamp.valueOf("2024-05-29 12:00:00"));
        anotherMessage.setOld(1);
        assertThat(messageUnderTest).isNotEqualTo(anotherMessage);
        assertThat(messageUnderTest.hashCode()).isNotEqualTo(anotherMessage.hashCode());
    }

    @Test
    void testToString() {
        String expectedToString = "Message(id=1, from=100, to=200, text=Hello, time=2024-05-29 12:00:00.0, old=0)";
        assertThat(messageUnderTest.toString()).isEqualTo(expectedToString);
    }
}
