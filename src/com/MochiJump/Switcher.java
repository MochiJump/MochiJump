package com.MochiJump;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.LayoutManager;

public class Switcher extends JFrame{
	int currentPanel = 0;
	//dogLogic needs a dependent constructor built \/
	DogLogic dogLogic = new DogLogic();
	StartPause startPause = new StartPause(this);
	JFrame frame = new JFrame ("Mochi Jump");

	
	// for changePanel (1) is startPause (2) is dogLogic
	
	public Switcher () {
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 changePanel(1);
		 frame.setVisible(true);
		 frame.repaint();
	}
	// this constructor can be removed \/
	public Switcher (int num) {
		changePanel(num);
	}
	
	public void setCurrentPanel (int option) {
		this.currentPanel = option;
	}
	
	public void setOutsideClassCurrentPanel (int option) {
		dogLogic.setCurrentPanel(option);
		startPause.setCurrentPanel(option);
	}
	
	public void changePanel (int panelNumber) {
		currentPanel = panelNumber;
		setOutsideClassCurrentPanel(panelNumber);
		frame.getContentPane().removeAll();
			if (currentPanel == 1) {
			frame.getContentPane().add(startPause);
			}else if (currentPanel == 2) {
			frame.getContentPane().add(dogLogic);
			// curious why this doesn't launch as it's part of the constructor?
			dogLogic.gameStart();
			}
			 frame.validate();
			 frame.pack();
	
	}
}
