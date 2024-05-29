package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AiContentTest {
    private AiContent aiContent;
    private AiContent anotherAiContent;

    @BeforeEach
    public void setUp() {
        aiContent = new AiContent();
        aiContent.setUser(1);
        aiContent.setContent("[{'role': 'user', 'content':'Hello'},{'role':'assistant', 'content':'Hi there!'}]");
        aiContent.setId(100);

        anotherAiContent = new AiContent();
        anotherAiContent.setUser(1);
        anotherAiContent.setContent("[{'role': 'user', 'content':'Hello'},{'role':'assistant', 'content':'Hi there!'}]");
        anotherAiContent.setId(100);
    }

    @Test
    public void testAiContentAttributes() {
        assertThat(aiContent.getUser()).isEqualTo(1);
        assertThat(aiContent.getContent()).isEqualTo("[{'role': 'user', 'content':'Hello'},{'role':'assistant', 'content':'Hi there!'}]");
        assertThat(aiContent.getId()).isEqualTo(100);
    }

    @Test
    public void testAiContentToString() {
        String toString = aiContent.toString();
        assertThat(toString).contains("user=1");
        assertThat(toString).contains("content=[{'role': 'user', 'content':'Hello'},{'role':'assistant', 'content':'Hi there!'}]");
        assertThat(toString).contains("id=100");
    }

    @Test
    public void testAiContentEquals() {
        assertThat(aiContent).isEqualTo(anotherAiContent);
        assertThat(aiContent.equals(anotherAiContent)).isTrue();

        anotherAiContent.setId(101);
        assertThat(aiContent).isNotEqualTo(anotherAiContent);
    }

    @Test
    public void testAiContentHashCode() {
        assertThat(aiContent.hashCode()).isEqualTo(anotherAiContent.hashCode());

        anotherAiContent.setId(101);
        assertThat(aiContent.hashCode()).isNotEqualTo(anotherAiContent.hashCode());
    }
}
