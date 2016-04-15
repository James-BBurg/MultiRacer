package com.multiracer.dlg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDlgAL implements ActionListener {
	EditDialog u = null;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String rbLabel = e.getActionCommand();
		if(rbLabel.equalsIgnoreCase("edit")) {
			u.doEdit();
		} else if(rbLabel.equalsIgnoreCase("delete")) {
			u.doDelete();
		} else if(rbLabel.equalsIgnoreCase("save")) {
			u.doSave();
		} else if(rbLabel.equalsIgnoreCase("add")) {
			u.doAdd();
		} else if(rbLabel.equalsIgnoreCase("clear")) {
			u.doClear();
		} else if(rbLabel.equalsIgnoreCase("exit")) {
			u.doExit();
		}		
		// TODO Auto-generated method stub

	}
	public EditDlgAL(EditDialog uRef) {
		this.u = uRef;
	}
}
