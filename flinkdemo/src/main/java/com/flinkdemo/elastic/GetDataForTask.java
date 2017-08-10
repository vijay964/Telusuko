package com.flinkdemo.elastic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;

public class GetDataForTask {
	
	//TODO:: Provide Username, Password and URL
	static final String USERNAME = "username";
	 static final String PASSWORD = "password";
	 static String URL = "jdbc:oracle:thin:@domain:port/instancename";
	 
	public static void main(String[] args) {
		try{
			GetDataForTask obj = new  GetDataForTask();
			obj.test();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void test(){
		System.out.println("iN tEST For Tasks ........");
		OracleConnection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<TaskModel> obiListInstanceModel= new ArrayList<TaskModel>();
		InstanceInsertToElasticDemo objInstanceInsertToElasticDemo= new InstanceInsertToElasticDemo();
		int i=1;
		try{
			 conn = connect();
			  stmt = conn.createStatement();
			  //TODO :: Provide Table Name
			  rs = stmt.executeQuery("select * from ( SELECT  INSTANCEID,DECODE(TASK_ID,'null',0,TASK_ID),TASKNAME,LOCALTASKNAME,to_char(ACTUALSTARTDATE, 'yyyy-mm-dd hh:mi:ss AM'),to_char(ACTUALENDDATE, 'yyyy-mm-dd hh:mi:ss AM'),to_char(PLANNEDSTARTDATE, 'yyyy-mm-dd hh:mi:ss AM'),to_char(PLANNEDENDDATE, 'yyyy-mm-dd hh:mi:ss AM'),ASSIGNED_TO,DEPT_NAME,to_char(CREATED_D, 'yyyy-mm-dd hh:mi:ss AM'),row_number() over(order by instanceid) seq FROM Table_Name) where seq > 40000 and seq <60001");
			 while(rs.next()){
				System.out.println("MY III "+i);
				TaskModel objTaskModel= new TaskModel();
				objTaskModel.setINSTANCEID(rs.getInt(1));
				objTaskModel.setTASK_ID(rs.getInt(2));
				objTaskModel.setTASKNAME(rs.getString(3));
				objTaskModel.setLOCALTASKNAME(rs.getString(4));
				objTaskModel.setACTUALSTARTDATE(rs.getString(5));
				objTaskModel.setACTUALENDDATE(rs.getString(6));
				objTaskModel.setPLANNEDSTARTDATE(rs.getString(7));
				objTaskModel.setPLANNEDENDDATE(rs.getString(8));
				objTaskModel.setASSIGNED_TO(rs.getString(9));
				objTaskModel.setDEPT_NAME(rs.getString(10));
				objTaskModel.setCREATED_D(rs.getString(11));
				obiListInstanceModel.add(objTaskModel);
				//objInstanceInsertToElasticDemo.insertToElasticToTask(objInstanceModel);
				 i++;
			 }
			 objInstanceInsertToElasticDemo.insertToElasticToTask(obiListInstanceModel);
			 
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	OracleConnection connect() throws SQLException {
      OracleDriver dr = new OracleDriver();
      Properties prop = new Properties();
      prop.setProperty("user", OracleInstanceHit.USERNAME);
      prop.setProperty("password", OracleInstanceHit.PASSWORD);
      return (OracleConnection) dr.connect(OracleInstanceHit.URL, prop);
  }

}
