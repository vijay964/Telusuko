package com.flinkdemo.reports;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkElasticConnector {

	public static void main(String[] args) {
		try{
			StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
			DataStream<String> stream = env.addSource(new SimpleStringGenerator());
			stream.addSink(new CustomElasticSink());
			env.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
