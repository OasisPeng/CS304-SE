package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {
    private Event event;
    private Event anotherEvent;

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

        anotherEvent = new Event();
        anotherEvent.setId(1);
        anotherEvent.setTitle("Meeting");
        anotherEvent.setOwner("john_doe");
        anotherEvent.setWeek(12);
        anotherEvent.setXq(3);
        anotherEvent.setLevel("High");
        anotherEvent.setFinish(0);
        anotherEvent.setCategory("Work");
        anotherEvent.setEmotion("Happy");
        anotherEvent.setText("Discuss project updates.");
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

    @Test
    public void testEventEquals() {
        assertThat(event).isEqualTo(anotherEvent);
        assertThat(event.equals(anotherEvent)).isTrue();

        anotherEvent.setId(2);
        assertThat(event).isNotEqualTo(anotherEvent);
    }

    @Test
    public void testEventHashCode() {
        assertThat(event.hashCode()).isEqualTo(anotherEvent.hashCode());

        anotherEvent.setId(2);
        assertThat(event.hashCode()).isNotEqualTo(anotherEvent.hashCode());
    }
}
