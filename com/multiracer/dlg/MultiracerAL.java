package com.multiracer.dlg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiracerAL implements ActionListener {

	MultiracerDlg u;
	
	public MultiracerAL(MultiracerDlg uRef) {
		this.u = uRef;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String rbLabel = e.getActionCommand();
		
		if(rbLabel.equalsIgnoreCase("edit")) {
			System.out.println("Menu edit chosen");
			this.u.doEdit();
		} else if(rbLabel.equalsIgnoreCase("load")) {
			System.out.println("Menu load chosen");
		} else if(rbLabel.equalsIgnoreCase("save")) {
			System.out.println("Menu save chosen");
		} else if(rbLabel.equalsIgnoreCase("create")) {
			this.u.doCreateRace();
			System.out.println("Menu create");
		}

	}

}
