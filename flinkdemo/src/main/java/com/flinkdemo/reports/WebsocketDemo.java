package com.flinkdemo.reports;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class WebsocketDemo {
	
	 public static void main( String[] args )
	    {
	       try{
	    		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
				
				Session session = container.connectToServer(CSWebsocketClient.class, new URI(
						"ws://localhost:8181/WebSocket/websocket"));
				session.getBasicRemote().sendText("HI");
	       }catch (Exception e) {
			e.printStackTrace();
		}
	    }

}
