package com.example.cs304.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.cs304.entity.Event;
import com.example.cs304.mapper.EventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventServiceImplTest {

    @Mock
    private EventMapper mockEventMapper;

    private EventServiceImpl eventServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        eventServiceImplUnderTest = new EventServiceImpl();
        eventServiceImplUnderTest.eventMapper = mockEventMapper;
    }

    @Test
    void testQueryByDateAndOwner() {
        // Setup
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        final List<Event> expectedResult = List.of(event);

        // Configure EventMapper.selectList(...).
        final Event event1 = new Event();
        event1.setId(0);
        event1.setTitle("title");
        event1.setOwner("owner");
        event1.setWeek(1);
        event1.setXq(0);
        final List<Event> events = List.of(event1);
        when(mockEventMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(events);

        // Run the test
        final List<Event> result = eventServiceImplUnderTest.queryByDateAndOwner(new int[]{0,1}, "owner");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testQueryByDateAndOwner_EventMapperReturnsNoItems() {
        // Setup
        when(mockEventMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Event> result = eventServiceImplUnderTest.queryByDateAndOwner(new int[]{0,1}, "owner");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() {
        // Setup
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);


        // Configure EventMapper.insert(...).
        final Event entity = new Event();
        entity.setId(0);
        entity.setTitle("title");
        entity.setOwner("owner");
        entity.setWeek(1);
        entity.setXq(0);
        when(mockEventMapper.insert(entity)).thenReturn(0);

        // Run the test
        final Event result = eventServiceImplUnderTest.save(event);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    @Test
    void testUpdateById() {
        // Setup
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);

        final Event expectedResult = new Event();
        expectedResult.setId(0);
        expectedResult.setTitle("title");
        expectedResult.setOwner("owner");
        expectedResult.setWeek(1);
        expectedResult.setXq(0);

        // Configure EventMapper.updateById(...).
        final Event entity = new Event();
        entity.setId(0);
        entity.setTitle("title");
        entity.setOwner("owner");
        entity.setWeek(1);
        entity.setXq(0);
        when(mockEventMapper.updateById(entity)).thenReturn(0);

        // Run the test
        final Event result = eventServiceImplUnderTest.updateById(event);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    @Test
    void testRemoveById() {
        // Setup
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);

        final Event expectedResult = new Event();
        expectedResult.setId(0);
        expectedResult.setTitle("title");
        expectedResult.setOwner("owner");
        expectedResult.setWeek(1);
        expectedResult.setXq(0);

        // Configure EventMapper.deleteById(...).
        final Event entity = new Event();
        entity.setId(0);
        entity.setTitle("title");
        entity.setOwner("owner");
        entity.setWeek(1);
        entity.setXq(0);
        when(mockEventMapper.deleteById(entity)).thenReturn(1);

        // Run the test
        final Event result = eventServiceImplUnderTest.removeById(event);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    @Test
    void testQueryByWeekAndOwner() {
        // Setup
        final Event event = new Event();
        event.setId(0);
        event.setTitle("title");
        event.setOwner("owner");
        event.setWeek(1);
        event.setXq(0);
        final List<Event> expectedResult = List.of(event);

        // Configure EventMapper.selectList(...).
        final Event event1 = new Event();
        event1.setId(0);
        event1.setTitle("title");
        event1.setOwner("owner");
        event1.setWeek(1);
        event1.setXq(0);
        final List<Event> events = List.of(event1);
        when(mockEventMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(events);

        // Run the test
        final List<Event> result = eventServiceImplUnderTest.queryByWeekAndOwner(1, "owner");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testQueryByWeekAndOwner_EventMapperReturnsNoItems() {
        // Setup
        when(mockEventMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<Event> result = eventServiceImplUnderTest.queryByWeekAndOwner(1, "owner");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
