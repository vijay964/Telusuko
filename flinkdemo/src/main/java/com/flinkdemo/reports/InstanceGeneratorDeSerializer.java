package com.flinkdemo.reports;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InstanceGeneratorDeSerializer implements Deserializer<InstanceGenerator>{

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InstanceGenerator deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
	    InstanceGenerator user = null;
	    try {
	      user = mapper.readValue(data, InstanceGenerator.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return user;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
