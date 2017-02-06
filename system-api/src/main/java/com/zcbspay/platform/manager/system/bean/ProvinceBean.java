package com.zcbspay.platform.manager.system.bean;

import java.io.Serializable;
/**
 *城市名
 */
public class ProvinceBean implements Serializable{

	private static final long serialVersionUID = 2198930296623009826L;
	
	private Long PId;
	private String PName;
	private String PCode;
	public Long getPId() {
		return PId;
	}
	public void setPId(Long pId) {
		PId = pId;
	}
	public String getPName() {
		return PName;
	}
	public void setPName(String pName) {
		PName = pName;
	}
	public String getPCode() {
		return PCode;
	}
	public void setPCode(String pCode) {
		PCode = pCode;
	}
}
