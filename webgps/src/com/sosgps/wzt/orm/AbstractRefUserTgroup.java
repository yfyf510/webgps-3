package com.sosgps.wzt.orm;



/**
 * AbstractRefUserTgroup generated by MyEclipse - Hibernate Tools
 */

public abstract class AbstractRefUserTgroup  implements java.io.Serializable {


    // Fields    

     private RefUserTgroupId id;
     private TTermGroup TTermGroup;


    // Constructors

    /** default constructor */
    public AbstractRefUserTgroup() {
    }

    
    /** full constructor */
    public AbstractRefUserTgroup(TTermGroup TTermGroup) {
        this.TTermGroup = TTermGroup;
    }

   
    // Property accessors

    public RefUserTgroupId getId() {
        return this.id;
    }
    
    public void setId(RefUserTgroupId id) {
        this.id = id;
    }

    public TTermGroup getTTermGroup() {
        return this.TTermGroup;
    }
    
    public void setTTermGroup(TTermGroup TTermGroup) {
        this.TTermGroup = TTermGroup;
    }
   








}