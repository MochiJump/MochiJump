// I'm going to try to use a compound JPanel to hold the other two JPanels that we will switch between in this program.

// interested to see what happens with the repaint() & @Override Paint methods doing this.

// read:https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html

public class Switcher {
  
  DogLogic dogLogic = new DogLogic();
  StartPause startpause = new StartPause();
  
  public Switcher (){
    JPanel switcherPanel = new JPanel();
    add (switcherPanel);
    switchControl();
  }
  
  private void switchControl(){
    Thread controlThread = new Thread(){
      public void run (){
        while (true){
          //method for JPanel switcher goes here
          try {
            Thread.sleep(1000/refreshRate);
          }catch(InterruptedException s) {
            System.out.println("An error has occured in the Switcher class"):
          }
        }
      }
    };
          
        
    
  }

}
