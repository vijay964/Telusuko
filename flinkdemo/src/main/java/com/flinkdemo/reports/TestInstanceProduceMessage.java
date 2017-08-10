package com.flinkdemo.reports;

import java.util.Properties;import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer08;

public class TestInstanceProduceMessage {

	public void putMessageToKafka(InstanceModel objInstanceModel){
		System.out.println("IN Method putMessageToKafka .....");
		try{
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("zookeeper.connect", "localhost:2181");
		//TODO :: Give something as second parameter
		properties.setProperty("group.id", "");
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		DataStream<InstanceModel> stream =env.addSource(new InstanceDataGenerator(objInstanceModel) , TypeInformation.of(InstanceModel.class));
		FlinkKafkaProducer08<InstanceModel> myProducer = new FlinkKafkaProducer08<InstanceModel>(
		        "InstanceCreated",                  // target topic
		        new InstanceModelSchema(),properties);
		
		myProducer.setLogFailuresOnly(false);   // "false" by default
		myProducer.setFlushOnCheckpoint(true);  // "false" by default
		stream.addSink(new InstanceElasticSink());
		env.execute();
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
}
