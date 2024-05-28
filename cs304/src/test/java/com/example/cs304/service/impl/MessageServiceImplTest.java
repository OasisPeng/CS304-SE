package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.Message;
import com.example.cs304.mapper.MessageMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    private MessageMapper mockDao;

    private MessageServiceImpl messageServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        messageServiceImplUnderTest = new MessageServiceImpl();
        messageServiceImplUnderTest.dao = mockDao;
    }

    @Test
    void testSelectByTwoUser() {
        // Setup
        final Message message = new Message();
        message.setId(0);
        message.setFrom(0);
        message.setTo(0);
        message.setTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        message.setOld(0);
        final List<Message> expectedResult = List.of(message);

        // Configure MessageMapper.selectList(...).
        final Message message1 = new Message();
        message1.setId(0);
        message1.setFrom(0);
        message1.setTo(0);
        message1.setTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        message1.setOld(0);
        final List<Message> messages = List.of(message1);
        when(mockDao.selectList(any(LambdaQueryWrapper.class))).thenReturn(messages);

        // Run the test
        final List<Message> result = messageServiceImplUnderTest.SelectByTwoUser(0, 0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectByTwoUser_MessageMapperReturnsNoItems() {
        // Setup
        when(mockDao.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Message> result = messageServiceImplUnderTest.SelectByTwoUser(0, 0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testInsertMessage() {
        // Setup
        final Message message = new Message();
        message.setId(0);
        message.setFrom(0);
        message.setTo(0);
        message.setTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        message.setOld(0);

        final Message expectedResult = new Message();
        expectedResult.setId(0);
        expectedResult.setFrom(0);
        expectedResult.setTo(0);
        expectedResult.setTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        expectedResult.setOld(0);

        // Run the test
        final Message result = messageServiceImplUnderTest.InsertMessage(message);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

        // Confirm MessageMapper.insert(...).
        final Message entity = new Message();
        entity.setId(0);
        entity.setFrom(0);
        entity.setTo(0);
        entity.setTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        entity.setOld(0);
        verify(mockDao).insert(entity);
    }

    @Test
    void testInsertMessages() {
        // Setup
        final Message message = new Message();
        message.setId(0);
        message.setFrom(0);
        message.setTo(0);
        message.setTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        message.setOld(0);
        final List<Message> messages = List.of(message);

        // Run the test
        final Boolean result = messageServiceImplUnderTest.InsertMessages(messages);

        // Verify the results
        assertThat(result).isTrue();

        // Confirm MessageMapper.insert(...).
        final Message entity = new Message();
        entity.setId(0);
        entity.setFrom(0);
        entity.setTo(0);
        entity.setTime(Timestamp.valueOf(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0)));
        entity.setOld(0);
        verify(mockDao).insert(entity);
    }
}
