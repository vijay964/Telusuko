package com.flinkdemo.reports;

import java.io.IOException;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.SerializationSchema;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomInstanceGeneratorSchema implements DeserializationSchema<InstanceGenerator>, SerializationSchema<InstanceGenerator> {

	private static final long serialVersionUID = 1L;

	@Override
	public byte[] serialize(InstanceGenerator arg1) {
		 byte[] retVal = null;
		 ObjectMapper objectMapper = new ObjectMapper();
		 try{
			 retVal = objectMapper.writeValueAsString(arg1).getBytes();
		 }catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	
	@Override
	public TypeInformation<InstanceGenerator> getProducedType() {
		// TODO Auto-generated method stub
		return  TypeInformation.of(InstanceGenerator.class);
	}

	@Override
	public InstanceGenerator deserialize(byte[] message) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
	    InstanceGenerator user = null;
	    try {
	      user = mapper.readValue(message, InstanceGenerator.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return user;
	}

	@Override
	public boolean isEndOfStream(InstanceGenerator nextElement) {
		// TODO Auto-generated method stub
		return false;
	}

}
