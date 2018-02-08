import javax.swing.ImageIcon;

public class StartPause extends JPanel {
  
  boolean isStart;
  boolean isPause;
  
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
}

