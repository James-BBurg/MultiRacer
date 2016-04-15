package com.multiracer.runner;

import javax.swing.JFrame;

import com.multiracer.dlg.MultiracerDlg;

public class MultiracerRunner {

    private final static int width  = 450;
    private final static int height = 490;
	
	
	public static void main(String[] args) {
		
		JFrame f = new MultiracerDlg();

        f.setSize(width, height);
        f.setResizable( false );
        f.setLocationRelativeTo(null);
        f.setVisible(true);
	}
}
