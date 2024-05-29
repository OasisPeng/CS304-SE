/**
 * AI-generated-content * tool: ChatGPT
 * version: 3.5
 * usage: I used the prompt "怎么给Event类写单元测试", and use the code from its response after some modification
 */
package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {
    private Event event;

    @BeforeEach
    public void setUp() {
        event = new Event();
        event.setId(1);
        event.setTitle("Meeting");
        event.setOwner("john_doe");
        event.setWeek(12);
        event.setXq(3);
        event.setLevel("High");
        event.setFinish(0);
        event.setCategory("Work");
        event.setEmotion("Happy");
        event.setText("Discuss project updates.");
    }

    @Test
    public void testEventAttributes() {
        assertThat(event.getId()).isEqualTo(1);
        assertThat(event.getTitle()).isEqualTo("Meeting");
        assertThat(event.getOwner()).isEqualTo("john_doe");
        assertThat(event.getWeek()).isEqualTo(12);
        assertThat(event.getXq()).isEqualTo(3);
        assertThat(event.getLevel()).isEqualTo("High");
        assertThat(event.getFinish()).isEqualTo(0);
        assertThat(event.getCategory()).isEqualTo("Work");
        assertThat(event.getEmotion()).isEqualTo("Happy");
        assertThat(event.getText()).isEqualTo("Discuss project updates.");
    }

    @Test
    public void testEventToString() {
        String toString = event.toString();
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("title=Meeting");
        assertThat(toString).contains("owner=john_doe");
        assertThat(toString).contains("week=12");
        assertThat(toString).contains("xq=3");
        assertThat(toString).contains("level=High");
        assertThat(toString).contains("finish=0");
        assertThat(toString).contains("category=Work");
        assertThat(toString).contains("emotion=Happy");
        assertThat(toString).contains("text=Discuss project updates.");
    }
}
