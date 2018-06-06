package com.MochiJump;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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

boolean isStart;
boolean isPause;
private int refreshRate = 30;

private Action MoveSelectorUp;
private Action MoveSelectorDown;
private Action MakeSelection;

private int maxHeight;
private int maxWidth;
private int setPointAx;
private int setPointAy;
private int setPointBx;
private int setPointBy;
private int setPointCx;
private int setPointCy;
private int setSelectorPointAx;
private int setSelectorPointAy;
private int setSelectorPointBx;
private int setSelectorPointBy;
private int setSelectorPointCx;
private int setSelectorPointCy;
private int selectorWidth;
private int selectorHeight;
private int setSelectorPointX;
private int setSelectorPointY;

private int selectorAniCounter;
//variables button sizes
private Dimension screenSize;
private Dimension sbSize;
private Dimension cbSize;
private Dimension mbSize;

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

JLabel test = new JLabel ("0");

JPanel sPScreen = new JPanel();
	public StartPause(){
	sPScreen.add(startScreenKeyInputs());
 	add (sPScreen);
 	
    this.addComponentListener(new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
           Component c = (Component)e.getSource();
           Dimension dim = c.getSize();
           maxWidth = dim.width;
           maxHeight = dim.height;
        }
     });
    
	startPauseActive();
}
public void startPauseActive() {
	Thread startPauseThread = new Thread(){
		public void run(){
			while (true){
				menuUpdate();
				repaint();
				try{
					Thread.sleep(1000/29);// <-- needs refreshrate variable should this be the same one as doglogics?
				}catch (InterruptedException ex){
					System.out.println("An error has occured in the StartPause Thread");
				}
			}
		}
	};
	startPauseThread.start();
}
private void menuUpdate(){
	setPoints();
	selectorAni();
}


private void setPoints() {
	  setPointAy = (int) (maxHeight/4);
	  setPointAx = (int) (maxWidth/2 - 222/2);
	  setPointBy = (int) (maxHeight/2);
	  setPointBx = (int) (maxWidth/2 -366/2);
	  setPointCx = (int) (maxHeight/2 +400);
	  setPointCy = (int) (maxWidth/2 - 3666/2);
	  setSelectorPointAx = setPointAx - 150;
	  setSelectorPointAy = setPointAy;
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
  mochiIcon.setClip(550, 100, 222, 225);
  mochiIcon.drawImage(mochiFaceState1, 550, 100, 222,225, null);
 
  startSelect.setClip(500, 400, 366, 71); 
  startSelect.drawImage(start, 500, 400, 366,71, null);
  contSelect.setClip(500, 600, 366, 71);
  contSelect.drawImage(cont, 500, 600, 366, 71, null);
  // the individual images show up fine. selectorAni() is not doing anything, there may be something wrong with the thread
  selectorIcon.setClip(300, 400, 140, 90);
  selectorIcon.drawImage(selectorImage, 300, 400, 140, 90, null);
}
public boolean getIsStart(){
  return this.isStart;
}

public boolean getIsPause(){
  return this.isPause;
}
public void setPause(){
  isPause = this.isPause;
}
private class StartActionHelper {
  public void actionPerformed (ActionEvent s){
    // this is to start the game
    isStart = false;
  }
}
// need additional ActionHelpers below:
private class ExitActionHelper{
  public void actionPerformed (ActionEvent exit){
    //check here for future updates with serialization, will I have to do anything to save state prior to this,
    //or should I add a save state in another way?
    System.exit(0);
  }
}


// let's go ahead and start with keybindings here:
public JLabel startScreenKeyInputs (){
  JLabel MochiStartLabel = new JLabel (String.valueOf(maxHeight));
 
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
	   if (setSelectorPointX == setSelectorPointBx){
		   setSelectorPointX = setSelectorPointCx;
		   setSelectorPointY = setSelectorPointCy;
	   }else if (setSelectorPointX == setSelectorPointCx){
		   setSelectorPointX = setSelectorPointBx;
		   setSelectorPointY = setSelectorPointBy;
	   }
	  }
}
private class MoveSelectorDown extends AbstractAction{
	  public void actionPerformed (ActionEvent md) {
		  
	  }
}

private class MakeSelection extends AbstractAction{
	  public void actionPerformed (ActionEvent ms) {
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
       return (new Dimension(maxWidth, maxHeight));
    }
}
