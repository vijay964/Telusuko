package com.flinkdemo.elastic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;

public class GetDataForInstance {
	
	//TODO:: Provide username, password and URL
	 static final String USERNAME = "username";
	 static final String PASSWORD = "password";
	 static String URL = "jdbc:oracle:thin:@domain:port/instanceName";
	 
	public static void main(String[] args) {
		try{
			GetDataForInstance obj = new  GetDataForInstance();
			obj.test();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void test(){
		System.out.println("iN tEST ........");
		OracleConnection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<InstanceModel> obiListInstanceModel= new ArrayList<InstanceModel>();
		InstanceInsertToElasticDemo objInstanceInsertToElasticDemo= new InstanceInsertToElasticDemo();
		int i=1;
		try{
			 conn = connect();
			  stmt = conn.createStatement();
			  //TODO :: Provide table name
			  rs = stmt.executeQuery("SELECT BU,CLIENT_NAME,INSTANCEID,INSTANCE_STATUS_LOCAL,COUNTRY_NAME,DEPT_NAME,PAYROLL_YEAR,to_char(CREATED_D, 'yyyy-mm-dd hh:mi:ss AM') FROM Table_Name");
			 while(rs.next()){
				System.out.println("MY III "+i);
				 InstanceModel objInstanceModel= new InstanceModel();
				 objInstanceModel.setBU(rs.getString(1));
				 objInstanceModel.setCLIENT_NAME(rs.getString(2));
				 objInstanceModel.setINSTANCEID(rs.getInt(3));
				 objInstanceModel.setINSTANCE_STATUS_LOCAL(rs.getString(4));
				 objInstanceModel.setCOUNTRY_NAME(rs.getString(5));
				 objInstanceModel.setDEPT_NAME(rs.getString(6));
				 objInstanceModel.setPAYROLL_YEAR(rs.getInt(7));
				 objInstanceModel.setCREATED_D(rs.getString(8));
				 obiListInstanceModel.add(objInstanceModel);
				 i++;
			 }
			 objInstanceInsertToElasticDemo.insertToElastic(obiListInstanceModel);
			 
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
