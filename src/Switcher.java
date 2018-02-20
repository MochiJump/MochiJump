// I'm going to try to use a compound JPanel to hold the other two JPanels that we will switch between in this program.

public class Switcher {
  
  DogLogic dogLogic = new DogLogic();
  StartPause startpause = new StartPause();
  
  public Switcher (){
    JPanel switcherPanel = new JPanel();
    add (switcherPanel);
  }

}
