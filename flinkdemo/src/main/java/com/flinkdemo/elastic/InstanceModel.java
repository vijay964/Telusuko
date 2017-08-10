package com.flinkdemo.elastic;

public class InstanceModel {

	private int INSTANCEID;
	private String CLIENT_NAME;
	private String COUNTRY_NAME;
	private String BU;
	private String DEPT_NAME;
	private int PAYROLL_YEAR;
	private String INSTANCE_STATUS_LOCAL;
	private String CREATED_D;
	
	public int getINSTANCEID() {
		return INSTANCEID;
	}
	public void setINSTANCEID(int iNSTANCEID) {
		INSTANCEID = iNSTANCEID;
	}
	public String getCLIENT_NAME() {
		return CLIENT_NAME;
	}
	public void setCLIENT_NAME(String cLIENT_NAME) {
		CLIENT_NAME = cLIENT_NAME;
	}
	public String getCOUNTRY_NAME() {
		return COUNTRY_NAME;
	}
	public void setCOUNTRY_NAME(String cOUNTRY_NAME) {
		COUNTRY_NAME = cOUNTRY_NAME;
	}
	public String getBU() {
		return BU;
	}
	public void setBU(String bU) {
		BU = bU;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dEPT_NAME) {
		DEPT_NAME = dEPT_NAME;
	}
	public int getPAYROLL_YEAR() {
		return PAYROLL_YEAR;
	}
	public void setPAYROLL_YEAR(int pAYROLL_YEAR) {
		PAYROLL_YEAR = pAYROLL_YEAR;
	}
	public String getINSTANCE_STATUS_LOCAL() {
		return INSTANCE_STATUS_LOCAL;
	}
	public void setINSTANCE_STATUS_LOCAL(String iNSTANCE_STATUS_LOCAL) {
		INSTANCE_STATUS_LOCAL = iNSTANCE_STATUS_LOCAL;
	}
	public String getCREATED_D() {
		return CREATED_D;
	}
	public void setCREATED_D(String cREATED_D) {
		CREATED_D = cREATED_D;
	}
}
