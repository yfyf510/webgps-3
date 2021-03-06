package com.sosgps.wzt.orm;
// Generated by MyEclipse - Hibernate Tools

import java.util.Date;
import java.util.Set;


/**
 * TEnt generated by MyEclipse - Hibernate Tools
 */
public class TEnt extends AbstractTEnt implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public TEnt() {
    }

	/** minimal constructor */
    public TEnt(String entName, Date openTime, Date endTime, String entStatus, Long feeType) {
        super(entName, openTime, endTime, entStatus, feeType);        
    }
    
    /** full constructor */
    public TEnt(String entName, Date openTime, Date endTime, String entStatus, String centerX, String centerY, Long mapZoom, String logoUrl, Date entCrtDate, Long feeType, Set TTermGroups, Set Kpis) {
        super(entName, openTime, endTime, entStatus, centerX, centerY, mapZoom, logoUrl, entCrtDate, feeType, TTermGroups, Kpis);        
    }
   
}
