package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.AiContent;
import com.example.cs304.mapper.AiContentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AiContentServiceImplTest {

    @Mock
    private AiContentMapper mockAiContentMapper;

    private AiContentServiceImpl aiContentServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        aiContentServiceImplUnderTest = new AiContentServiceImpl();
        aiContentServiceImplUnderTest.aiContentMapper = mockAiContentMapper;
    }

    @Test
    void testSelectById() {
        // Setup
        final AiContent expectedResult = new AiContent();
        expectedResult.setUser(0);
        expectedResult.setContent("content");
        expectedResult.setId(0);

        // Configure AiContentMapper.selectById(...).
        final AiContent aiContent = new AiContent();
        aiContent.setUser(0);
        aiContent.setContent("content");
        aiContent.setId(0);
        when(mockAiContentMapper.selectById(0)).thenReturn(aiContent);

        // Run the test
        final AiContent result = aiContentServiceImplUnderTest.SelectById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByUserName() {
        // Setup
        final AiContent aiContent = new AiContent();
        aiContent.setUser(0);
        aiContent.setContent("content");
        aiContent.setId(0);
        final List<AiContent> expectedResult = List.of(aiContent);

        // Configure AiContentMapper.selectList(...).
        final AiContent aiContent1 = new AiContent();
        aiContent1.setUser(0);
        aiContent1.setContent("content");
        aiContent1.setId(0);
        final List<AiContent> aiContents = List.of(aiContent1);
        when(mockAiContentMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(aiContents);

        // Run the test
        final List<AiContent> result = aiContentServiceImplUnderTest.SelectByUserName(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByUserName_AiContentMapperReturnsNoItems() {
        // Setup
        when(mockAiContentMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<AiContent> result = aiContentServiceImplUnderTest.SelectByUserName(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testDeleteById() {
        // Setup
        when(mockAiContentMapper.deleteById(0)).thenReturn(0);

        // Run the test
        final Integer result = aiContentServiceImplUnderTest.DeleteById(0);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }
}
