package com.sosgps.wzt.orm;
// Generated by MyEclipse - Hibernate Tools

import java.util.Date;


/**
 * TLocrecord generated by MyEclipse - Hibernate Tools
 */
public class TLocrecord extends AbstractTLocrecord implements java.io.Serializable {

    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    // Constructors

    /** default constructor */
    public TLocrecord() {
    }

    
    /** full constructor */
    public TLocrecord(String deviceId, Float latitude, Float longitude, String jmx, String jmy, Float speed, Float direction, Float height, Float distance, Long statlliteNum, Date gpstime, Date inputdate,String locDesc,String varExt1, String imsi, Float temperature, Float humidity) {
        super(deviceId, latitude, longitude, jmx, jmy, speed, direction, height, distance, statlliteNum, gpstime, inputdate,locDesc, varExt1, imsi, temperature, humidity);   
    }
   
}
