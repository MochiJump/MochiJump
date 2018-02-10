import javax.swing.ImageIcon;

public class StartPause extends JPanel {
  
  // images still need to be created and imported as .png files
  
  //Mochi class requires a keybinding for the pause button
  //this means that the element that holds keybinding for the main mochi jump program cannot be in the JPanel/Window
  // during the start pause sequence.
  
  // I'll need imageicons for the buttons, make button's invisible and give them the same dimensions and spacial properties
  // as the imageicons
  
  boolean isStart;
  boolean isPause;
  
  Image bg = new ImageIcon("createBGImage.png").getImage();
  Image start = new ImageIcon("createStartImage.png").getImage();
  Image pause = new ImageIcon("createPauseImage.png").getImage();
  Image selector = new ImageIcon("createSelectorImage.png").getImage();
  // selector can me animated which would require creating a new animation method as well as multiple versions
  // of the selector image above
  
  public StartPause(){
    StartActionHelper startActionHelper = new StartActionHelper();
    PauseActionHelper pauseActionHelper = new PauseActionHelper();
   JPanel sPScreen = new JPanel();
    JButton start = new JButton();
    JButton continue = new JButton();
    start.addActionListener(startActionHelper);
    continue.addActionListener(pauseActionHelper);
    
        
   add (sPScreen);
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

