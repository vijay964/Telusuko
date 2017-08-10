package com.flinkdemo.reports;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InstanceGeneratorSerializer implements Serializer<InstanceGenerator>{

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String arg0, InstanceGenerator arg1) {
		 byte[] retVal = null;
		 ObjectMapper objectMapper = new ObjectMapper();
		 try{
			 retVal = objectMapper.writeValueAsString(arg1).getBytes();
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

}
