package com.sosgps.wzt.orm;
// Generated by MyEclipse - Hibernate Tools

import java.util.Date;


/**
 * TOptLog generated by MyEclipse - Hibernate Tools
 */
public class TOptLog extends AbstractTOptLog implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public TOptLog() {
    }

    
    /** full constructor */
    public TOptLog(Long userId, String userName, String empCode, Date optTime, String accessIp, String optDesc, String funFType, String funCType, Long result) {
        super(userId, userName, empCode, optTime, accessIp, optDesc, funFType, funCType, result);        
    }
   
}
