package com.flinkdemo.reports;

import java.io.Serializable;
import java.util.Date;

public class InstanceModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int  INSTANCE_ID;
	private Date DT;
	private String BU;
	private String DEPT;
	
	
	public int getINSTANCE_ID() {
		return INSTANCE_ID;
	}
	public void setINSTANCE_ID(int iNSTANCE_ID) {
		INSTANCE_ID = iNSTANCE_ID;
	}
	public Date getDT() {
		return DT;
	}
	public void setDT(Date dT) {
		DT = dT;
	}
	public String getBU() {
		return BU;
	}
	public void setBU(String bU) {
		BU = bU;
	}
	public String getDEPT() {
		return DEPT;
	}
	public void setDEPT(String dEPT) {
		DEPT = dEPT;
	}
	
	
}
