import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.Action;

import javax.swing.*;

public class StartPause extends JPanel {
  

/** Okay I'm going to give the StartPause class it's own thread to run on... or should I? The main issue is how to
* "change screens" Do I want to actually flip between JPanels or do I want to have one panel that paints two
* different screens?
* 
* either way I can start by getting this to work independently in and then figure out how to integrate it. */

	
  /** To Do:
  * give this class it's own thread
  * Format layout of the screen
  * add a selector icon that moves with keybinding
  * program keybinding to only allow movement within menu options
  * Add mouse listener
  * Make MochiIcon blink when clicked */
  
  boolean isStart;
  boolean isPause;
  private int refreshRate = 30;
  
  //actions for the start screen:
  private Action MoveSelectorUp;
  private Action MoveSelectorDown;
  private Action MakeSelection;
  
  //okay we will need some variables to setup the layout of the startstopscreen:
  
  private int maxHeight;
  private int maxWidth;
  private int setPointAx;
  private int setPointAy;
  private int setPointBx;
  private int setPointBy;
  private int setPointCx;
  private int setPointCy;
  private int setSelectorPointx;
  private int setSelectorPointy;
  private int selectorWidth;
  private int selectorHeight;
  
  //variables button sizes
  private Dimension screenSize;
  private Dimension sbSize;
  private Dimension cbSize;
  private Dimension mbSize;
  
  Image mochiFaceState1 = new ImageIcon("background.png").getImage(); //<-- consider changing png file name
  Image mochiFaceState2 = new ImageIcon("blink.png").getImage();
  Image mochiFace;
  Image start = new ImageIcon("start.png").getImage();
  // I made this image, but it doesn't serve a purpose and may just need to be erased.
  Image pause = new ImageIcon("pause.png").getImage();
  // still need images attached to them
  Image cont = new ImageIcon("continue.png").getImage();
  Image selectorImage;
  // selector can me animated which would require creating a new animation method as well as multiple versions
  // of the selector image above to cycle through
  Image exit = new ImageIcon("exit.png").getImage();
  
  // requires variables for draw method location as well as a method for getting the values for those variables
  
  public StartPause(){
	JPanel sPScreen = new JPanel();
    // getting the height and width of the screen:
    screenSize = sPScreen.getSize();
    maxHeight = screenSize.height;
    maxWidth = screenSize.width;    
   add (sPScreen);
	startPauseActive();
  }
	
public void startPauseActive();
	Thread startPauseThread = new Thread(){
		public void run(){
			while (true){ //<-- This is a good place for the boolean switch maybe(?)
				//this will hold the start pause screen methods:
				try{
					Thread.sleep(1000/refreshRate);// <-- needs refreshrate variable should this be the same one as doglogics?
				}catch (InterruptedException ex){
					System.out.println("An error has occured in the StartPause Thread");
				}
			}
		}
	};
}
  
  private void setPoints() {
	  setPointAy = maxHeight/4;
	  setPointAx = maxWidth/2 - 222/2;
	  setPointBy = maxHeight/2;
	  setPointBx = maxWidth/2 -366/2;
	  setPointCx = maxHeight/2 +400;
	  setPointCy = maxWidth/2 - 3666/2;
  
  }
  public void draw (Graphics g){
    Graphics2D mochiIcon = (Graphics2D) g.create();
    Graphics2D startSelect = (Graphics2D) g.create();
    Graphics2D contSelect = (Graphics2D) g.create();
    Graphics2D selectorIcon = (Graphics2D) g.create();
    
    mochiIcon.setClip(setPointAx, setPointAy, 222, 225);
    mochiIcon.drawImage(mochiFace, setPointAx, SetPointAy, 222,225, null);
   
    startSelect.setClip(setPointBx, setPointBy, 366, 71);
    startSelect.drawImage(start, setPointBx, setPointBy, 366,71, null);
    contSelect.setClip(setPointCx, setPointCy, 366, 71);
    contSelect.drawImage(cont, setPointCx, setPointCy, 366, 71, null);
    selectorIcon.setClip(setSelectorPointx, setSelectorPointy, selectorWidth, selectorHeight);
    selectorIcon.drawImage(selectorImage, setSelectorPointx, setSelectorPointy, selectorWidth, selectorHeight, null);
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
    JLabel MochiStartLabel = new JLabel ("Mochi Jump Start");
    /**
    MoveSelectorUp MoveSelectorUp = new MoveSelectorUp();
    MoveSelectorDown MoveSelectorDown = new MoveSelectorDown();
    MakeSelection MakeSelection = new MakeSelection(); */
    
    InputMap im = MochiStartLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = MochiStartLabel.getActionMap();
    
    return MochiStartLabel;
    
  }
  
  private class MochiButtonHelper{
    // I guess we will make Mochi blink if this button is covered with the mouse
  }
  private class PauseActionHelper {
    public void actionPerformed (ActionEvent p){
     // here we just want the gameStart thread to continue
     isPause = false;
    }
  }

}
