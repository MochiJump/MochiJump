package com.MochiJump;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


/*
 * This would change if we only had a list of names initially
 * 
*/




public class LevelSelector extends JPanel{

	private int setPointAx;
	private int setPointAy;
	private  int setPointBy;
	private  int setPointBx;
	private  int setPointCy;
	private  int setPointCx;
	private  int setPointDx;
	private  int setPointDy;
	private String pointBText= " ";
	private String pointCText= " ";
	private String pointDText =" ";
	private boolean isInitalized;
	private int selectIndx;
	private int selectIndy;
	private String selectIndText ="->";
	private int index=0;
	
	private Dimension screenSize;
	
	int test = 0;
	
Image mochiFaceState1 = new ImageIcon(this.getClass().getResource("/background.png")).getImage();
	
  JPanel lSPanel = new JPanel();
  int refreshRate = 30;
  int currentPanel;
  Switcher switcher;
  
  public LevelSelector (Switcher s) {
		selectionKeyInputs();
	 	add (lSPanel);
		levelSelectorActive();
		switcher = s;
		
	}
    
private Action MoveSelectionUp;
private Action MoveSelectionDown;
private Action MakeSelection;
private Action Escape;
  
private double maxHeight;
private double maxWidth;
private double ratioHeight;
private double ratioWidth;
ImportLevelReader lr;


ArrayList <String> levelName;
ArrayList <Integer> importOrder;
  
public void setCurrentPanel (int option) {
	this.currentPanel = option;
}
public int getCurrentPanel() {
	return this.currentPanel;
}
  public void levelSelectorActive(){
	Thread startPauseThread = new Thread(){
		public void run(){
			while (currentPanel == 3){
				menuUpdate();
				repaint();
				importLevelNames();
				try{
					Thread.sleep(1000/refreshRate);
				}catch (InterruptedException ex){
					System.out.println("An error has occured in the LevelSelection thread");
				}
			}
		}
	};
	startPauseThread.start();
}
  private void importLevelNames() {
	  if(!isInitalized) {
		pointBText = "loading";
		lr = new ImportLevelReader();
		lr.ReadRest(switcher.dogLogic.levelMap);
		lr.getLevelNames();
		displayNames();
		isInitalized = true;
	  }else {
		  displayNames();
	  }
	  
  }
  
  private void displayNames() {
	  if (index < lr.names.size()-2) {
	  pointBText = lr.names.get(index).toString();
	  pointCText = lr.names.get(index+1).toString();
	  pointDText = lr.names.get(index+2).toString();
	  }else if (index < lr.names.size()-1) {
	  pointBText = lr.names.get(index).toString();
	  pointCText = lr.names.get(index+1).toString();
	  pointDText = " ";	  
	  }else if (index < lr.names.size()) {
		pointBText = lr.names.get(index).toString();
		pointCText =" ";
		pointDText = " ";	  
	}
  }
  
private void menuUpdate(){
	screenSizeCheck();
	ratioCheck();
	setPoints();
	selectorAni();
}

Toolkit toolkit = Toolkit.getDefaultToolkit();

private void screenSizeCheck(){
  screenSize = switcher.startPause.getSize();
  maxHeight = screenSize.getHeight();
  maxWidth = screenSize.getWidth();
}

private void ratioCheck() {
	// originally designed on a 1366x768 screen
	ratioHeight = maxHeight/768;
	ratioWidth = maxWidth/1366;
}

private void setPoints() {
	  setPointAy = (int) (maxHeight/5);
	  setPointAx = (int) (maxWidth/2 - 222/2);
	  setPointBy = (int) (maxHeight/1.75);
	  setPointBx = (int) (maxWidth/2-75);
	  setPointCy = (int) (maxHeight/1.5);
	  setPointCx = (int) (maxWidth/2 -75);
	  setPointDx = (int) (maxWidth/2 -75);
	  setPointDy = (int) (maxHeight/1.25);
	  selectIndx = (int) (maxWidth/2) - (int)(125 *ratioWidth);
	  selectIndy = (int) (maxHeight/1.75);

}
  
  private void selectorAni() {
	  
  }
  
  
  public void draw (Graphics g){
	  Color white = new Color(255,255,255);
	  Graphics2D mochiIcon = (Graphics2D) g.create();
	  Graphics2D Message1 = (Graphics2D) g.create();
	  Graphics2D Message2 = (Graphics2D) g.create();
	  Graphics2D Message3 = (Graphics2D) g.create();
	  Graphics2D selectInd = (Graphics2D) g.create();
	  mochiIcon.setClip(setPointAx, setPointAy, (int) (222*ratioWidth), (int)(225*ratioHeight));
	  mochiIcon.drawImage(mochiFaceState1, setPointAx, setPointAy, (int) (222*ratioWidth), (int)(225*ratioHeight), null);
	  Message1.setColor(Color.BLACK);
	  Message1.setFont(new Font ("Impact", Font.PLAIN, 48));
	  Message1.drawString(pointBText, setPointBx, setPointBy);
	  Message2.setColor(Color.BLACK);
	  Message2.setFont(new Font ("Impact", Font.PLAIN, 48));
	  Message2.drawString(pointCText, setPointCx, setPointCy);
	  Message3.setColor(Color.BLACK);
	  Message3.setFont(new Font ("Impact", Font.PLAIN, 48));
	  Message3.drawString(pointDText, setPointDx, setPointDy);
	  selectInd.setColor(Color.BLACK);
	  selectInd.setFont(new Font("Impact", Font.PLAIN, 48));
	  selectInd.drawString(selectIndText, selectIndx, selectIndy);
	  
	}
  
  private void selectionKeyInputs () {
	  MakeSelection MakeSelection = new MakeSelection();
	  MoveSelectionUp MoveSelectionUp = new MoveSelectionUp();
	  MoveSelectionDown MoveSelectionDown = new MoveSelectionDown();
	  Escape Escape = new Escape();
	  
	  InputMap im = lSPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	  ActionMap am = lSPanel.getActionMap();
	  im.put(KeyStroke.getKeyStroke("UP"), "MoveSelectionUp");
	  am.put("MoveSelectionUp", MoveSelectionUp);

	  im.put(KeyStroke.getKeyStroke("DOWN"), "MoveSelectorDown");
	  am.put("MoveSelectorDown", MoveSelectionDown);

	  im.put(KeyStroke.getKeyStroke("ENTER"), "MakeSelection");
	  am.put("MakeSelection", MakeSelection);
	  
	  
	  im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "Escape");
	  am.put("Escape", Escape);
	  
  }
  private class MakeSelection extends AbstractAction{
		// need keybindings for new button
		  public void actionPerformed (ActionEvent ms) {
				lr.importLevel(index);
				switcher.changePanel(2);
	}
  }
  private class MoveSelectionUp extends AbstractAction{
		  public void actionPerformed (ActionEvent ms) {
			  // why does this let me go out of bounds?
			  if (index < lr.names.size()-1) {
			  index++;
			  } 
		  }
  }
  
  private class MoveSelectionDown extends AbstractAction{
		// need keybindings for new button
		  public void actionPerformed (ActionEvent ms) {
			  if (index>0) {
				index--;  
			  }

	}
  }
  
  private class Escape extends AbstractAction{
		// need keybindings for new button
		  public void actionPerformed (ActionEvent ms) {
			  switcher.escapeUsed = true;
			  switcher.changePanel(1);

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
  