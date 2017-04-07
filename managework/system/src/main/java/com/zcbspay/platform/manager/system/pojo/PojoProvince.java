package com.zcbspay.platform.manager.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("all")
@Entity
@Table(name = "T_PROVINCE")
public class PojoProvince implements java.io.Serializable {


	// Fields

	private String PId;
	private String PName;
	private String PCode;

	// Constructors

	@Id
	@Column(name = "P_ID")
	public String getPId() {
		return this.PId;
	}

	public void setPId(String PId) {
		this.PId = PId;
	}

	@Column(name = "P_NAME", length = 20)
	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	@Column(name = "P_CODE", length = 4)
	public String getPCode() {
		return this.PCode;
	}

	public void setPCode(String PCode) {
		this.PCode = PCode;
	}
}