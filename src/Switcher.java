// I'm going to try to use a compound JPanel to hold the other two JPanels that we will switch between in this program.

// read:https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html

public class Switcher {
  
  DogLogic dogLogic = new DogLogic();
  StartPause startpause = new StartPause();
  
  public Switcher (){
    JPanel switcherPanel = new JPanel();
    add (switcherPanel);
    switchControl();
  }
  
  private switchControl(){
    
  }

}
