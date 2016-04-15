package com.multiracer.entity;

public class Member implements Comparable {

	private String firstName;
	private String lastName;
	private String nickName;
	private int tempIdx;
	
	public Member() {
		firstName = "";
		lastName = "";
		nickName = "";
		tempIdx = -1;
	}
	public Member(Member org) {
		firstName = org.getFirstName();
		lastName = org.getLastName();
		nickName = org.getNickName();
		tempIdx = org.getTempIdx();
	}
	
	public int getTempIdx() {
		return tempIdx;
	}
	public void setTempIdx(int i) {
		tempIdx = i;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * Generate a HTML string so display data
	 * @return
	 */
	public String displayMember() {
		StringBuilder sb = new StringBuilder();
		String formatStr1 = "<html><table><tr><td>%s</td><td>%s</td><td>(%s)</td></tr></table></html>";
		String formatStr2 = "<html><table><tr><td>%s</td><td>%s</td></tr></table></html>";
		
		if(nickName.length() > 0)
			return String.format(formatStr1, lastName, firstName, nickName);
		else
			return String.format(formatStr2, lastName, firstName);
	}
	
	public void parseArchive(String arch) {
		String [] tmp = arch.split(",");
		if(tmp.length > 0)
			this.lastName = tmp[0].trim().toUpperCase();
		if(tmp.length > 1)
			this.firstName = tmp[1].trim().toUpperCase();
		if(tmp.length > 2)
			this.nickName = tmp[2].trim().toUpperCase();
	}
	public String toArchiveString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(lastName);
		sb.append(",");
		sb.append(firstName);
		sb.append(",");
		sb.append(nickName);
		return sb.toString();
	}
	@Override
	public int compareTo(Object o) {
		int iResult = 0;
		if(o instanceof Member) {
			if((iResult = this.lastName.compareTo(((Member)o).getLastName())) == 0) {
				if((iResult = this.firstName.compareTo(((Member)o).getFirstName())) == 0) {
					iResult = this.nickName.compareTo(((Member)o).getNickName());
				}
			}
		}
		return iResult;
	}
	
}
