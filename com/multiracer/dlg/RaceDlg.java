package com.multiracer.dlg;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.multiracer.entity.Member;
import com.multiracer.entity.Race;
import com.multiracer.entity.Racer;
import com.multiracer.entity.Roster;

public class RaceDlg extends JDialog implements ActionListener, ListSelectionListener {

	final static int RACE_ROUND_ROBIN = 0;
	final static int RACE_SWISS = 1;
	final static int RACE_SINGLE_ELIM = 2;
	final static int RACE_DOUBLE_ELIM = 3;	
	final static int RACE_STANDARD = 4;
	
	private JLabel lblRaceName;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblNickName;
	private JLabel lblNumber;
	private JLabel lblLaneCnt;
	private JLabel lblPerHeat;
	
	private JLabel lastName;
	private JLabel firstName;
	private JLabel nickName;
	private JTextField raceName;
	private JTextField number;
	private JTextField laneCnt;
	private JTextField perHeat;
	
	private JButton add;
	private JButton remove;
//	JButton select;
	private JButton create;
	
	private JRadioButton rbRoundRobin;			// Round Robin			2
	private JRadioButton rbSwiss;				// Swiss				2+
	private JRadioButton rbSingleElim;			// Single Elimination	2+
	private JRadioButton rbDoubleElim;			// Double Elimination	2+
	private JRadioButton rbStandard;			// Standard    			2+
	private ButtonGroup bgGroup1;
	
	private JPanel racePanel;
	
	private Roster roster;
	private List<Racer> mainList;
	private List<Racer> raceList;
	private Racer selectedRacer;
	private int raceType;
	
	private Race race;
	
    private JList 			 rosterList = null;
    private DefaultListModel rosterModel = null;
    private JScrollPane      rosterPane = null;
    
    private JList 			 racerList = null;
    private DefaultListModel racerModel = null;
    private JScrollPane      racerPane = null;
    
    private JLabel 			 msg = null;

    public RaceDlg(JFrame parent) {
	
        super(parent, "Create Race", true);
        setMinimumSize(new Dimension(600, 500));
        raceList = new ArrayList<Racer>();	// holds list of racers selcted for race
        mainList = new ArrayList<Racer>();  // holds original list from roster
        selectedRacer = null;
        

        GridBagConstraints c = new GridBagConstraints();

        racePanel = new JPanel();
		getContentPane().add(racePanel);
		
		setupDlg();
    }
    public void setRoster(Roster roster) {
    	this.roster = roster;
		rosterModel.clear();
		int idx = 0;

    	for(Member curr : roster.getRacerList()) {
    		Racer node = new Racer();
    		mainList.add(node);
    		node.setFirstName(curr.getFirstName());
    		node.setLastName(curr.getLastName());
    		node.setNickName(curr.getNickName());
    		node.setTempIdx(idx);
    		rosterModel.add(idx, node.displayMember());
    		idx++;
    	}
    }
    
