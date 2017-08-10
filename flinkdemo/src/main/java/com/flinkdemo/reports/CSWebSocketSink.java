package com.flinkdemo.reports;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CSWebSocketSink implements SinkFunction<InstanceGenerator>{
	private static final long serialVersionUID = 1L;
	

	
	@Override
	public void invoke(InstanceGenerator value) throws Exception {
		try{
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			Session session = container.connectToServer(CSWebsocketClient.class, new URI(
					"ws://HYRDLT4199:8181/WebSocket/websocket"));
			session.getBasicRemote().sendText(new ObjectMapper().writeValueAsString(value));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
