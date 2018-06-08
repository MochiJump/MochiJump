package com.MochiJump;


import java.awt.BorderLayout;
import java.awt.Container;
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

static int currentPanel;
private int refreshRate = 30;

private Action MoveSelectorUp;
private Action MoveSelectorDown;
private Action MakeSelection;

private double maxHeight;
private double maxWidth;
private double ratioHeight;
private double ratioWidth;
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

private int setSelectorPointX= 10000;
private int setSelectorPointY=10000;

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
// Switcher switcher = new Switcher(); wow so just initializing the switcher in this class breaks everything
	

public StartPause() {
	sPScreen.add(startScreenKeyInputs());
 	add (sPScreen);
	startPauseActive();
}


public void setCurrentPanel (int option) {
	this.currentPanel = option;
}
public int getCurrentPanel() {
	return this.currentPanel;
}


public void startPauseActive() {
	Thread startPauseThread = new Thread(){
		public void run(){
			while (currentPanel == 1){
				menuUpdate();
				repaint();
				getCurrentPanel();
				System.out.println(String.valueOf(currentPanel));
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
	ratioCheck();
	setPoints();
	selectorAni();
}

Toolkit toolkit = Toolkit.getDefaultToolkit();

private void screenSizeCheck(){
  screenSize = toolkit.getScreenSize();
  maxHeight = screenSize.getHeight();
  maxWidth = screenSize.getWidth();
}

private void ratioCheck() {
	ratioHeight = maxHeight/768;
	ratioWidth = maxWidth/1366;
}

// using the above we can resize all of the image icons to fit the dimensions of whatever screen it is displayed on
// include the ratioHeight and Width in the draw method

private void setPoints() {
	  setPointAy = (int) (maxHeight/5*ratioHeight);
	  setPointAx = (int) (maxWidth/2 - 222/2 *ratioWidth);
	  setPointBy = (int) (maxHeight/2 *ratioHeight);
	  setPointBx = (int) (maxWidth/2 -366/2 *ratioWidth);
	  setPointCy = (int) (maxHeight/1.5 *ratioHeight);
	  setPointCx = (int) (maxWidth/2 - 366/2 *ratioWidth);
	//  setSelectorPointAx = setPointAx - 150;
	//  setSelectorPointAy = setPointAy; not neccessary because that's the mochi icon
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
  //including ratioWidth/Height below to resize the image
  mochiIcon.setClip(setPointAx, setPointAy, (int) (222*ratioWidth), (int)(225*ratioHeight));
  mochiIcon.drawImage(mochiFaceState1, setPointAx, setPointAy, (int) (222*ratioWidth), (int)(225*ratioHeight), null);
 
  startSelect.setClip(setPointBx, setPointBy, (int)(366*ratioWidth), (int)(71*ratioHeight)); 
  startSelect.drawImage(start, setPointBx, setPointBy, (int)(366*ratioWidth),(int)(71*ratioHeight), null);
  contSelect.setClip(setPointCx, setPointCy, (int)(366*ratioWidth), (int)(71*ratioHeight));
  contSelect.drawImage(cont, setPointCx, setPointCy, (int)(366*ratioWidth), (int)(71*ratioHeight), null);
  selectorIcon.setClip(setSelectorPointX, setSelectorPointY, (int)(140*ratioWidth), (int)(90*ratioHeight));
  selectorIcon.drawImage(selectorImage, setSelectorPointX, setSelectorPointY, (int)(ratioWidth*140), (int)(ratioHeight*90), null);
}


private class StartActionHelper {
  public void actionPerformed (ActionEvent s){
    // having multiple panel options requires the location of the select to be checked for this to work

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
		  if (setSelectorPointY == setSelectorPointBy) {
			  //okay so this does start the DogLogic thread, but it doesn't stop the menu animation
			  new Switcher(2);
		  }
		  if (setSelectorPointY == setSelectorPointCy) {
			  System.exit(0);
		  }
		  
	  }
		  
}

	@Override
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		draw(g2);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return screenSize;
	}
	
}
