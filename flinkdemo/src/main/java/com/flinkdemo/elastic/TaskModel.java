package com.flinkdemo.elastic;

public class TaskModel {
	

	private int INSTANCEID;
	private int TASK_ID;
	private String TASKNAME;
	private String LOCALTASKNAME;
	private String ACTUALSTARTDATE;
	private String ACTUALENDDATE;
	private String PLANNEDSTARTDATE;
	private String PLANNEDENDDATE;
	private String CREATED_D;
	private String ASSIGNED_TO;
	private String DEPT_NAME;
	
	
	public int getINSTANCEID() {
		return INSTANCEID;
	}
	public void setINSTANCEID(int iNSTANCEID) {
		INSTANCEID = iNSTANCEID;
	}
	public int getTASK_ID() {
		return TASK_ID;
	}
	public void setTASK_ID(int tASK_ID) {
		TASK_ID = tASK_ID;
	}
	public String getTASKNAME() {
		return TASKNAME;
	}
	public void setTASKNAME(String tASKNAME) {
		TASKNAME = tASKNAME;
	}
	public String getLOCALTASKNAME() {
		return LOCALTASKNAME;
	}
	public void setLOCALTASKNAME(String lOCALTASKNAME) {
		LOCALTASKNAME = lOCALTASKNAME;
	}
	
	public String getASSIGNED_TO() {
		return ASSIGNED_TO;
	}
	public void setASSIGNED_TO(String aSSIGNED_TO) {
		ASSIGNED_TO = aSSIGNED_TO;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dEPT_NAME) {
		DEPT_NAME = dEPT_NAME;
	}
	public String getACTUALSTARTDATE() {
		return ACTUALSTARTDATE;
	}
	public void setACTUALSTARTDATE(String aCTUALSTARTDATE) {
		ACTUALSTARTDATE = aCTUALSTARTDATE;
	}
	public String getACTUALENDDATE() {
		return ACTUALENDDATE;
	}
	public void setACTUALENDDATE(String aCTUALENDDATE) {
		ACTUALENDDATE = aCTUALENDDATE;
	}
	public String getPLANNEDSTARTDATE() {
		return PLANNEDSTARTDATE;
	}
	public void setPLANNEDSTARTDATE(String pLANNEDSTARTDATE) {
		PLANNEDSTARTDATE = pLANNEDSTARTDATE;
	}
	public String getPLANNEDENDDATE() {
		return PLANNEDENDDATE;
	}
	public void setPLANNEDENDDATE(String pLANNEDENDDATE) {
		PLANNEDENDDATE = pLANNEDENDDATE;
	}
	public String getCREATED_D() {
		return CREATED_D;
	}
	public void setCREATED_D(String cREATED_D) {
		CREATED_D = cREATED_D;
	}
	

}
