import javax.swing.ImageIcon;

public class StartPause extends JPanel {
  
  //May be worth running this on it's own to see things visually before attemping to tie through Dog Logic
  
  /** To Do:
  * Format layout of the screen
  * add a selector icon that moves with keybinding
  * program keybinding to only allow movement within menu options
  * Add mouse listener
  * Make mochi button blink when clicked
  * Add level editor button? that functionality is not implemented yet
  */
  
  boolean isStart;
  boolean isPause;
  
  //actions for the start screen:
  private Action MoveSelectorUp;
  private Action MoveSelectorDown;
  private Action MakeSelection;
  
  //okay we will need some variables to setup the layout of the startstopscreen:
  
  private int maxHeight;
  private int maxWidth;
  private int selectionA;
  private int selectionB;
  private int selectionC;
  
  Image bg = new ImageIcon("background.png").getImage(); //<-- this should be renamed because it isn't actually a background.
  Image start = new ImageIcon("start.png").getImage();
  // I made this image, but it doesn't serve a purpose and may just need to be erased.
  Image pause = new ImageIcon("pause.png").getImage();
  // still need images attached to them
  Image continue = newImageIcon("continue.png").getImage();
  Image selector = new ImageIcon("createSelectorImage.png").getImage();
  // selector can me animated which would require creating a new animation method as well as multiple versions
  // of the selector image above
  Image exit = new ImageIcone("exit.png").getImage();
  
  // requires variables for draw method location as well as a method for getting the values for those variables
  
  public StartPause(){
    StartActionHelper startActionHelper = new StartActionHelper();
    ContinueActionHelper countinueActionHelper = new CountinueActionHelper();
    ExitActionHelper exitActionHelper = new ExitActionHelper();
    MochiButtonHelper mochiButtonHelper = new MochiButtonHelper();
   JPanel sPScreen = new JPanel();
    // getting the height and width of the screen:
    Dimension screenSize = sPScreen.getSize();
    maxHeight = screenSize.height;
    maxWidth = screenSize.width;
    JButton startButton = new JButton(start); //<-- makes the image continue a JButton!
    // must set location of these buttons instead of relying on a layout
    Dimension sbSize = startButton.getSize();
    startButton.setBounds(maxWidth/2, maxHeight/2, sbSize.width, sbSize.height); // <-- should place it center of screen
    //now just work out the layout you'd like to see
    JButton continueButton = new JButton(continue);
    //set location
    JButton mochiButton = new JButton(bg);
    //set location
    startButton.addActionListener(startActionHelper);
    continueButton.addActionListener(countinueActionHelper);
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
    
    MoveSelectorUp MoveSelectorUp = new MoveSelectorUp();
    MoveSelectorDown MoveSelectorDown = new MoveSelectorDown();
    MakeSelection MakeSelection = new MakeSelection();
    
    InputMap im = MochiStartLabel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = MochiStartLabel.getActionMap();
    
    // now create the maps to add the keys to the actions you want. easy peasy.
    
  }
  
  private class MochiButtonHelper{
    // I guess we will make Mochi blink if this button is covered with the mouse
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

