package com.sosgps.wzt.orm;
// Generated by MyEclipse - Hibernate Tools

import java.util.Date;


/**
 * TLoginLog generated by MyEclipse - Hibernate Tools
 */
public class TLoginLog extends AbstractTLoginLog implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public TLoginLog() {
    }

	/** minimal constructor */
    public TLoginLog(Long userId, String userName, String empCode, String accessIp, Long result) {
        super(userId, userName, empCode, accessIp, result);        
    }
    
    /** full constructor */
    public TLoginLog(Long userId, String userName, String empCode, String accessIp, Date loginTime, String loginDesc, Long result) {
        super(userId, userName, empCode, accessIp, loginTime, loginDesc, result);        
    }
   
}