    private void setupDlg() {
		this.setTitle("Create Race");
        Border bType = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

		//      																 x    y    w    h
        lblRaceName = new JLabel("Race Name:");		    lblRaceName.setBounds(  10,  10,  80,  25);
        raceName = new JTextField();					   raceName.setBounds( 100,  10, 300,  25);
        
        lblLastName = new JLabel("Last:");        		lblLastName.setBounds( 280,  60,  80,  25);
        lastName = new JLabel();					       lastName.setBounds( 310,  90, 150,  25);
//        lastName.setEnabled(false);						 lastName.setForeground(Color.DARK_GRAY);
        
        lblFirstName = new JLabel("First:");		   lblFirstName.setBounds( 280, 120,  80,  25);
        firstName = new JLabel();					      firstName.setBounds( 310, 150, 150,  25);
//        firstName.setEnabled(false);
        
        lblNickName = new JLabel("NickName:");          lblNickName.setBounds( 280, 180,  80,  25);
        nickName = new JLabel();					       nickName.setBounds( 310, 210, 150,  25);
//        nickName.setEnabled(false);
        
        lblNumber = new JLabel("Race Number:");		              lblNumber.setBounds( 280, 240,  80,  25);
        number = new JTextField();						             number.setBounds( 310, 270, 150,  25);
        
        // Number of lanes available for the race
        lblLaneCnt = new JLabel("Number of Lanes:"); 	         lblLaneCnt.setBounds(  10, 380, 120,  25);
        laneCnt = new JTextField();						   	        laneCnt.setBounds( 150, 380, 100,  25);

        // Type of race
        // radio button - Round Robin - # of lanes == 2
        // 
        rbRoundRobin = new JRadioButton("Round Robin");	       rbRoundRobin.setBounds(  10,  410, 120, 25);
        rbSwiss = new JRadioButton("Swiss");			            rbSwiss.setBounds(  10,  435, 120, 25);
        rbSingleElim = new JRadioButton("Single Elimination"); rbSingleElim.setBounds(  10,  460, 120, 25);
        rbDoubleElim = new JRadioButton("Double Elimination"); rbDoubleElim.setBounds(  10,  485, 120, 25);
        bgGroup1 = new ButtonGroup();
        bgGroup1.add(rbRoundRobin);
        bgGroup1.add(rbSwiss);
        bgGroup1.add(rbSingleElim);
        bgGroup1.add(rbDoubleElim);
        
        
        lblPerHeat = new JLabel("Racers per Heat:"); 	        lblPerHeat.setBounds(  10, 430, 120,  25);
        perHeat = new JTextField();						           perHeat.setBounds( 150, 430, 200,  25);
        
        add = new JButton("Add");								add.setBounds( 350, 310, 160,  25);        
        remove = new JButton("Remove");						 remove.setBounds( 610, 370,  80,  25);
//        select = new JButton("Select");						 select.setBounds(  10, 370,  80,  25);
        create = new JButton("Create");						 create.setBounds( 780, 520,  80,  25);
        
		rosterModel = new DefaultListModel();
		rosterList = new JList(rosterModel);
		rosterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rosterPane = new JScrollPane(rosterList);		 rosterPane.setBounds(  10,  60, 250, 300);
		rosterPane.setBorder(bType);

		
		racerModel = new DefaultListModel();
		racerList = new JList(racerModel);
		racerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		racerPane = new JScrollPane(racerList);	 	      racerPane.setBounds( 610,  60, 250, 300);
		racerPane.setBorder(bType);

//		select.addActionListener(this);
		add.addActionListener(this);
		rosterList.addListSelectionListener(this);
		remove.addActionListener(this);
		create.addActionListener(this);
		
		msg = new JLabel("");								msg.setBounds(  10,   520,  200,  25);
		
		contentPane.add(rbRoundRobin);
		contentPane.add(rbSwiss);
		contentPane.add(rbSingleElim);
		contentPane.add(rbDoubleElim);
		contentPane.add(lblLaneCnt);
		contentPane.add(laneCnt);
//		contentPane.add(lblPerHeat);
//		contentPane.add(perHeat);
		contentPane.add(add);
//		contentPane.add(select);
		contentPane.add(remove);
		contentPane.add(create);
		contentPane.add(msg);
		
		contentPane.add(lblLastName);
		contentPane.add(lastName);
		contentPane.add(lblFirstName);
		contentPane.add(firstName);
		contentPane.add(lblNickName);
		contentPane.add(nickName);
		contentPane.add(lblNumber);
		contentPane.add(number);
		
		contentPane.add(lblRaceName);
		contentPane.add(raceName);
		contentPane.add(rosterPane);
		contentPane.add(racerPane);
    }

    private void clearSelected() {
    	selectedRacer = null;
    	firstName.setText("");
    	lastName.setText("");
    	nickName.setText("");
    	number.setText("");
    }
    
    /**
     * Determine if the current racer's number is already present in the list.  Return false if 
     * if the number already exists.
     * 
     * @return
     */
    private boolean checkRacerNumbers() {
    	boolean bResult = true;
    	
    	String temp = number.getText();
    	for(Racer curr : raceList) {  // existing list
    		if(curr.getNumber().equalsIgnoreCase(temp)) {
    			bResult = false;
    			break;
    		}
    	}
    	
    	return bResult;
    }
    public void doRemove() {
    	clearSelected();
    	if(!racerList.isSelectionEmpty()) {
    		int idx = racerList.getSelectedIndex();
    		for(Racer curr : raceList) {
    			if(curr.getTempIdx() == idx) {
    				curr.setNumber("");
    				raceList.remove(curr);
    				mainList.add(curr);
    				updMainList();
    				updRacerList();
    				break;    				
    			}
    		}
    	}
    }
    /**
     * Add the current racer from the main List to the racer list.
     * 
     */
    private void doAdd() {

    	// Add the currently selected racer to the racer list
    	
    	if(selectedRacer != null) {
        	if((lastName.getText().length() > 0) &&
   	    	   (firstName.getText().length() > 0) && 
   	    	   (number.getText().length() > 0)) {

        		if(checkRacerNumbers()) {
	        		int idx = racerModel.getSize();
	        		mainList.remove(selectedRacer);
	        		selectedRacer.setTempIdx(idx);
	        		selectedRacer.setNumber(number.getText());
	        		raceList.add(selectedRacer);
	        		updRacerList();
	        		updMainList();
	        		clearSelected();
        		}
        	}
    	}
    }
    
