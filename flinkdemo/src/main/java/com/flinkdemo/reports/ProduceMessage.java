package com.flinkdemo.reports;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer08;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

public class ProduceMessage {

	public static void main(String[] args) throws Exception {
		try{
			StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
			DataStream<String> stream = env.addSource(new SimpleStringGenerator());
			FlinkKafkaProducer08<String> myProducer = new FlinkKafkaProducer08<String>(
			        "localhost:9092",            // broker list
			        "topic1",                  // target topic
			        new SimpleStringSchema());
			// the following is necessary for at-least-once delivery guarantee
			myProducer.setLogFailuresOnly(false);   // "false" by default
			myProducer.setFlushOnCheckpoint(true);  // "false" by default
			stream.addSink(myProducer);
			env.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
