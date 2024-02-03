package com.cos.photogramstart.handler;

import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component // 스프링 어플리케이션 컨텍스트에 Bean으로 등록되어 관리
public class SocketHandler extends TextWebSocketHandler{

	HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
	
	@Override
	public void handleTextMessage(WebSocketSession webSocketSession, TextMessage textMessage) {
		// 메시지 발송
		
		String msg = textMessage.getPayload(); //수신한 메시지 msg변수 저장
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				wss.sendMessage(new TextMessage(msg));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		// 소켓 연결
		super.afterConnectionEstablished(webSocketSession);
		sessionMap.put(webSocketSession.getId(), webSocketSession);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
		// 소켓 종료
		sessionMap.remove(webSocketSession.getId());
		super.afterConnectionClosed(webSocketSession, closeStatus);
	}
	
}
