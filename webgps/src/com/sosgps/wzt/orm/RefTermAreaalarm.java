package com.sosgps.wzt.orm;

// Generated 2010-4-10 9:20:50 by Hibernate Tools 3.2.5.Beta

/**
 * RefTermAreaalarm generated by hbm2java
 */
public class RefTermAreaalarm implements java.io.Serializable {

	private Long id;
	private TArea TArea;
	private TTerminal TTerminal;
	private Long alarmType;
	private String startTime;
	private String endTime;

	public RefTermAreaalarm() {
	}

	public RefTermAreaalarm(TArea TArea, TTerminal TTerminal, Long alarmType) {
		this.TArea = TArea;
		this.TTerminal = TTerminal;
		this.alarmType = alarmType;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TArea getTArea() {
		return this.TArea;
	}

	public void setTArea(TArea TArea) {
		this.TArea = TArea;
	}

	public TTerminal getTTerminal() {
		return this.TTerminal;
	}

	public void setTTerminal(TTerminal TTerminal) {
		this.TTerminal = TTerminal;
	}

	public Long getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Long alarmType) {
		this.alarmType = alarmType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
