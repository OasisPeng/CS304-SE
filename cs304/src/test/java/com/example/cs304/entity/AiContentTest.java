/**
 * AI-generated-content * tool: ChatGPT
 * version: 3.5
 * usage: I used the prompt "怎么给AiContentTest类写单元测试", and use the code from its response after some modification
 */
package com.example.cs304.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AiContentTest {
    private AiContent aiContent;

    @BeforeEach
    public void setUp() {
        aiContent = new AiContent();
        aiContent.setUser(1);
        aiContent.setContent("[{'role': 'user', 'content':'Hello'},{'role':'assistant', 'content':'Hi there!'}]");
        aiContent.setId(100);
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
}
