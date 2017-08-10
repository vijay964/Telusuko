package com.flinkdemo.reports;

import java.util.Properties;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08;
import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ConsumeMessageForAggregate {

	
	private String jsonValue="";
	
	public ConsumeMessageForAggregate(String _jsonValue){
		this.jsonValue=_jsonValue;	
		try {
			invoke(this.jsonValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void invoke(String value) throws Exception {
		try{
			StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
			Properties properties = new Properties();
			properties.setProperty("bootstrap.servers", "localhost:9092");
			// only required for Kafka 0.8
			properties.setProperty("zookeeper.connect", "localhost:2181");
			properties.setProperty("group.id", "test1");
			DataStream<ObjectNode> stream = env
				.addSource(new FlinkKafkaConsumer08<>("BUInstanceAggregate", new JSONKeyValueDeserializationSchema(false), properties));
			  stream.rebalance().map(new MapFunction<ObjectNode, String>() {
				    private static final long serialVersionUID = -6867736771747690202L;
				      @Override
				      public String map(ObjectNode node) throws Exception {
				    	 System.out.println("Value For Consume Aggregate::"+value);
				    	 
				    	 new ProduceMessageForAggregate(sendData(jsonValue,node.get(0).textValue()));
				        return "Stream Value: " + value;
				      }
				    }).print();
			 
			  env.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	private int sendData(String jsonValue,String textValue){
		System.out.println("textValue :::::: "+textValue);
		int total=0;
		try{
			InstanceGenerator obj=new  ObjectMapper().readValue(jsonValue, InstanceGenerator.class);
	    	InstanceGenerator forAggrobj=new  ObjectMapper().readValue(textValue, InstanceGenerator.class);
	    	 total =forAggrobj.getInstanceCounter()+obj.getInstanceCounter();
		}catch(Exception e){
			e.printStackTrace();
		}
		return total;
	}
	
	
/*	public static void main(String[] args) {
		try{
			StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
			Properties properties = new Properties();
			properties.setProperty("bootstrap.servers", "localhost:9092");
			// only required for Kafka 0.8
			properties.setProperty("zookeeper.connect", "localhost:2181");
			properties.setProperty("group.id", "test");
			DataStream<String> stream = env
				.addSource(new FlinkKafkaConsumer08<>("BUInstanceAggregate", new SimpleStringSchema(), properties));
			
			  stream.map(new MapFunction<String, String>() {
				      private static final long serialVersionUID = -6867736771747690202L;

				      @Override
				      public String map(String value) throws Exception {
				    	  System.out.println("Value::"+value);
				        return "Stream Value: " + value;
				      }
				    }).addSink(prodiceofadd);
			 
			  env.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
