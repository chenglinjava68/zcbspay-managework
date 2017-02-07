package com.zcbspay.platform.manager.merch.bean;

import java.util.Date;

public class CashBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7888626843029048621L;
	private Long cashid;
	private String cashver;
	private String cashname;
	private String status;
	private Date intime;
	private Long inuser;
	private Date uptime;
	private Long upuser;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public CashBean() {
	}

	/** minimal constructor */
	public CashBean(Long cashid, String cashver, String cashname, Date intime) {
		this.cashid = cashid;
		this.cashver = cashver;
		this.cashname = cashname;
		this.intime = intime;
	}

	/** full constructor */
	public CashBean(Long cashid, String cashver, String cashname, String status,
			Date intime, Long inuser, Date uptime, Long upuser, String notes,
			String remarks) {
		this.cashid = cashid;
		this.cashver = cashver;
		this.cashname = cashname;
		this.status = status;
		this.intime = intime;
		this.inuser = inuser;
		this.uptime = uptime;
		this.upuser = upuser;
		this.notes = notes;
		this.remarks = remarks;
	}

	public Long getCashid() {
		return this.cashid;
	}

	public void setCashid(Long cashid) {
		this.cashid = cashid;
	}

	public String getCashver() {
		return this.cashver;
	}

	public void setCashver(String cashver) {
		this.cashver = cashver;
	}

	public String getCashname() {
		return this.cashname;
	}

	public void setCashname(String cashname) {
		this.cashname = cashname;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getIntime() {
		return this.intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public Long getInuser() {
		return this.inuser;
	}

	public void setInuser(Long inuser) {
		this.inuser = inuser;
	}

	public Date getUptime() {
		return this.uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public Long getUpuser() {
		return this.upuser;
	}

	public void setUpuser(Long upuser) {
		this.upuser = upuser;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}