    private void doSelect() {
    	if(!rosterList.isSelectionEmpty()) {
    		int idx = rosterList.getSelectedIndex();
    		for(Racer curr : mainList){ 
    			if(curr.getTempIdx() == idx) {
    				selectedRacer = curr;
    				if((!lastName.getText().equalsIgnoreCase(curr.getLastName())) ||
    				   (!firstName.getText().equalsIgnoreCase(curr.getFirstName())) ||
    				   (!nickName.getText().equalsIgnoreCase(curr.getNickName())))
    					number.setText("");
    					
    				lastName.setText(curr.getLastName());
    				firstName.setText(curr.getFirstName());
    				nickName.setText(curr.getNickName());
    			}
    		}
    	}
    }
    private void updMainList() {
		rosterModel.clear();
		int idx = 0;
		Collections.sort(mainList);
		
		for(Racer curr : mainList) {
			curr.setTempIdx(idx++);
    		rosterModel.add(curr.getTempIdx(), curr.displayMember());
    	}
    }
    private void updRacerList() {
    	racerModel.clear();
    	int idx = 0;
    	Collections.sort(raceList);
    	
    	for(Racer curr : raceList) {
    		curr.setTempIdx(idx++);
    		racerModel.add(curr.getTempIdx(), curr.displayRacer());
    	}
    }
    
    private void updateSelections() {
    	if(!rosterList.isSelectionEmpty()) {
    		int idx = rosterList.getSelectedIndex();
    		for(Member curr : roster.getRacerList()) {
    			if(curr.getTempIdx() == idx) {
    				lastName.setText(curr.getLastName());
    				firstName.setText(curr.getFirstName());
    				nickName.setText(curr.getNickName());
    			}
    		}
    	}
    }
    
    private void doCreate() {
    	// Verify needed data
    	// 1) Race Name
    	// 2) Racing list contains more than 1 racer
    	// 3) Number of lanes 
    	// 4) Type of race
    	System.out.println("doCreate");
    	msg.setText("Validating information");
    	
    	if(!rbRoundRobin.isSelected() && !rbSwiss.isSelected() &&
    	   !rbSingleElim.isSelected() && !rbDoubleElim.isSelected() 
    	   /* && !rbStandard.isSelected() */) {
        	System.out.println("Must choose a race type.");
    		msg.setText("Must choose a race type.");
    		return;
    	} else {
    		raceType = 0;
    		if(rbRoundRobin.isSelected()) raceType = RACE_ROUND_ROBIN;
    		if(rbSwiss.isSelected()) raceType = RACE_SWISS;
    		if(rbSingleElim.isSelected()) raceType = RACE_SINGLE_ELIM;
    		if(rbDoubleElim.isSelected()) raceType = RACE_DOUBLE_ELIM;
    	}
    	
    	if(raceName.getText().length() == 0) {
    		System.out.println("Please assign race a name.");
    		msg.setText("Please assign race a name.");
    		return;
    	}
    		
    	if(raceList.size() < 2) {
    		System.out.println("You must have at least 2 racers.");
    		msg.setText("You must have at least 2 racers.");
    		return;
    	} else {
    		for(Racer curr : raceList) {
    			System.out.println(curr.getLastName() + ", " + curr.getFirstName());
    		}
    	}
    	int lanes = 0;
    	try {
    		lanes = Integer.parseInt(laneCnt.getText());
    	} catch(NumberFormatException nfe) {
    		lanes = 0;
    	}
    	if(lanes < 2) {
    		System.out.println("Must have at least 2 lanes.");
    		msg.setText("Must have at least 2 lanes.");
    		return;
    	}    	
    	msg.setText("");
    }
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		String rbLabel = e.getActionCommand();
		System.out.println("==>"+ rbLabel);
		if(rbLabel.equalsIgnoreCase("create")) {
			doCreate();
		} else if(rbLabel.equalsIgnoreCase("add")) {
			doAdd();
		} else if(rbLabel.equalsIgnoreCase("remove")) {
			doRemove();
		}

	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean adjust = e.getValueIsAdjusting();
		if(!adjust) {
			JList list = (JList) e.getSource();
			int selections[] = list.getSelectedIndices();
			Object selValues[] = list.getSelectedValues();
		if(selections.length == 1) {
				doSelect();
			}
		}
	}
}
