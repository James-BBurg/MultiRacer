package com.multiracer.entity;

import java.util.List;
import java.util.UUID;

public class Race {

	private String name;
	private String id;
	private int numOfLanes;
	private int lanesPerHeat;
	private List<Racer> racers;
	private List<Heat> heats;
	
	public Race() {
		id = "RR_" + UUID.randomUUID().toString();
	}
	
	public String getId() {
		return this.id;
	}
	
	public List<Racer> getRacers() {
		return this.racers;
	}
}
