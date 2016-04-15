package com.multiracer.dlg;

import java.awt.Container;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.multiracer.entity.Roster;

public class MultiracerDlg extends JFrame {
	JMenuBar 	menuBar;
	JMenu	 	raceMenu;
	JMenu       rosterMenu;
	JMenuItem	createMenuItem;
	JMenuItem	loadMenuItem;
	JMenuItem	saveMenuItem;
	JMenuItem	editMenuItem;
	Roster      mainRoster;
	int         state;
	JTextField  mLastName;
	JTextField  mFirstName;
	JTextField  mNickName;
	JButton     mAdd;
	JButton     mEdit;
	JButton     mDelete;
	
	public MultiracerDlg() {
		init();
		setupDlg();
	}
	
	
	public void init() {
        ResourceBundle rb = null;
        Enumeration e = null;
        String key = null;
        String value = null;
        boolean bGood = true;
        
        // Load current Roster information
        ////////////////////////////////////////////////////////////////
        mainRoster = new Roster();
        mainRoster.loadRosterFile("c:\\work\\Multiracer\\Roster.dat");
	}
	
	public void doCreateRace() {
		int width = 900;
		int height = 600;
		RaceDlg dlg = new RaceDlg(this);
		dlg.setRoster(mainRoster);
        dlg.setSize(width, height);
        dlg.setResizable( false );
        dlg.setLocationRelativeTo(null);
        dlg.setVisible(true);
	}
	public void doEdit() {
		int width = 700;
		int height = 450;
		EditDialog dlg = new EditDialog(this);
		dlg.setList(mainRoster);
        dlg.setSize(width, height);
        dlg.setResizable( false );
        dlg.setLocationRelativeTo(null);
        dlg.setVisible(true);
	}
	private void setupDlg() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        this.setTitle("Multi-Racer");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Border bType = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		MultiracerAL al = new MultiracerAL(this);

        // create menu bar
		menuBar = new JMenuBar();
		
		// create menus
		raceMenu = new JMenu("Race");
		rosterMenu = new JMenu("Roster");
		
		// create menu items for menus
		createMenuItem = new JMenuItem("Create");
		loadMenuItem = new JMenuItem("Load");
		saveMenuItem = new JMenuItem("Save");
		
		createMenuItem.addActionListener(al);
		loadMenuItem.addActionListener(al);
		saveMenuItem.addActionListener(al);
		raceMenu.add(createMenuItem);
		raceMenu.add(loadMenuItem);
		raceMenu.add(saveMenuItem);
		
		editMenuItem = new JMenuItem("Edit");
		editMenuItem.addActionListener(al);
		rosterMenu.add(editMenuItem);
		
		menuBar.add(raceMenu);
		menuBar.add(rosterMenu);
//		menuItem = new JMenuItem("Load");		
//		menu.add(menuItem);
//		
//		menuItem = new JMenuItem("Save");		
//		menu.add(menuItem);
		menuBar.setVisible(true);
		setJMenuBar(menuBar);
		contentPane.add(menuBar);
		
		
	}
}
