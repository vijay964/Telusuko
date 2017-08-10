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

public class InstanceElasticSink implements SinkFunction<InstanceModel>{

	private static final long serialVersionUID = 1L;

	@Override
	public void invoke(InstanceModel value) throws Exception {
		System.out.println("IN InstanceElasticSink INVOKE METHOD......");
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
      	                        .field("businessUnit", value.getBU())
      	                        .field("departmentName", value.getDEPT())
      	                        .field("instanceNumber", value.getINSTANCE_ID())
      	                        .field("instanceCreationDate", value.getDT())
      	                        .endObject()
      	                  )
      	        );
      	
      			   
      	BulkResponse bulkResponse = bulkRequest.get();
      	System.out.println("bulkResponse :::: "+bulkResponse.hasFailures());
      	
        			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
