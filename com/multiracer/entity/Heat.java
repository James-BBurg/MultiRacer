package com.multiracer.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Heat {

	private String id;
	private String raceId;
	private List<HeatNode> heats;
	
	public Heat() {
		id = "HH_" + UUID.randomUUID().toString();
		heats = new ArrayList<HeatNode>();
	}

	public Heat(String raceId) {
		id = "HH_" + UUID.randomUUID().toString();
		this.raceId = raceId;
		heats = new ArrayList<HeatNode>();
	}
	public String getId() {
		return id;
	}

	public String getRaceId() {
		return raceId;
	}

	public List<HeatNode> getHeats() {
		return heats;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}

	public void setHeats(List<HeatNode> heats) {
		this.heats = heats;
	}
	public void addHeatNode(HeatNode node) {
		if(this.heats == null)
			this.heats = new ArrayList<HeatNode>();
		this.heats.add(node);
	}
}
