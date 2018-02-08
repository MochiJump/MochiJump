import javax.swing.ImageIcon;

public class StartPause extends JPanel {
  
  boolean isStart;
  boolean isPause;
  
  public StartPause(){
    StartActionHelper startActionHelper = new StartActionHelper();
    PauseActionHelper pauseActionHelper = new PauseActionHelper()'
   JPanel sPScreen = new JPanel();
    JButton start = new JButton();
    JButton exit = new JButton();
    start.addActionListener(startActionHelper);
        
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
      isStart = false;
    }
  }
  private class PauseActionHelper {
    public void actionPerformed (ActionEvent p{
     isPause = false
       //????
    }
  }
}

