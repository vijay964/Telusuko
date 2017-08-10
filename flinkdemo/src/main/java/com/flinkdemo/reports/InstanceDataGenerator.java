package com.flinkdemo.reports;

import java.util.Date;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import com.flinkdemo.elastic.InstanceModel;

public class InstanceDataGenerator  implements SourceFunction<InstanceModel>{
	private static final long serialVersionUID = 1L;
	
	private InstanceModel objInstanceModel=null;
	
	public InstanceDataGenerator(InstanceModel _objInstanceModel){
		this.objInstanceModel=_objInstanceModel;
	}

	@Override
	public void run(org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext<InstanceModel> ctx)
			throws Exception {
		ctx.collect(this.objInstanceModel);
		//ctx.collect(prepareData());
		
	}

	private InstanceModel prepareData() {
		InstanceModel objInstanceModel =null;
		try{
			objInstanceModel= new InstanceModel();
			objInstanceModel.setINSTANCE_ID(1);
			objInstanceModel.setBU("IND");
			objInstanceModel.setDEPT("DEPT1");
			objInstanceModel.setDT(new Date());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return objInstanceModel;
	}

	@Override
	public void cancel() {
		
	}
}
