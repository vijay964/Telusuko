package com.elasticdemo;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import java.net.InetAddress;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class GeoPoint {
	

	public static void main(String[] args) {
		try{
			
			Settings settings = Settings.builder()
        	        .put("client.transport.sniff", true).build();
        		TransportClient client = new PreBuiltTransportClient(settings)
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        	
        	
        	client.admin().indices().create(new CreateIndexRequest("geolocation")).actionGet();
        	//client.admin().indices().delete(new DeleteIndexRequest("geolocation")).actionGet();
        	XContentBuilder mapping = jsonBuilder().prettyPrint()
	                .startObject()
                    .startObject("_default_")
                        .startObject("properties")
                           // .startObject("speaker").field("type", "keyword").field("index", "not_analyzed").endObject()
                        	.startObject("location").field("type", "geo_point").endObject()
                        	.startObject("businessUnit").field("type", "keyword").endObject()
                        	.startObject("name").field("type", "keyword").endObject()
                        .endObject()
                    .endObject()
                .endObject();
        	
        	System.out.println("Pretty Print ::: "+mapping.string());
        	
        	PutMappingResponse putMappingResponse = client.admin().indices()
        		    .preparePutMapping("geolocation")
        		    .setType("_default_")
        		    .setSource(mapping.prettyPrint())
        		    .execute().actionGet();
        	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
