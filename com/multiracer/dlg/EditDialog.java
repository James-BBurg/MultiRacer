package com.multiracer.dlg;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.multiracer.entity.Member;
import com.multiracer.entity.Roster;

public class EditDialog extends JDialog{
	public final static int EDITING = 1;
	public final static int DELETING = 2;
	public final static int SAVE = 3;
	public final static int ADD = 4;
	public final static int NEUTRAL = 0;
	

	private JPanel editPanel;
	
	private JTextField lastName;
	private JTextField firstName;
	private JTextField nickName;
	
	private JButton edit;
	private JButton add_save;
	private JButton clear;
	private JButton delete;
	private JButton exit;
	
	private JLabel lblLast;
	private JLabel lblFirst;
	private JLabel lblNick;
	private JLabel lblMsg;
	
	private JCheckBox clearOnSave;
	private Roster roster;
    private JList 			 rosterList = null;
    private DefaultListModel rosterModel = null;
    private JScrollPane      rosterPane = null;

    private Member backup;
    
	public EditDialog(JFrame parent) {
	
        super(parent, "Edit Roster", true);
        setMinimumSize(new Dimension(420, 400));

        GridBagConstraints c = new GridBagConstraints();

        editPanel = new JPanel();
		getContentPane().add(editPanel);
		
		setupDlg();
	}
	private void loadFromRoster() {
		rosterModel.clear();
		int idx = 0;
		for(Member curr : roster.getRacerList()) {
			curr.setTempIdx(idx);
			rosterModel.add(idx, curr.displayMember());
			idx++;
		}
	}
	private void updToRoster() {
		int idx = 0;
		Member curr;
		String temp;
		roster.clear();
		
		for(idx = 0; idx < rosterModel.getSize(); idx++) {
			temp = rosterModel.get(idx).toString();
			curr = new Member();
			curr.parseArchive(temp);
			roster.addRacerToList(curr);
		}
	}
	public void setList(Roster orgRoster) {
		roster = orgRoster;
		loadFromRoster();
	}
	
	private void setupDlg() {
		this.setTitle("Edit Roster");
        Border bType = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

		//      																 x    y    w    h
        edit        = new JButton("Edit");					   edit.setBounds(  10, 320,  80,  25);
		add_save	= new JButton("Add");				   add_save.setBounds( 590, 115,  80,  25);
		clear 		= new JButton("Clear");				      clear.setBounds( 500, 115,  80,  25);
		delete 		= new JButton("Delete");			     delete.setBounds( 110, 320,  80,  25);
		exit 		= new JButton("Exit");				       exit.setBounds( 590, 320,  80,  25);
		
		lblFirst	= new JLabel("First Name");		   	   lblFirst.setBounds( 340,  10,  80,  25);
		lblLast		= new JLabel("Last Name");			    lblLast.setBounds( 340,  45,  80,  25);
		lblNick		= new JLabel("Nick Name");			    lblNick.setBounds( 340,  80,  80,  25);
		lblMsg		= new JLabel("");					     lblMsg.setBounds( 340, 110, 320,  25);
		
		firstName 	= new JTextField();					  firstName.setBounds( 430,  10, 240,  25);
		lastName 	= new JTextField();					   lastName.setBounds( 430,  45, 240,  25);
		nickName	= new JTextField();					   nickName.setBounds( 430,  80, 240,  25);
		
		clearOnSave = new JCheckBox("Clear on Save");	clearOnSave.setBounds( 340, 115, 120,  25);
		
		rosterModel = new DefaultListModel();
		rosterList = new JList(rosterModel);
		rosterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rosterPane = new JScrollPane(rosterList);		 rosterPane.setBounds(  10,  10, 320, 300);
		rosterPane.setBorder(bType);
		
		EditDlgAL al = new EditDlgAL(this);
		
		edit.addActionListener(al);
		add_save.addActionListener(al);
		clear.addActionListener(al);
		delete.addActionListener(al);
		exit.addActionListener(al);
		
		contentPane.add(edit);
		contentPane.add(add_save);
		contentPane.add(clear);
		contentPane.add(delete);
		contentPane.add(exit);
		contentPane.add(lblLast);
		contentPane.add(lblFirst);
		contentPane.add(lblNick);
		contentPane.add(lblMsg);
		contentPane.add(lastName);
		contentPane.add(firstName);
		contentPane.add(nickName);
		contentPane.add(clearOnSave);
		contentPane.add(rosterPane);
	}
	

