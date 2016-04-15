package com.multiracer.entity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Roster {

	List<Member> racerList;
	
	public Roster() {
		this.racerList = new ArrayList<Member>();		
	}

	public List<Member> getRacerList() {
		return racerList;
	}

	public void setRacerList(List<Member> racerList) {
		this.racerList = racerList;
	}
	
	public void addRacerToList(Member newMember) {
		if(this.racerList == null) 
			this.racerList = new ArrayList<Member>();
		
		this.racerList.add(newMember);
	}
	public void clear() {
		racerList.clear();
	}
	public void loadRosterFile(String filename) {

		BufferedReader br = null;
		String currLine;
		
		try {
			br = new BufferedReader(new FileReader(filename));
			while((currLine = br.readLine()) != null) {
				Member curr = new Member();
				curr.parseArchive(currLine);
				racerList.add(curr);
			}
			br.close();
		} catch(IOException  ioe) {
			System.out.println("Error processing roster data.");
			ioe.printStackTrace();			
		}
	}
	/**
	 * Format of the file will be 
	 * each line contains one member record, each record 
	 * @param filename
	 */
	public void saveRosterFile(String filename) {

		BufferedWriter bw = null;
		String currLine;
		
		try {
			bw = new BufferedWriter(new FileWriter(filename));
			for(Member curr : racerList) {
				bw.write(curr.toArchiveString());
				bw.write("\n");
			}
			bw.close();
		} catch(IOException ioe) {
			System.out.println("Error writing out roster");
			ioe.printStackTrace();
		}
	}
}
