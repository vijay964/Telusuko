package com.flinkdemo.reports;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
 
@ClientEndpoint
public class CSWebsocketClient {
	
	private Session session;

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Connected to server");
			
		}
	
/*	@OnMessage
	public void onText(String message, Session session) {
		System.out.println("Message received from server:" + message);
	}*/
	

   @OnMessage
    public void onMessage(String message) {
    	
    	System.out.println("From on message :: " + message);
        
    }

	@OnClose
	public void onClose(CloseReason reason, Session session) {
		System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
	}


	public void sendMessage(String str) {
		try {
			//session.getBasicRemote().sendText(str);
			session.getAsyncRemote().sendText(str);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}

