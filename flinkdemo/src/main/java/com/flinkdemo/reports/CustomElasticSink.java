package com.flinkdemo.reports;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.net.InetAddress;

import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;



public class CustomElasticSink implements  SinkFunction<String>{
	private static final long serialVersionUID = 1L;

	@Override
	public void invoke(String value) throws Exception {
		System.out.println("In INvoke of CustomElasticSink:::");
		try{
			Settings settings = Settings.builder()
        	        .put("client.transport.sniff", true).build();
        	TransportClient client = new PreBuiltTransportClient(settings)
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        	BulkRequestBuilder bulkRequest = client.prepareBulk();
        	bulkRequest.add(client.prepareIndex("instancecount", "instance")
        	        .setSource(jsonBuilder()
        	                    .startObject()
        	                        .field("businessUnit", "UK")
        	                        .field("departmentName", "DEP2")
        	                        .field("instanceNumber", 11)
        	                        .field("instanceCreationDate", "2017-07-27")
        	                        .endObject()
        	                  )
        	        );
        	
        			   
        	BulkResponse bulkResponse = bulkRequest.get();
        	System.out.println("bulkResponse :::: "+bulkResponse.hasFailures());
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
