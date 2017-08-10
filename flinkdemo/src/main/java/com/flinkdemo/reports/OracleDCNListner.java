package com.flinkdemo.reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.dcn.DatabaseChangeEvent;
import oracle.jdbc.dcn.DatabaseChangeListener;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import oracle.sql.ROWID;

public class OracleDCNListner {
	
	//TODO :: Provide username and password
	 static final String USERNAME = "username";
	 static final String PASSWORD = "password";
	 static String URL = "jdbc:oracle:thin:@localhost:1521/xe";

    public static void main(String[] args) {
    	OracleDCNListner oracleDCN = new OracleDCNListner();
        try {
            oracleDCN.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void run() throws Exception{
        OracleConnection conn = connect();
        Properties prop = new Properties();
        prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true");
        DatabaseChangeRegistration dcr = conn.registerDatabaseChangeNotification(prop);

        try{
            dcr.addListener(new DatabaseChangeListener() {

                public void onDatabaseChangeNotification(DatabaseChangeEvent dce) {
                    System.out.println("Changed row id : "+dce.getTableChangeDescription()[0].getRowChangeDescription()[0].getRowid().stringValue());
                    getChangedData(dce.getTableChangeDescription()[0].getRowChangeDescription()[0].getRowid());
                    		
                }
            });

            Statement stmt = conn.createStatement();
            ((OracleStatement) stmt).setDatabaseChangeRegistration(dcr);
            //TODO :: Please provide table name
            ResultSet rs = stmt.executeQuery("select * from TableName");
            while (rs.next()) {
            }
            rs.close();
            stmt.close();
        }catch(SQLException ex){
            if (conn != null)
            {
                conn.unregisterDatabaseChangeNotification(dcr);
                conn.close();
            }
            throw ex;
        }
    }
    
    public void getChangedData(ROWID id){
    	System.out.println("IN getChangedData"+id);
    	ResultSet rs=null;
    	Statement stmt=null;
    	OracleConnection conn=null;
    	InstanceModel objInstanceModel= null;
    	TestInstanceProduceMessage objInstanceProduceMessage=null;
    	try{
    		 conn = connect();
    		 stmt = conn.createStatement();
    		 String sql="SELECT INSTANCE_ID,DEPT,BU,DT FROM _INSTNCE_FINAL WHERE ROWID='"+id.stringValue()+"'";
    		 System.out.println(sql);
    		 rs= stmt.executeQuery(sql);
    		while(rs.next()){
    			objInstanceModel= new InstanceModel();
    			objInstanceProduceMessage= new InstanceProduceMessage();
    			objInstanceModel.setINSTANCE_ID(rs.getInt("INSTANCE_ID"));
    			objInstanceModel.setDEPT(rs.getString("DEPT"));
    			objInstanceModel.setBU(rs.getString("BU"));
    			objInstanceModel.setDT(rs.getDate("DT"));
    			objInstanceProduceMessage.putMessageToKafka(objInstanceModel);
    		}
    		
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	finally {
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
        prop.setProperty("user", OracleDCNListner.USERNAME);
        prop.setProperty("password", OracleDCNListner.PASSWORD);
        return (OracleConnection) dr.connect(OracleDCNListner.URL, prop);
    }
}