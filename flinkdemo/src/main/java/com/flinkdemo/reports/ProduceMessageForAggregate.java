package com.flinkdemo.reports;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer08;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

public class ProduceMessageForAggregate  {

	private int totalAggr=0;
	
	public ProduceMessageForAggregate(int _totalAggr){
		this.totalAggr=_totalAggr;
		try {
			invoke();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
/*	public static void main(String[] args) {
		try{
			ProduceMessageForAggregate o1 = new  ProduceMessageForAggregate(0);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}*/
	public void invoke() throws Exception {
		try{
			StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
			DataStream<String> stream = env.addSource(new AggregateInstanceGenerator(totalAggr));
			FlinkKafkaProducer08<String> myProducer = new FlinkKafkaProducer08<String>(
			        "localhost:9092",            // broker list
			        "BUInstanceAggregate",                  // target topic
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
	
	public String dummy(String test){
		return "";
	}

/*	public static void main(String[] args) throws Exception {
		try{
			StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
			DataStream<String> stream = env.addSource(new SimpleStringGenerator());
			FlinkKafkaProducer08<String> myProducer = new FlinkKafkaProducer08<String>(
			        "localhost:9092",            // broker list
			        "BUInstanceCreated",                  // target topic
			        new SimpleStringSchema());
			// the following is necessary for at-least-once delivery guarantee
			myProducer.setLogFailuresOnly(false);   // "false" by default
			myProducer.setFlushOnCheckpoint(true);  // "false" by default
			stream.addSink(myProducer);
			env.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
