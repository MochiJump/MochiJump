import javax.swing.ImageIcon;

public class StartPause extends JPanel {
  
  //Remember, you can always break this away from the MochiJump program and run independantly for testing purposes.
  
  // images still need to be created and imported as .png files
  
  //Mochi class requires a keybinding for the pause button
  //this means that the element that holds keybinding for the main mochi jump program cannot be in the JPanel/Window
  // during the start pause sequence.
  
  // I'll need imageicons for the buttons, make button's invisible and give them the same dimensions and spacial properties
  // as the imageicons
  
  boolean isStart;
  boolean isPause;
  
  //actions for the start screen:
  private Action MoveSelectorUp;
  private Action MoveSelectorDown;
  Private Action MakeSelection;
  
  Image bg = new ImageIcon("background.png").getImage(); //<-- this should be renamed because it isn't actually a background.
  Image start = new ImageIcon("start.png").getImage();
  // I made this image, but it doesn't serve a purpose and may just need to be erased.
  Image pause = new ImageIcon("pause.png").getImage();
  // still need images attached to them
  Image continue = newImageIcon("continue.png").getImage();
  Image selector = new ImageIcon("createSelectorImage.png").getImage();
  // selector can me animated which would require creating a new animation method as well as multiple versions
  // of the selector image above
  
  // requires variables for draw method location as well as a method for getting the values for those variables
  
  public StartPause(){
    StartActionHelper startActionHelper = new StartActionHelper();
    PauseActionHelper pauseActionHelper = new PauseActionHelper();
   JPanel sPScreen = new JPanel();
    JButton startButton = new JButton(start);
    JButton continueButton = new JButton(continue); //<-- makes the image continue a JButton!
    startButton.addActionListener(startActionHelper);
    continueButton.addActionListener(pauseActionHelper);
    sPScreen.add(startScreenKeyInputs());
    sPScreen.add(startButton)
    sPScreen.add(continueButton)
    
        
   add (sPScreen);
  }
  
  public void draw (Graphics g){
    Graphics2D g2 = (Graphics2D) g.create();
    
   // all additional graphical implementation must go here 
    // g2.setClip();
    //g2.drawImage();
  }
  public boolean getIsStart(){
    return this.isStart;
  }
  
  public boolean getIsPause(){
    returh this.isPause;
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
  // let's go ahead and start with keybindings here:
  public JLabel startScreenKeyInputs (){
    JLabel MochiStartLabel = new JLabel ("Mochi Jump Start");
    
    MoveSelectorUp MoveSelectorUp = new MoveSelectorUp();
    MoveSelectorDown MoveSelectorDown = new MoveSelectorDown();
    MakeSelection MakeSelection = new MakeSelection();
    
    InputMap im = MochiStartLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = MochiStartLabel.getActionMap();
    
    // now create the maps to add the keys to the actions you want. easy peasy.
    
  }
  
  
  
  
  private class PauseActionHelper {
    public void actionPerformed (ActionEvent p{
     // here we just want the gameStart thread to continue
     isPause = false;
    }
  }
 // what else do we want here, a level restart button, which means we have to work with
// serialized peices of the game. or just a way to set the variable values back to the beginning
               // ultimately the level editor option would go in here as well.
}

