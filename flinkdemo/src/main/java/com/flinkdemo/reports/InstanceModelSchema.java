package com.flinkdemo.reports;

import java.io.IOException;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.SerializationSchema;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InstanceModelSchema implements DeserializationSchema<InstanceModel>, SerializationSchema<InstanceModel> {

	private static final long serialVersionUID = 1L;

	@Override
	public TypeInformation<InstanceModel> getProducedType() {
		return  TypeInformation.of(InstanceModel.class);
	}

	@Override
	public byte[] serialize(InstanceModel arg1) {
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
	public InstanceModel deserialize(byte[] message) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		InstanceModel user = null;
	    try {
	      user = mapper.readValue(message, InstanceModel.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return user;
	}

	@Override
	public boolean isEndOfStream(InstanceModel nextElement) {
		// TODO Auto-generated method stub
		return false;
	}

}
