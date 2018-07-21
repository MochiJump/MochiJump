package com.MochiJump;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
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
 * this class in not yet implemented. Currently refactoring project. Keeping for notes:
 * 
*/




public class LevelSelector extends JPanel{

	private int setPointAx;
	private int setPointAy;
	
	private Dimension screenSize;
	
	int test = 0;
	
Image mochiFaceState1 = new ImageIcon(this.getClass().getResource("/background.png")).getImage();
	
  JPanel lSPanel = new JPanel();
  int refreshRate = 30;
  int currentPanel;
  Switcher switcher;
  
  public LevelSelector (Switcher s) {
		lSPanel.add(selectionKeyInputs());
	 	add (lSPanel);
		levelSelectorActive();
		switcher = s;
		
	}
  
// will need to create and import images for ui
// http://www.java-gaming.org/index.php/topic,13599.0
// https://stackoverflow.com/questions/43205942/how-to-display-zoom-cubes-in-3d-in-javas-frames-panel
  
private Action MoveSelectonUp;
private Action MoveSelectonDown;
private Action MakeSelection;
  
private double maxHeight;
private double maxWidth;
private double ratioHeight;
private double ratioWidth;


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

}
  
  private void selectorAni() {
	  
  }
  
  public void importLevel () {
		ImportLevelReader lr = new ImportLevelReader();
		lr.ReadRest(switcher.dogLogic.levelMap);
		lr = null;
	}
  
  public void draw (Graphics g){
	  Graphics2D mochiIcon = (Graphics2D) g.create();
	  mochiIcon.setClip(setPointAx, setPointAy, (int) (222*ratioWidth), (int)(225*ratioHeight));
	  mochiIcon.drawImage(mochiFaceState1, setPointAx, setPointAy, (int) (222*ratioWidth), (int)(225*ratioHeight), null);
	 
	}
  
  private JLabel selectionKeyInputs () {
	  JLabel levelSelectLabel = new JLabel("Select Your Level");
	  MakeSelection MakeSelection = new MakeSelection();
	  
	  InputMap im = levelSelectLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	  ActionMap am = levelSelectLabel.getActionMap();
	  im.put(KeyStroke.getKeyStroke("UP"), "MoveSelectorUp");
	  im.put(KeyStroke.getKeyStroke("ENTER"), "MakeSelection");
	  am.put("MakeSelection", MakeSelection);
	  return levelSelectLabel;
	  
  }
  
  private class MakeSelection extends AbstractAction{
		// need keybindings for new button
		  public void actionPerformed (ActionEvent ms) {

			  if (test == 0) {
				importLevel();
				test++;
			  }else {
				switcher.changePanel(2);
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
  