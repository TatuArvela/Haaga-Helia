package com.example.websocket_voting;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class VotingWebSocketHandler extends TextWebSocketHandler {

    public String question = "";
    public int no = 0;
    public int yes = 0;
    private List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // A connection has been opened
        addSession(session);
        sendData();
    }

    synchronized void addSession(WebSocketSession sess) {
        this.sessions.add(sess);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // The connection has been closed
        removeSession(session);
        sendData();
    }

    synchronized void removeSession(WebSocketSession sess) {
        this.sessions.remove(sess);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        // A message has been received
        String message = textMessage.getPayload();

        if(message.startsWith("question")) {
            String newQuestion = message.replaceFirst("^question ", "");
            question = newQuestion;
            no = 0;
            yes = 0;
        }

        if (message.equals("no") && !question.isEmpty()) {
            no++;
        }
        
        if (message.equals("yes") && !question.isEmpty()) {
            yes++;
        }

        sendData();
    }

    protected void sendData() throws Exception {
        // Broadcast to all users
        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage("{" +
                "\"question\": \"" + question + "\"," +
                "\"no\": \"" + no + "\", " +
                "\"yes\": \"" + yes + "\"" +
                "}"));
        }
    }
}
