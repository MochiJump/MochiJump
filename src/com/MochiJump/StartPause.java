package com.MochiJump;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.JLabel;


public class StartPause extends JPanel {

boolean isStartOn = true;
private int refreshRate = 30;

private Action MoveSelectorUp;
private Action MoveSelectorDown;
private Action MakeSelection;

private double maxHeight;
private double maxWidth;
private int setPointAx;
private int setPointAy;
private int setPointBx;
private int setPointBy;
private int setPointCx;
private int setPointCy;
private int setSelectorPointBx;
private int setSelectorPointBy;
private int setSelectorPointCx;
private int setSelectorPointCy;

private int setSelectorPointX = 10000;
private int setSelectorPointY = 10000;

private int selectorAniCounter;
private Dimension screenSize;


Image mochiFaceState1 = new ImageIcon(this.getClass().getResource("/background.png")).getImage(); //<-- consider changing png file name
Image mochiFaceState2 = new ImageIcon(this.getClass().getResource("/blink.png")).getImage();
Image mochiFace;
Image start = new ImageIcon(this.getClass().getResource("/start.png")).getImage();
Image cont = new ImageIcon(this.getClass().getResource("/continue.png")).getImage();
Image selectorImage;
Image selectorImage1 = new ImageIcon(this.getClass().getResource("/bone1M.png")).getImage();
Image selectorImage2 = new ImageIcon(this.getClass().getResource("/bone2M.png")).getImage();
Image selectorImage3 = new ImageIcon(this.getClass().getResource("/bone3M.png")).getImage();
Image selectorImage4 = new ImageIcon(this.getClass().getResource("/bone4M.png")).getImage(); //<-- optional (inverted 2M)
Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();

JPanel sPScreen = new JPanel();
	public StartPause(){
	sPScreen.add(startScreenKeyInputs());
 	add (sPScreen);
	startPauseActive();
}
public void startPauseActive() {
	Thread startPauseThread = new Thread(){
		public void run(){
			while (isStartOn){
				menuUpdate();
				repaint();
				try{
					Thread.sleep(1000/refreshRate);
				}catch (InterruptedException ex){
					System.out.println("An error has occured in the StartPause Thread");
				}
			}
		}
	};
	startPauseThread.start();
}
private void menuUpdate(){
	screenSizeCheck();
	setPoints();
	selectorAni();
}

Toolkit toolkit = Toolkit.getDefaultToolkit();

private void screenSizeCheck(){
  screenSize = toolkit.getScreenSize();
  maxHeight = screenSize.getHeight();
  maxWidth = screenSize.getWidth();
}
private void setPoints() {
	  setPointAy = (int) (maxHeight/5);
	  setPointAx = (int) (maxWidth/2 - 222/2);
	  setPointBy = (int) (maxHeight/2);
	  setPointBx = (int) (maxWidth/2 -366/2);
	  setPointCy = (int) (maxHeight/1.5);
	  setPointCx = (int) (maxWidth/2 - 366/2);
	//  setSelectorPointAx = setPointAx - 150;
	//  setSelectorPointAy = setPointAy;
	  setSelectorPointBx = setPointBx -150;
	  setSelectorPointBy = setPointBy;
	  setSelectorPointCx = setPointCx -150;
	  setSelectorPointCy = setPointCy;
}

private void selectorAni(){
	  if (selectorAniCounter <= 5){
		  selectorImage = selectorImage1;
		  selectorAniCounter++;
	  } else if (selectorAniCounter <= 10){
		  selectorImage = selectorImage2;
		  selectorAniCounter++;
	  } else if (selectorAniCounter <= 15){
		  selectorImage = selectorImage3;
		  selectorAniCounter++;
	  } else if (selectorAniCounter <=20){
		  selectorImage = selectorImage4;
		  selectorAniCounter++;
	  }
	  else{
		  selectorAniCounter = 0;
	  }
}


public void draw (Graphics g){
  Graphics2D mochiIcon = (Graphics2D) g.create();
  Graphics2D startSelect = (Graphics2D) g.create();
  Graphics2D contSelect = (Graphics2D) g.create();
  Graphics2D selectorIcon = (Graphics2D) g.create();
  mochiIcon.setClip(setPointAx, setPointAy, 222, 225);
  mochiIcon.drawImage(mochiFaceState1, setPointAx, setPointAy, 222,225, null);
 
  startSelect.setClip(setPointBx, setPointBy, 366, 71); 
  startSelect.drawImage(start, setPointBx, setPointBy, 366,71, null);
  contSelect.setClip(setPointCx, setPointCy, 366, 71);
  contSelect.drawImage(cont, setPointCx, setPointCy, 366, 71, null);
  selectorIcon.setClip(setSelectorPointX, setSelectorPointY, 140, 90);
  selectorIcon.drawImage(selectorImage, setSelectorPointX, setSelectorPointY, 140, 90, null);
}
public boolean getIsStartOn(){
  return this.isStartOn;
}

private class StartActionHelper {
  public void actionPerformed (ActionEvent s){
    // this is to start the game
    isStartOn = false;
  }
}

private class ExitActionHelper{
  public void actionPerformed (ActionEvent exit){
    System.exit(0);
  }
}


public JLabel startScreenKeyInputs (){
  JLabel MochiStartLabel = new JLabel ("Start Pause");
 
  MoveSelectorUp MoveSelectorUp = new MoveSelectorUp();
  MoveSelectorDown MoveSelectorDown = new MoveSelectorDown();
  MakeSelection MakeSelection = new MakeSelection();
  
  InputMap im = MochiStartLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
  ActionMap am = MochiStartLabel.getActionMap();
  im.put(KeyStroke.getKeyStroke("UP"), "MoveSelectorUp");
  am.put("MoveSelectorUp", MoveSelectorUp);
  im.put(KeyStroke.getKeyStroke("DOWN"), "MoveSelectorDown");
  am.put("MoveSelectorDown", MoveSelectorDown);
  im.put(KeyStroke.getKeyStroke("ENTER"), "MakeSelection");
  am.put("MakeSelection", MakeSelection);
  // corresponding classes MoveSelectorUp, MoveSelectorDown, MakeSelection, need to be created	  
  
  return MochiStartLabel;
  
}
private class MoveSelectorUp extends AbstractAction{
	  public void actionPerformed (ActionEvent mu) {
	   if (setSelectorPointY == setSelectorPointBy){
		   setSelectorPointX = setSelectorPointCx;
		   setSelectorPointY = setSelectorPointCy;
	   }else if (setSelectorPointY == setSelectorPointCy) {
		   setSelectorPointX = setSelectorPointBx;
		   setSelectorPointY = setSelectorPointBy;
	   }else {		  
		   setSelectorPointX = setSelectorPointBx;
		   setSelectorPointY = setSelectorPointBy;
	   }
	  }
}
private class MoveSelectorDown extends AbstractAction{
	  public void actionPerformed (ActionEvent md) {
		  if (setSelectorPointY == setSelectorPointBy){
			   setSelectorPointX = setSelectorPointCx;
			   setSelectorPointY = setSelectorPointCy;
		 }else if (setSelectorPointY == setSelectorPointCy) {
			   setSelectorPointX = setSelectorPointBx;
			   setSelectorPointY = setSelectorPointBy;
		 }else {		  
			   setSelectorPointX = setSelectorPointBx;
			   setSelectorPointY = setSelectorPointBy;
		  }  
	  }
}

private class MakeSelection extends AbstractAction{
	  public void actionPerformed (ActionEvent ms) {
		  isStartOn = false;
	  }
		  
}

	@Override
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		draw(g2);
	}
}
