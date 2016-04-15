package com.multiracer.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HeatNode {
	private String id;
	private String heatId;
	List<HeatElement> lanes;
	
	public HeatNode() {
		id = "HN_" + UUID.randomUUID().toString();
		lanes = new ArrayList<HeatElement>();
	}

	public HeatNode(String heatId) {
		id = "HN_" + UUID.randomUUID().toString();
		lanes = new ArrayList<HeatElement>();
		this.heatId = heatId; 
	}

	public String getId() {
		return id;
	}

	public String getHeatId() {
		return heatId;
	}

	public List<HeatElement> getLanes() {
		return lanes;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setHeatId(String heatId) {
		this.heatId = heatId;
	}

	public void setLanes(List<HeatElement> lanes) {
		this.lanes = lanes;
	}
	public void addHeatElement(HeatElement node) {
		lanes.add(node);
	}
}
