package com.multiracer.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Racer extends Member {

	private String id;
	private String raceId;
	private String number;
	private List<String> heatIds;
	
	public Racer() {
		super();
		this.raceId = "";
		this.number = "";
		heatIds = new ArrayList<String>();
		id = UUID.randomUUID().toString();
	}
	public Racer(String raceId) {
		super();
		heatIds = new ArrayList<String>();
		id = UUID.randomUUID().toString();
		this.raceId = raceId;
		this.number = "";
	}
	public String getId() {
		return id;
	}
	public String getRaceId() {
		return raceId;
	}
	public List<String> getHeatIds() {
		return heatIds;
	}
	public void addHeatId(String heatId) {
		heatIds.add(heatId);
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}
	public void setHeatIds(List<String> heatIds) {
		this.heatIds = heatIds;
	}
	public int getNumberInt() {
		int i = 0;
		
		try {
			i = Integer.parseInt(this.number);
		}catch(NumberFormatException nfe) {
			i = 0;
		}
		return i;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String displayRacer() {
		StringBuilder sb = new StringBuilder();
		String formatStr1 = "<html><table><tr><td>%s</td><td>-</td><td>%s</td><td>%s</td><td>(%s)</td></tr></table></html>";
		String formatStr2 = "<html><table><tr><td>%s</td><td>-</td><td>%s</td><td>%s</td></tr></table></html>";
		
		if(this.getNickName().length() > 0)
			return String.format(formatStr1, number, this.getLastName(), this.getFirstName(), this.getNickName());
		else
			return String.format(formatStr2, number, this.getLastName(), this.getFirstName());
	}
	@Override
	public int compareTo(Object o) {
		int iResult = 0;
		if(o instanceof Racer) {
			int i,j;
			i = this.getNumberInt();
			j = ((Racer)o).getNumberInt();
			if(i > j)
				iResult = 1;
			else if(j > i)
				iResult = -1;
			else if((iResult = this.getLastName().compareTo(((Racer)o).getLastName())) == 0) {
					if((iResult = this.getFirstName().compareTo(((Racer)o).getFirstName())) == 0) {
						iResult = this.getNickName().compareTo(((Racer)o).getNickName());
					}
				}
		}
		return iResult;
	}
	
}
