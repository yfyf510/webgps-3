package com.sosgps.wzt.orm;
// Generated by MyEclipse - Hibernate Tools

import java.util.Date;
import java.util.Set;


/**
 * TTerminalType generated by MyEclipse - Hibernate Tools
 */
public class TEntTermtype extends AbstractTEntTermtype implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public TEntTermtype() {
    }

    
    /** full constructor */
    public TEntTermtype(String typeName, Date inputdate, String createman, Set TTerminals) {
        super(typeName, inputdate, createman, TTerminals);        
    }
   
}