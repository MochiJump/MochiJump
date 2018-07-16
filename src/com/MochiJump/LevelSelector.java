/*
* Factory build pattern deleted, is was unneccessary a static arrayList will work fine
* need to make arraylist from ImportLevelReader static and outside restReader method.
*/

package com.MochiJump;


public class LevelSelector extends JPanel{
  JPanel lSPanel = new JPanel();
  refreshRate = 30;
  static int currentPanel;
  
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

private Dimension screenSize;

static ArrayList <String> levelName;
static ArrayList <Integer> importOrder;
  
 public LevelSelector(){
   // to add keybinding lSPanel.add(componentwithkeybindingassociated);
   setupSelection();
   add(lSPanel);
   
 }
public void setCurrentPanel (int option) {
	this.currentPanel = option;
}
public int getCurrentPanel() {
	return this.currentPanel;
}
  public void setupSelection(){
	Thread startPauseThread = new Thread(){
		public void run(){
			while (currentPanel == 3){
				menuUpdate();
				repaint();
				getCurrentPanel();
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
    // this will be different compared to the startPause screen.
}
  //graphics and everything still needs to be created, in this case we will have 3 options that will be visible (initially 2)
  // the selector graphic will not move but instead always be linked to the middle option. will need to create a transition animation
  // down the road.
}
