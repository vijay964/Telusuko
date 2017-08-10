package com.flinkdemo.reports;


import org.apache.flink.streaming.api.functions.source.SourceFunction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AggregateInstanceGenerator implements SourceFunction<String>{

	private static final long serialVersionUID = 1L;
	private int totalAggr;
	public AggregateInstanceGenerator(int _totalAggr){
		this.totalAggr=_totalAggr;
	}
	
	private volatile boolean isRunning = true;
	@Override
	public void run(SourceContext<String> ctx)
			throws Exception {
			ctx.collect(sendInstanceCount());
		
	}

	@Override
	public void cancel() {
		isRunning=false;
		
	}
	
	
	private String sendInstanceCount(){
		String json=null;
		try{
			InstanceGenerator obj = new InstanceGenerator();
			obj.setCountryCode("IND");
			obj.setInstanceCounter(this.totalAggr);
			ObjectMapper objMapper = new ObjectMapper();
			json=objMapper.writeValueAsString(obj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
