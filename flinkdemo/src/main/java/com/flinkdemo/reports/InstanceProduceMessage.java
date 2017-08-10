package com.flinkdemo.reports;

import java.util.Properties;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer08;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

public class InstanceProduceMessage {

	public static void main(String[] args) throws Exception {
		try{
			Properties properties = new Properties();
			properties.setProperty("bootstrap.servers", "localhost:9092");
			// only required for Kafka 0.8
			properties.setProperty("zookeeper.connect", "localhost:2181");
			properties.setProperty("group.id", "test");
			//properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		//	properties.put("value.serializer", "com.adp.reports.InstanceGeneratorSerializer");
			StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
			DataStream<InstanceGenerator> stream = env.addSource(new SimpleInstanceGenerator());
			FlinkKafkaProducer08<InstanceGenerator> myProducer = new FlinkKafkaProducer08<InstanceGenerator>(
			        "BUInstanceCreated",                  // target topic
			        new CustomInstanceGeneratorSchema(),properties);
			
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
