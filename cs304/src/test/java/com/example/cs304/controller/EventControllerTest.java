//package com.example.cs304.controller;
//
//import com.example.cs304.common.Result;
//import com.example.cs304.entity.Event;
//import com.example.cs304.service.IEventService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class EventControllerTest {
//
//    @Mock
//    private IEventService eventService;
//
//    @InjectMocks
//    private EventController eventController;
//
//    public EventControllerTest() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void saveTest() {
//        Event event = new Event();
//        when(eventService.save(event)).thenReturn(event);
//
//        Result result = eventController.save(event);
//
//        assertEquals(Result.suc(event), result);
//        verify(eventService, times(1)).save(event);
//    }
//
//    @Test
//    void updateTest() {
//        Event event = new Event();
//        when(eventService.updateById(event)).thenReturn(true);
//
//        Result result = eventController.update(event);
//
//        assertEquals(Result.suc(event), result);
//        verify(eventService, times(1)).updateById(event);
//    }
//
//    @Test
//    void delTest() {
//        Event event = new Event();
//        when(eventService.removeById(event)).thenReturn(true);
//
//        Result result = eventController.del(event);
//
//        assertEquals(Result.suc(event), result);
//        verify(eventService, times(1)).removeById(event);
//    }
//
//    @Test
//    void queryByWeekTest() {
//        int week = 1;
//        String owner = "testOwner";
//        List<Event> expectedEventList = Collections.singletonList(new Event());
//        when(eventService.queryByWeekAndOwner(week, owner)).thenReturn(expectedEventList);
//
//        Result result = eventController.queryByWeek(week, owner);
//
//        assertEquals(Result.suc(expectedEventList), result);
//        verify(eventService, times(1)).queryByWeekAndOwner(week, owner);
//    }
//
//    @Test
//    void queryTest() {
//        String date = "2024-01-01";
//        String owner = "testOwner";
//        List<Event> expectedEventList = Collections.singletonList(new Event());
//        when(eventService.queryByDateAndOwner(date, owner)).thenReturn(expectedEventList);
//
//        Result result = eventController.query(date, owner);
//
//        assertEquals(Result.suc(expectedEventList), result);
//        verify(eventService, times(1)).queryByDateAndOwner(date, owner);
//    }
//}
