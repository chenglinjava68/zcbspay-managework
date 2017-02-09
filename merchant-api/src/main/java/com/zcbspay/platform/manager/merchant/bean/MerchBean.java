package com.zcbspay.platform.manager.merchant.bean;

import java.io.Serializable;
import java.util.Date;

import com.zcbspay.platform.manager.pojo.Money;

public class MerchBean implements Serializable{

	private static final long serialVersionUID = -879142983602551970L;

	/**主键**/
    private Long merchId;
    /**会员外键**/
    private MemberBean member;
    /**会员号**/
    private String memberId;
    /**上级商户**/
    private String parent;
    /**清算周期**/
    private Long setlCycle;
    /**清算类型**/
    private String  setlType;
    /**结算行号**/
    private String bankCode;
    /**开户行号**/
    private String bankNode;
    /**开户账号**/
    private String accNum;
    /**开户名**/
    private String accName;
    /**服务费**/
    private Money charge;
    /**保证金**/
    private Money deposit;
    /**合约开始日期**/
    private Date agreemtStart;
    /**合约终止日期**/
    private Date agreemtEnd;
    /**产品代码**/
    private String prdtVer;
    /**扣率版本**/
    private String feeVer;
    /**分润版本**/
    private String spiltVer;
    /**风控版本**/
    private String riskVer;
    /**路由版本**/
    private String routVer;
    /**状态**/
    private String merchStatus;
    /**写入人**/
    private Long mInUser;
    /**写入时间**/
    private Date mInTime;
    /**初审人**/
    private Long stexaUser;
    /**初审时间**/
    private Date stexaTime;
    /**初审意见**/
    private String stexaOpt;
    /**复核人**/
    private Long cvlexaUser;
    /**复核时间**/
    private Date cvlexaTime;
    /**复核意见**/
    private String cvlexaOpt;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
    /**活动状态**/
    private String activeteStatus;
    /**付款卡清算行号**/
    private String payBankCode;
    /**付款卡开户行号**/
    private String payBankNode;
    /**付款卡开户账号**/
    private String payAccNum;
    /**付款卡开户名**/
    private String payAccName;
    
	public Long getMerchId() {
		return merchId;
	}
	public void setMerchId(Long merchId) {
		this.merchId = merchId;
	}
	
	public MemberBean getMember() {
		return member;
	}
	public void setMember(MemberBean member) {
		this.member = member;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public Long getSetlCycle() {
		return setlCycle;
	}
	public void setSetlCycle(Long setlCycle) {
		this.setlCycle = setlCycle;
	}
	public String getSetlType() {
		return setlType;
	}
	public void setSetlType(String setlType) {
		this.setlType = setlType;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankNode() {
		return bankNode;
	}
	public void setBankNode(String bankNode) {
		this.bankNode = bankNode;
	}
	public String getAccNum() {
		return accNum;
	}
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public Money getCharge() {
		return charge;
	}
	public void setCharge(Money charge) {
		this.charge = charge;
	}
	public Money getDeposit() {
		return deposit;
	}
	public void setDeposit(Money deposit) {
		this.deposit = deposit;
	}
	public Date getAgreemtStart() {
		return agreemtStart;
	}
	public void setAgreemtStart(Date agreemtStart) {
		this.agreemtStart = agreemtStart;
	}
	public Date getAgreemtEnd() {
		return agreemtEnd;
	}
	public void setAgreemtEnd(Date agreemtEnd) {
		this.agreemtEnd = agreemtEnd;
	}
	public String getPrdtVer() {
		return prdtVer;
	}
	public void setPrdtVer(String prdtVer) {
		this.prdtVer = prdtVer;
	}
	public String getFeeVer() {
		return feeVer;
	}
	public void setFeeVer(String feeVer) {
		this.feeVer = feeVer;
	}
	public String getSpiltVer() {
		return spiltVer;
	}
	public void setSpiltVer(String spiltVer) {
		this.spiltVer = spiltVer;
	}
	public String getRiskVer() {
		return riskVer;
	}
	public void setRiskVer(String riskVer) {
		this.riskVer = riskVer;
	}
	public String getRoutVer() {
		return routVer;
	}
	public void setRoutVer(String routVer) {
		this.routVer = routVer;
	}
	public String getMerchStatus() {
		return merchStatus;
	}
	public void setMerchStatus(String merchStatus) {
		this.merchStatus = merchStatus;
	}
	public Long getmInUser() {
		return mInUser;
	}
	public void setmInUser(Long mInUser) {
		this.mInUser = mInUser;
	}
	public Date getmInTime() {
		return mInTime;
	}
	public void setmInTime(Date mInTime) {
		this.mInTime = mInTime;
	}
	public Long getStexaUser() {
		return stexaUser;
	}
	public void setStexaUser(Long stexaUser) {
		this.stexaUser = stexaUser;
	}
	public Date getStexaTime() {
		return stexaTime;
	}
	public void setStexaTime(Date stexaTime) {
		this.stexaTime = stexaTime;
	}
	public String getStexaOpt() {
		return stexaOpt;
	}
	public void setStexaOpt(String stexaOpt) {
		this.stexaOpt = stexaOpt;
	}
	public Long getCvlexaUser() {
		return cvlexaUser;
	}
	public void setCvlexaUser(Long cvlexaUser) {
		this.cvlexaUser = cvlexaUser;
	}
	public Date getCvlexaTime() {
		return cvlexaTime;
	}
	public void setCvlexaTime(Date cvlexaTime) {
		this.cvlexaTime = cvlexaTime;
	}
	public String getCvlexaOpt() {
		return cvlexaOpt;
	}
	public void setCvlexaOpt(String cvlexaOpt) {
		this.cvlexaOpt = cvlexaOpt;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getActiveteStatus() {
		return activeteStatus;
	}
	public void setActiveteStatus(String activeteStatus) {
		this.activeteStatus = activeteStatus;
	}
	public String getPayBankCode() {
		return payBankCode;
	}
	public void setPayBankCode(String payBankCode) {
		this.payBankCode = payBankCode;
	}
	public String getPayBankNode() {
		return payBankNode;
	}
	public void setPayBankNode(String payBankNode) {
		this.payBankNode = payBankNode;
	}
	public String getPayAccNum() {
		return payAccNum;
	}
	public void setPayAccNum(String payAccNum) {
		this.payAccNum = payAccNum;
	}
	public String getPayAccName() {
		return payAccName;
	}
	public void setPayAccName(String payAccName) {
		this.payAccName = payAccName;
	}
	
}
