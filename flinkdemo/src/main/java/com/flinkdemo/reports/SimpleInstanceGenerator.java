package com.flinkdemo.reports;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class SimpleInstanceGenerator implements SourceFunction<InstanceGenerator> {

	private static final long serialVersionUID = 1L;
	 private volatile boolean isRunning = true;
	
	 @Override
	public void run(SourceContext<InstanceGenerator> ctx)
			throws Exception {
		while(isRunning){
			ctx.collect(sendInstanceCount());
		}
	}

	@Override
	public void cancel() {
		isRunning=false;
		
	}
	
	
	private InstanceGenerator sendInstanceCount(){
		//String json=null;
		InstanceGenerator obj=null;
		try{
			obj = new InstanceGenerator();
			obj.setCountryCode("IND");
			obj.setInstanceCounter(1);
		//ObjectMapper objMapper = new ObjectMapper();
			///json=objMapper.writeValueAsString(obj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
