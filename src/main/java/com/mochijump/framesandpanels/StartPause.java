
package com.mochijump.framesandpanels;

import com.mochijump.levels.ImportLevelReader;
import com.mochijump.VersionChecker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class StartPause extends JPanel {
	public VersionChecker versionChecker;
	private int currentPanel;
	private int refreshRate = 30;
	
	private double maxHeight;
	private double maxWidth;
	private double ratioHeight;
	private double ratioWidth;
	private int setTopX;
	private int setTopY;
	private int setPointAx;
	private int setPointAy;
	private int setPointBx;
	private int setPointBy;
	private int setPointCx;
	private int setPointCy;
	private int setPointDx;
	private int setPointDy;
	private int setSelectorPointBx;
	private int setSelectorPointBy;
	private int setSelectorPointCx;
	private int setSelectorPointCy;
	private int setSelectorPointDx;
	private int setSelectorPointDy;
	
	int cv = 0;
	String versionMessage = "";
	
	private int setSelectorPointX= 10000;
	private int setSelectorPointY=10000;
	
	private int selectorAniCounter;
	public Dimension screenSize;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	int sizeChange;
	
	
	Image mochiFaceState1 = new ImageIcon(this.getClass().getResource("/background.png")).getImage(); //<-- consider changing png file name
	Image mochiFaceState2 = new ImageIcon(this.getClass().getResource("/blink.png")).getImage();
	Image mochiFace;
	Image start = new ImageIcon(this.getClass().getResource("/start.png")).getImage();
	Image cont = new ImageIcon(this.getClass().getResource("/continue.png")).getImage();
	Image exit = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
	Image load = new ImageIcon(this.getClass().getResource("/loadLvl.png")).getImage();
	Image selectorImage;
	Image selectorImage1 = new ImageIcon(this.getClass().getResource("/bone1M.png")).getImage();
	Image selectorImage2 = new ImageIcon(this.getClass().getResource("/bone2M.png")).getImage();
	Image selectorImage3 = new ImageIcon(this.getClass().getResource("/bone3M.png")).getImage();
	Image selectorImage4 = new ImageIcon(this.getClass().getResource("/bone4M.png")).getImage(); //<-- optional (inverted 2M)
	
	JPanel sPScreen = new JPanel();
	// Switcher switcher = new Switcher(); wow so just initializing the switcher in this class breaks everything
	
	Switcher switcher;
	
	public StartPause(Switcher s) {
		startScreenKeyInputs();
	 	add (sPScreen);
		startPauseActive();
		switcher = s;
	}
	
	
	public void setCurrentPanel (int option) {
		this.currentPanel = option;
	}
	
	// this is a place holder for now will be moved to LevelSelector later
	// not happy about how far up the dependency chain you have to go to get LevelMap below
	public void importLevel () {
		ImportLevelReader lr = new ImportLevelReader();
		lr.ReadRest(switcher.dogLogic.levelMap);
		lr = null;
	}
	
	
	public void startPauseActive() {
		Thread startPauseThread = new Thread(){
			public void run(){
				while (currentPanel == 1){
					menuUpdate();
					repaint();
					checkVersion();
	
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
	

	
	public void checkVersion() {
		if (cv<1) {
		versionChecker = new VersionChecker();
		versionMessage = ("Current Verison is "+ versionChecker.version + " and "+versionChecker.checkVersion());
		cv++;
		}
	}
	
	private void menuUpdate(){
		screenSizeCheck();
		ratioCheck();
		setPoints();
		selectorAni();
	}
	

	
	private void screenSizeCheck(){
	  screenSize = toolkit.getScreenSize();
	  maxHeight = screenSize.getHeight();
	  maxWidth = screenSize.getWidth();
	}
	
	private void ratioCheck() {
		// originally designed on a 1366x768 screen
		// could include an aspect check for tall screens /mobile
		ratioHeight = maxHeight/768;
		ratioWidth = maxWidth/1366;
	}
	

	
	
	private void setPoints() {
		  setTopX = 0;
		  setTopY = (int) (maxHeight/50);
		  setPointAy = (int) (maxHeight/5);
		  setPointAx = (int) (maxWidth/2 - 222*ratioWidth/2);
		  setPointBy = (int) (maxHeight/2);
		  setPointBx = (int) (maxWidth/2 -366*ratioWidth/2);
		  setPointCy = (int) (maxHeight/1.5);
		  setPointCx = (int) (maxWidth/2 - 366*ratioWidth/2);
		  setPointDx = (int) (maxWidth/2 - 366*ratioWidth/2);
		  setPointDy = (int) (maxHeight/1.25);
		  setSelectorPointBx = setPointBx - (int) (150 * ratioWidth);
		  setSelectorPointBy = setPointBy;
		  setSelectorPointCx = setPointCx - (int) (150 * ratioWidth);
		  setSelectorPointCy = setPointCy;
		  setSelectorPointDx = setPointDx - (int) (150 * ratioWidth);
		  setSelectorPointDy = setPointDy;
		
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
	  Graphics2D version = (Graphics2D) g.create();
	  Graphics2D startSelect = (Graphics2D) g.create();
	  Graphics2D contSelect = (Graphics2D) g.create();
	  Graphics2D exitSelect = (Graphics2D) g.create();
	  Graphics2D selectorIcon = (Graphics2D) g.create();
	  Graphics2D loadSelect = (Graphics2D) g.create();
	  // for version checker output
	  version.setColor(Color.BLACK);
	  version.setFont(new Font ("Impact", Font.PLAIN, 20));
	  version.drawString(versionMessage, setTopX, setTopY);
	  
	  //including ratioWidth/Height below to resize the image
	  mochiIcon.setClip(setPointAx, setPointAy, (int) (222*ratioWidth), (int)(225*ratioHeight));
	  mochiIcon.drawImage(mochiFaceState1, setPointAx, setPointAy, (int) (222*ratioWidth), (int)(225*ratioHeight), null);
	  if (!switcher.showContinue) {
		  startSelect.setClip(setPointBx, setPointBy, (int)(366*ratioWidth), (int)(71*ratioHeight)); 
		  startSelect.drawImage(start, setPointBx, setPointBy, (int)(366*ratioWidth),(int)(71*ratioHeight), null);
	  } else {
		  contSelect.setClip(setPointBx, setPointBy, (int)(366*ratioWidth), (int)(71*ratioHeight)); 
		  contSelect.drawImage(cont, setPointBx, setPointBy, (int)(366*ratioWidth),(int)(71*ratioHeight), null);
	  }
	  //setClip and drawImage for new button
	  loadSelect.setClip(setPointCx, setPointCy, (int)(366*ratioWidth), (int)(71*ratioHeight));
	  loadSelect.drawImage(load, setPointCx, setPointCy, (int)(366*ratioWidth), (int)(71*ratioHeight),null);
	  
	  exitSelect.setClip(setPointDx, setPointDy, (int)(366*ratioWidth), (int)(71*ratioHeight));
	  exitSelect.drawImage(exit, setPointDx, setPointDy, (int)(366*ratioWidth), (int)(71*ratioHeight), null);
		
	  selectorIcon.setClip(setSelectorPointX, setSelectorPointY, (int)(140*ratioWidth), (int)(90*ratioHeight));
	  selectorIcon.drawImage(selectorImage, setSelectorPointX, setSelectorPointY, (int)(ratioWidth*140), (int)(ratioHeight*90), null);
	}
	
	
	private class StartActionHelper {
	  public void actionPerformed (ActionEvent s){
	    // having multiple panel options requires the location of the select to 
		//be checked for this to work
	
	  }
	}
	
	private class ExitActionHelper{
	  public void actionPerformed (ActionEvent exit){
	    System.exit(0);
	  }
	}
	
	
	public void startScreenKeyInputs (){
	 
	  MoveSelectorUp MoveSelectorUp = new MoveSelectorUp();
	  MoveSelectorDown MoveSelectorDown = new MoveSelectorDown();
	  MakeSelection MakeSelection = new MakeSelection();
	  
	  InputMap im = sPScreen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	  ActionMap am = sPScreen.getActionMap();
	  im.put(KeyStroke.getKeyStroke("UP"), "MoveSelectorUp");
	  am.put("MoveSelectorUp", MoveSelectorUp);
	  im.put(KeyStroke.getKeyStroke("DOWN"), "MoveSelectorDown");
	  am.put("MoveSelectorDown", MoveSelectorDown);
	  im.put(KeyStroke.getKeyStroke("ENTER"), "MakeSelection");
	  am.put("MakeSelection", MakeSelection);
	  
	}
	private class MoveSelectorUp extends AbstractAction{
	
		public void actionPerformed (ActionEvent mu) {
		   if (setSelectorPointY == setSelectorPointBy){
			   setSelectorPointX = setSelectorPointDx;
			   setSelectorPointY = setSelectorPointDy;
		   }else if (setSelectorPointY == setSelectorPointCy) {
			   setSelectorPointX = setSelectorPointBx;
			   setSelectorPointY = setSelectorPointBy;
		   }else if (setSelectorPointY == setSelectorPointDy) {
			   setSelectorPointX = setSelectorPointCx;
			   setSelectorPointY = setSelectorPointCy;
		   }
		   else{		  
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
				   setSelectorPointX = setSelectorPointDx;
				   setSelectorPointY = setSelectorPointDy;
			 }else if (setSelectorPointY == setSelectorPointDy) {
				 setSelectorPointX = setSelectorPointBx;
			   setSelectorPointY = setSelectorPointBy;
			 }
			 
			 else {		  
				   setSelectorPointX = setSelectorPointBx;
				   setSelectorPointY = setSelectorPointBy;
			  }  
		  }
	}
	
	private class MakeSelection extends AbstractAction{
		
		  public void actionPerformed (ActionEvent ms) {
			  if (setSelectorPointY == setSelectorPointBy) {
			
				  switcher.changePanel(2);
			  }
			  if (setSelectorPointY == setSelectorPointCy) {
				  switcher.dogLogic = new DogLogic(switcher);
				  switcher.changePanel(3);
	
			  }
			  if (setSelectorPointY == setSelectorPointDy){
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