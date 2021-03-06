package com.sosgps.wzt.orm;

// Generated 2010-4-10 16:44:48 by Hibernate Tools 3.2.5.Beta

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TLayers generated by hbm2java
 */
public class TLayers implements java.io.Serializable {

    private Long id;

    private String layerName;

    private String layerDesc;

    private Long useStatus;

    private Date ctdate;

    private String entcode;

    private Long userId;

    private String info1;

    private String info2;

    private String visible;

    private Set refUserLayers = new HashSet(0);

    private Set refLayerPois = new HashSet(0);

    private Long states;

    public Long getStates() {
        return states;
    }

    public void setStates(Long states) {
        this.states = states;
    }

    public TLayers() {
    }

    public TLayers(String layerName, String layerDesc, Long useStatus, Date ctdate, String entcode,
            Long userId, String info1, String info2, String visible, Set refUserLayers,
            Set refLayerPois) {
        this.layerName = layerName;
        this.layerDesc = layerDesc;
        this.useStatus = useStatus;
        this.ctdate = ctdate;
        this.entcode = entcode;
        this.userId = userId;
        this.info1 = info1;
        this.info2 = info2;
        this.visible = visible;
        this.refUserLayers = refUserLayers;
        this.refLayerPois = refLayerPois;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLayerName() {
        return this.layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public String getLayerDesc() {
        return this.layerDesc;
    }

    public void setLayerDesc(String layerDesc) {
        this.layerDesc = layerDesc;
    }

    public Long getUseStatus() {
        return this.useStatus;
    }

    public void setUseStatus(Long useStatus) {
        this.useStatus = useStatus;
    }

    public Date getCtdate() {
        return this.ctdate;
    }

    public void setCtdate(Date ctdate) {
        this.ctdate = ctdate;
    }

    public String getEntcode() {
        return this.entcode;
    }

    public void setEntcode(String entcode) {
        this.entcode = entcode;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getInfo1() {
        return this.info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return this.info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getVisible() {
        return this.visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public Set getRefUserLayers() {
        return this.refUserLayers;
    }

    public void setRefUserLayers(Set refUserLayers) {
        this.refUserLayers = refUserLayers;
    }

    public Set getRefLayerPois() {
        return this.refLayerPois;
    }

    public void setRefLayerPois(Set refLayerPois) {
        this.refLayerPois = refLayerPois;
    }

}
