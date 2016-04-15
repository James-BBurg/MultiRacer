package com.multiracer.entity;

import java.util.UUID;

public class HeatElement {

	private String id;
	private String heatNodeId;
	private String racerId;
	private int lane;
	private int result;
	
	
	public HeatElement() {
		id = "HE_" + UUID.randomUUID().toString();
		racerId = "";
		heatNodeId = "";
	}

	public HeatElement(String racerId, String heatNodeId) {
		id = "HE_" + UUID.randomUUID().toString();
		this.racerId = racerId;
		this.heatNodeId = heatNodeId;		
	}
	
	public String getId() {
		return id;
	}


	public String getHeatNodeId() {
		return heatNodeId;
	}


	public String getRacerId() {
		return racerId;
	}


	public int getLane() {
		return lane;
	}


	public int getResult() {
		return result;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setHeatNodeId(String heatNodeId) {
		this.heatNodeId = heatNodeId;
	}


	public void setRacerId(String racerId) {
		this.racerId = racerId;
	}


	public void setLane(int lane) {
		this.lane = lane;
	}


	public void setResult(int result) {
		this.result = result;
	}	
}
