package com.flinkdemo.elastic;


import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;


public class InstanceInsertToElasticDemo {
	
	public void insertToElastic(List<InstanceModel> obiListInstanceModel){
		System.out.println("Inside insertToElastic");
		try{
			Settings settings = Settings.builder()
        	        .put("client.transport.sniff", true).build();
        	TransportClient client = new PreBuiltTransportClient(settings)
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        	BulkRequestBuilder bulkRequest = client.prepareBulk();
        	  for(InstanceModel obj:obiListInstanceModel){
        	bulkRequest.add(client.prepareIndex("instanceidx", "instancetype")
        			  .setSource(jsonBuilder()
      	                    .startObject()
      	                        .field("businessUnit", obj.getBU())
      	                        .field("clientName", obj.getCLIENT_NAME())
      	                        .field("instanceNumber", obj.getINSTANCEID())
      	                        .field("instanceStatus", obj.getINSTANCE_STATUS_LOCAL())
      	                        .field("countryName", obj.getCOUNTRY_NAME())
    	                        .field("departmentName", obj.getDEPT_NAME())
    	                        .field("payrollYear", obj.getPAYROLL_YEAR())
      	                        .field("instanceCreationDate", converToTimeStamp(obj.getCREATED_D()))
      	                     
      	                        .endObject()
      	                  )
      	        );
        	}
      	
      			   
      	BulkResponse bulkResponse = bulkRequest.get();
      	System.out.println("bulkResponse :::: "+bulkResponse.hasFailures());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertToElasticToTask(List<TaskModel> obiListTaskModel){
		try{
			Settings settings = Settings.builder()
        	        .put("client.transport.sniff", true).build();
        	TransportClient client = new PreBuiltTransportClient(settings)
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
        	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        	BulkRequestBuilder bulkRequest = client.prepareBulk();
        	for(TaskModel obj:obiListTaskModel){
        	bulkRequest.add(client.prepareIndex("taskidx", "tasktype")
        			  .setSource(jsonBuilder()
      	                    .startObject()
      	                        .field("instanceNumber", obj.getINSTANCEID())
      	                        .field("taskNumber", obj.getTASK_ID())
      	                        .field("taskName", obj.getTASKNAME())
      	                        .field("localtaskName", obj.getLOCALTASKNAME())
      	                        .field("actualStartDate", converToTimeStamp(obj.getACTUALSTARTDATE()))
    	                        .field("actualEndDate", converToTimeStamp(obj.getACTUALENDDATE()))
    	                        .field("plannedStartDate", converToTimeStamp(obj.getPLANNEDSTARTDATE()))
      	                        .field("plannedEndDate", converToTimeStamp(obj.getPLANNEDENDDATE()))
      	                        .field("assignedTo", obj.getASSIGNED_TO())
    	                        .field("departmentName", converToTimeStamp(obj.getDEPT_NAME()))
    	                        .field("taskCreationDate", converToTimeStamp(obj.getCREATED_D()))
      	                        .endObject()
      	                  )
      	        );
		}
      			   
      	BulkResponse bulkResponse = bulkRequest.get();
      	System.out.println("bulkResponse :::: "+bulkResponse.hasFailures());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String converToTimeStamp(String date){
		String rdate=null;
		try{
			DateFormat readFormat = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss aa");
			DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			Date date1 = readFormat.parse(date);
			rdate=writeFormat.format(date1);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return rdate;
	}

}
