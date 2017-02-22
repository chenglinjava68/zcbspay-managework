package com.zcbspay.platform.manager.merch.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * TBusiRate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_RATE_BUSI")
public class CommonRateEntity implements java.io.Serializable {

	// Fields

	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4211613220222800243L;
    private Long tid;
	private String rateId;
	private String rateDes;
	private BigDecimal feeRate;
	private BigDecimal minFee;
	private BigDecimal maxFee;
	private String rateType;
	private String inuser;
	private String intime;
	private String notes;
	private String remarks;
	private String minFeeStr;
	private String maxFeeStr;
	private String feeRateStr;
	// Constructors

	/** default constructor */
	public CommonRateEntity() {
	}

	/** minimal constructor */
	public CommonRateEntity(Long tid, String rateId, String rateDes, BigDecimal feeRate) {
		this.tid = tid;
		this.rateId = rateId;
		this.rateDes = rateDes;
		this.feeRate = feeRate;
	}

	/** full constructor */
	public CommonRateEntity(Long tid, String rateId, String rateDes, BigDecimal feeRate,
	        BigDecimal minFee, BigDecimal maxFee, String rateType, String inuser,
			String intime, String notes, String remarks) {
		this.tid = tid;
		this.rateId = rateId;
		this.rateDes = rateDes;
		this.feeRate = feeRate;
		this.minFee = minFee;
		this.maxFee = maxFee;
		this.rateType = rateType;
		this.inuser = inuser;
		this.intime = intime;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "TID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getTid() {
		return this.tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	@Column(name = "RATE_ID", nullable = false, length = 10)
	public String getRateId() {
		return this.rateId;
	}

	public void setRateId(String rateId) {
		this.rateId = rateId;
	}

	@Column(name = "RATE_DESC", nullable = false, length = 64)
	public String getRateDes() {
		return this.rateDes;
	}

	public void setRateDes(String rateDes) {
		this.rateDes = rateDes;
	}

	@Column(name = "FEE_RATE", nullable = false, precision = 4, scale = 0)
	public BigDecimal getFeeRate() {
		return this.feeRate;
	}

	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}

	@Column(name = "MIN_FEE", precision = 12, scale = 0)
	public BigDecimal getMinFee() {
		return this.minFee;
	}

	public void setMinFee(BigDecimal minFee) {
		this.minFee = minFee;
	}

	@Column(name = "MAX_FEE", precision = 12, scale = 0)
	public BigDecimal getMaxFee() {
		return this.maxFee;
	}

	public void setMaxFee(BigDecimal maxFee) {
		this.maxFee = maxFee;
	}

	@Column(name = "RATE_TYPE", length = 2)
	public String getRateType() {
		return this.rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	@Column(name = "INUSER", length = 128)
	public String getInuser() {
		return this.inuser;
	}

	public void setInuser(String inuser) {
		this.inuser = inuser;
	}

	@Column(name = "INTIME", length = 128)
	public String getIntime() {
		return this.intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	@Column(name = "NOTES", length = 128)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "REMARKS", length = 128)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Transient
    public String getMinFeeStr() {
        return minFeeStr;
    }

    public void setMinFeeStr(String minFeeStr) {
        this.minFeeStr = minFeeStr;
    }
    @Transient
    public String getMaxFeeStr() {
        return maxFeeStr;
    }

    public void setMaxFeeStr(String maxFeeStr) {
        this.maxFeeStr = maxFeeStr;
    }
    @Transient
    public String getFeeRateStr() {
        return feeRateStr;
    }

    public void setFeeRateStr(String feeRateStr) {
        this.feeRateStr = feeRateStr;
    }
}