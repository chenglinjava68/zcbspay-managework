/* 
 * PojoBusiness.java  
 * 
 * version TODO
 *
 * 2015年10月14日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.manager.merchant.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_BUSINESS")
public class PojoBusiness {
    private Long busiId;
    
    private String busiCode;
    
    private String busiName;
    
    private String busiType;
    
    private Character riskFlag;
    
    private Integer status;
    
    private String notes;
    @Id
    @Column(name = "BUSIID")
    public Long getBusiId() {
        return busiId;
    }
    @Column(name="BUSICODE")
    public String getBusiCode() {
        return busiCode;
    }
    @Column(name="BUSINAME")
    public String getBusiName() {
        return busiName;
    }
    @Column(name="BUSITYPE")
    public String getBusiType() {
        return busiType;
    }
    @Column(name="RISKFLAG")
    public Character getRiskFlag() {
        return riskFlag;
    }
    @Column(name="STATUS")
    public Integer getStatus() {
        return status;
    }
    @Column(name="NOTES")
    public String getNotes() {
        return notes;
    }

    public void setBusiId(Long busiId) {
        this.busiId = busiId;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public void setRiskFlag(Character riskFlag) {
        this.riskFlag = riskFlag;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
