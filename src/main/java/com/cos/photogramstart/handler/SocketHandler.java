package com.cos.photogramstart.handler;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
		
		//메시지 발송
		String msg = textMessage.getPayload();
	
		JSONObject obj = new JSONObject();
		obj.put("type", "message");
		obj.put("sessionId", webSocketSession.getId());
		obj.put("msg", msg);
		
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				wss.sendMessage(new TextMessage(obj.toJSONString()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		// 소켓 연결
		super.afterConnectionEstablished(webSocketSession);
		System.out.println("소켓연결" + webSocketSession);
		sessionMap.put(webSocketSession.getId(), webSocketSession);
		
		JSONObject obj = new JSONObject();
		obj.put("type", "getId");
		obj.put("sessionId", webSocketSession.getId());
		webSocketSession.sendMessage(new TextMessage(obj.toJSONString()));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
		// 소켓 종료
		System.out.println("소켓종료");
		sessionMap.remove(webSocketSession.getId());
		super.afterConnectionClosed(webSocketSession, closeStatus);
	}
	
	
	
}
