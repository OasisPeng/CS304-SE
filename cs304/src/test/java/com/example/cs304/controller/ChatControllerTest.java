/**
 *  AI-generated-content
 *  * tool: ChatGPT
 *  * version: 3.5
 *  * usage: I used the prompt "怎么测试ChatController类中的所有方法", and
 *  * directly copy the code from its response
 */

package com.example.cs304.controller;

import com.example.cs304.controller.ChatController;
import com.example.cs304.entity.Message;
import com.example.cs304.service.impl.MessageServiceImpl;
import jakarta.websocket.CloseReason;
import jakarta.websocket.RemoteEndpoint;
import jakarta.websocket.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import static org.mockito.Mockito.*;

public class ChatControllerTest {

    @Mock
    private Session sessionMock;

    @Mock
    private RemoteEndpoint.Basic remoteEndpointMock;

    private ChatController chatController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        chatController = new ChatController();
        chatController.service = mock(MessageServiceImpl.class);
    }

    @Test
    public void onOpenTest() {
        chatController.onOpen(sessionMock, 1);
    }

    @Test
    public void onCloseTest() {
//        chatController.onClose();
        // Your assertions for onClose method if any
    }

    @Test
    public void onMessageTest() {
        Message message = new Message();
        message.setTo(2);
        String jsonMessage = "{\"to\":2}";
        Session toSessionMock = mock(Session.class);
        when(sessionMock.getId()).thenReturn("1");
        when(toSessionMock.getId()).thenReturn("2");
        when(sessionMock.getBasicRemote()).thenReturn(remoteEndpointMock);

        chatController.onMessage(jsonMessage, sessionMock, 1);

    }

    @Test
    public void onMessageTest_InvalidToSession() {
        Message message = new Message();
        message.setTo(2);
        String jsonMessage = "{\"to\":2}";
        when(sessionMock.getId()).thenReturn("1");
        when(sessionMock.getBasicRemote()).thenReturn(remoteEndpointMock);

        chatController.onMessage(jsonMessage, sessionMock, 1);

        verify(chatController.service, times(1)).InsertMessage(message);
    }

    @Test
    public void onErrorTest() {
        Throwable error = mock(Throwable.class);
        chatController.onError(error);
        // Your assertions for onError method if any
    }
}
