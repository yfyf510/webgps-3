package com.sosgps.wzt.orm;

import java.util.Date;


/**
 * AbstractTCallRecord generated by MyEclipse - Hibernate Tools
 */

public abstract class AbstractTCallRecord  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String callerNumber;
     private String calledNumber;
     private Date calleDate;
     private String deviceId;
     private String callType;
     private String content;
     private String memo;


    // Constructors

    /** default constructor */
    public AbstractTCallRecord() {
    }

    
    /** full constructor */
    public AbstractTCallRecord(String callerNumber, String calledNumber, Date calleDate, String deviceId, String callType, String content, String memo) {
        this.callerNumber = callerNumber;
        this.calledNumber = calledNumber;
        this.calleDate = calleDate;
        this.deviceId = deviceId;
        this.callType = callType;
        this.content = content;
        this.memo = memo;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getCallerNumber() {
        return this.callerNumber;
    }
    
    public void setCallerNumber(String callerNumber) {
        this.callerNumber = callerNumber;
    }

    public String getCalledNumber() {
        return this.calledNumber;
    }
    
    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public Date getCalleDate() {
        return this.calleDate;
    }
    
    public void setCalleDate(Date calleDate) {
        this.calleDate = calleDate;
    }

    public String getDeviceId() {
        return this.deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCallType() {
        return this.callType;
    }
    
    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
   








}