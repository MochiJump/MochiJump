import javax.swing.ImageIcon;

public class StartPause extends JPanel {
  
  boolean isStart;
  boolean isPause;
  
  public StartPause(){
   JPanel sPScreen = new JPanel();
    
   add (sPScreen);
  }
  
  public boolean getIsStart(){
    return this.isStart;
  }
  
  public boolean getIsPause(){
    returh this.isPause;
  }
  

}
