package com.flinkdemo.reports;

import java.util.Properties;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

public class ConsumeMessage {

	public static void main(String[] args) {
		try{
			StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
			Properties properties = new Properties();
			properties.setProperty("bootstrap.servers", "localhost:9092");
			// only required for Kafka 0.8
			properties.setProperty("zookeeper.connect", "localhost:2181");
			properties.setProperty("group.id", "test");
			DataStream<String> stream = env
				.addSource(new FlinkKafkaConsumer08<>("topic1", new SimpleStringSchema(), properties));
			
			  stream.map(new MapFunction<String, String>() {
				      private static final long serialVersionUID = -6867736771747690202L;

				      @Override
				      public String map(String value) throws Exception {
				    	  System.out.println("Value::"+value);
				        return "Stream Value: " + value;
				      }
				    }).print();
			  env.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