	public void doEdit(){
		int currIdx;
		
		if(!rosterList.isSelectionEmpty()) {
			currIdx = rosterList.getSelectedIndex();
			for(Member curr : roster.getRacerList()){
				if(curr.getTempIdx() == currIdx) {
					backup = new Member(curr);						// Make a backup copy of the current selected 
					firstName.setText(curr.getFirstName());
					lastName.setText(curr.getLastName());
					nickName.setText(curr.getNickName());
				}
			}
			add_save.setText("Save");
		}
	}
	public void doAdd() {
		String fName = firstName.getText().toUpperCase();
		String lName = lastName.getText().toUpperCase();
		String nName = nickName.getText().toUpperCase();
		StringBuilder sb = new StringBuilder();

		// Validate data
		if(fName.length() == 0)
			sb.append("First Name must have value. ");
		if(lName.length() == 0)
			sb.append("Last Name must have value. ");
		
		if(sb.length() > 0) {
			lblMsg.setText(sb.toString());
			return;
		}
		boolean bFound = false;
		for(Member curr : roster.getRacerList()) {		// Search existing list, don't add duplicates
			if((curr.getLastName().compareToIgnoreCase(lName) == 0) &&
			   (curr.getFirstName().compareToIgnoreCase(fName) == 0) && 
			   (curr.getNickName().compareToIgnoreCase(nName) == 0)) {
				   bFound = true;
				   break;
			}				
		}

		if(!bFound) {									// Not found in list already
			Member newNode = new Member();
			newNode.setLastName(lName);
			newNode.setFirstName(fName);
			newNode.setNickName(nName);
			roster.addRacerToList(newNode);
			loadFromRoster();
			
			if(clearOnSave.isEnabled()) {
				doClear();
			} else {
				firstName.setText(fName);
				lastName.setText(lName);
				nickName.setText(nName);
			}
		}
		
	}
	/**
	 * Save the current data; validate & compare to backup.
	 */
	public void doSave(){
		String fName = firstName.getText().toUpperCase();
		String lName = lastName.getText().toUpperCase();
		String nName = nickName.getText().toUpperCase();
		StringBuilder sb = new StringBuilder();
		int idx;
		
		
		if(backup == null) {
			lblMsg.setText("Error, cannot save data, missing original record.");
			return;
		}
			
		idx = backup.getTempIdx();
		
		// Validate data
		if(fName.length() == 0)
			sb.append("First Name must have value. ");
		if(lName.length() == 0)
			sb.append("Last Name must have value. ");
		
		if(sb.length() > 0) {
			lblMsg.setText(sb.toString());
			return;
		}
		
		if((fName.compareToIgnoreCase(backup.getFirstName()) == 0) &&
		   (lName.compareToIgnoreCase(backup.getLastName()) == 0) && 
		   (nName.compareToIgnoreCase(backup.getNickName()) == 0))
			return;
		
		for(Member curr : roster.getRacerList()) {
			if(curr.getTempIdx() == backup.getTempIdx()) {
				curr.setFirstName(fName);
				curr.setLastName(lName);
				curr.setNickName(nName);
				loadFromRoster();
				if(clearOnSave.isEnabled()) {
					doClear();
				} else {
					firstName.setText(fName);
					lastName.setText(lName);
					nickName.setText(nName);
				}
				return;
			}
		}
	}
	
	public void doClear() {
		firstName.setText("");
		lastName.setText("");
		nickName.setText("");
		add_save.setText("Add");
	}
	public void doDelete() {
		int idx = rosterList.getSelectedIndex();
		
		if(idx != -1) {
			for(Member curr : roster.getRacerList()) {
				if(curr.getTempIdx() == idx) {			// found item to delete
					roster.getRacerList().remove(curr);
					break;
				}
			}
		}
		loadFromRoster();
	}
	
	
	public void doExit() {
		dispose();
	}
}
