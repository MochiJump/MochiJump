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
	DogLogic dogLogic = new DogLogic(this);
	StartPause startPause;
	LevelSelector levelSelector= new LevelSelector(this);
	JFrame frame = new JFrame ("Mochi Jump");
	boolean escapeUsed;

	
	// for changePanel (1) is startPause (2) is dogLogic (3) is levelSelector
	
	public Switcher () {
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		 frame.setVisible(true);
		 frame.repaint();
		 startPause = new StartPause(this);
		 changePanel(1);
	}
	
	public void setCurrentPanel (int option) {
		this.currentPanel = option;
	}
	
	public void setOutsideClassCurrentPanel (int option) {
		dogLogic.setCurrentPanel(option);
		startPause.setCurrentPanel(option);
		levelSelector.setCurrentPanel(option);
	}
	
	public void changePanel (int panelNumber) {
		currentPanel = panelNumber;
		setOutsideClassCurrentPanel(panelNumber);
		frame.getContentPane().removeAll();
			if (currentPanel == 1) {
			frame.getContentPane().add(startPause);
				if (escapeUsed) {
					startPause.startPauseActive();
				}
			}else if (currentPanel == 2) {
			frame.getContentPane().add(dogLogic);
			dogLogic.gameStart();
			}else if (currentPanel == 3) {
				frame.getContentPane().add(levelSelector);
				levelSelector.levelSelectorActive();
				}
			 frame.validate();
			 frame.pack();	
	}
}